package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.model.entity.AnswerEntity;
import edu.maszek.brainpowerquiz.model.entity.ThemeEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.repository.AnswerRepository;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import edu.maszek.brainpowerquiz.statistic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<ThemeCountInGames> getThemesByPopularity() {
        return themeRepository.findAll().stream()
                .map(ThemeCountInGames::new).toList();
    }

    @Override
    public GlobalStatistics getGlobalStatistics() {
        List<AnswerEntity> answers = answerRepository.findAll();
        List<ThemeEntity> themes = themeRepository.findAll();

        List<StatisticsGroup> statisticsByAge = new ArrayList<>();
        List<StatisticsGroup> statisticsByTheme = new ArrayList<>();

        // popular themes
        List<ThemeCountInGames> popularThemes = themes.stream().map(ThemeCountInGames::new).toList();

        // age-groups
        int range = 15;
        int cv = 6; // cv * range interval

        for(int i = 0; i < cv; i++) {
            int finalI = i;
            List<AnswerEntity> currentAnswers = answers.stream()
                    .filter(answerEntity -> (finalI * range) <= answerEntity.getUser().getAge() && answerEntity.getUser().getAge() < (finalI + 1) * range).toList();
            addNewStatisticsGroupToTheResults(currentAnswers, statisticsByAge, i, range);
        }

        // themes
        List<ThemePropertyEntity> themePropertyEntities = themes.stream().map(ThemePropertyEntity::new).toList();

        for(ThemePropertyEntity theme: themePropertyEntities) {
            List<AnswerEntity> currentAnswers = answers.stream()
                    .filter(answerEntity -> answerEntity.getQuestion().getThemes().stream().map(ThemePropertyEntity::getText).toList().contains(theme.getText())).toList();
            addNewStatisticsGroupToTheResults(currentAnswers, statisticsByTheme, theme.getText());
        }

        return new GlobalStatistics(popularThemes, statisticsByAge, statisticsByTheme);
    }

    @Override
    public List<ThemeCountInGames> getUserFavouriteThemes(String _id) {
        // get all answerEntity
        List<AnswerEntity> answers = answerRepository.findAll();
        // get user answerEntities
        answers = answers.stream().filter(answerEntity -> answerEntity.getUser().get_id().equals(_id)).collect(Collectors.toList());


        var themeCountInUserGames = answers.stream().map(answerEntity -> answerEntity.getQuestion().getThemes())    // get the themes
                .flatMap(themePropertyEntities -> themePropertyEntities.stream().map(ThemePropertyEntity::getText)) // get the themes text, and in only one array
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))                         // group them by the string value, and count them
                .entrySet().stream().sorted(Comparator.comparingLong(Map.Entry::getValue))                          // sort the list by the count value
                .limit(3)                                                                                   // limit that the first 3 remains
                .toList();

        // convert map to array
        List<ThemeCountInGames> result = new ArrayList<>();
        for(Map.Entry<String, Long> entry: themeCountInUserGames) {
            result.add(new ThemeCountInGames(entry.getKey(), Math.toIntExact(entry.getValue())));
        }
        return result;
    }

    @Override
    public UserStatistics getUserStatistics(String id) {
        List<AnswerEntity> answers = answerRepository.findAll().stream().filter(answerEntity -> answerEntity.getUser().get_id().equals(id)).toList();
        List<ThemePropertyEntity> themes = themeRepository.findAll().stream().map(ThemePropertyEntity::new).toList();

        List<StatisticsGroup> answersByDifficulty = new ArrayList<>();
        List<StatisticsGroup> answersByTheme = new ArrayList<>();

        Double averagePoints = calculateAveragePoint(answers);

        for(int i = 0; i < 3; i++) {
            final int finalI = i;
            List<AnswerEntity> currentAnswers = answers.stream().filter(answerEntity -> answerEntity.getQuestion().getDifficulty() == finalI).toList();
            addNewStatisticsGroupToTheResults(currentAnswers, answersByDifficulty, i);
        }

        for(ThemePropertyEntity theme: themes) {
            List<AnswerEntity> currentAnswers = answers.stream()
                    .filter(answerEntity -> answerEntity.getQuestion().getThemes().stream().map(ThemePropertyEntity::getText).toList().contains(theme.getText())).toList();
            addNewStatisticsGroupToTheResults(currentAnswers, answersByTheme, theme.getText());
        }

        return new UserStatistics(averagePoints, answersByDifficulty, answersByTheme);
    }

    private void addNewStatisticsGroupToTheResults(List<AnswerEntity> currentAnswers, List<StatisticsGroup> results, int i) {
        Double[] values = getNumericValues(currentAnswers);
        results.add(new StatisticsGroup(String.valueOf(i), values[0], values[1], values[2], values[3], values[4]));
    }

    private void addNewStatisticsGroupToTheResults(List<AnswerEntity> currentAnswers, List<StatisticsGroup> results, int i, int range) {
        Double[] values = getNumericValues(currentAnswers);
        results.add(new StatisticsGroup(i, range, values[0], values[1], values[2], values[3], values[4]));
    }

    private void addNewStatisticsGroupToTheResults(List<AnswerEntity> currentAnswers, List<StatisticsGroup> results, String theme) {
        Double[] values = getNumericValues(currentAnswers);
        results.add(new StatisticsGroup(theme, values[0], values[1], values[2], values[3], values[4]));
    }

    private Double[] getNumericValues(List<AnswerEntity> currentAnswers) {
        return new Double[] {
                calculateAveragePoint(currentAnswers),
                calculateCorrectAnswerRatio(currentAnswers, 0),
                calculateCorrectAnswerRatio(currentAnswers, 1),
                calculateCorrectAnswerRatio(currentAnswers, 2),
                calculateCorrectAnswerRatio(currentAnswers)
        };
    }

    private Double calculateAveragePoint(List<AnswerEntity> currentAnswers) {
        return (double)
                (currentAnswers.size() == 0 ? 0 : currentAnswers.stream().map(AnswerEntity::getPoint).mapToInt(Integer::intValue).sum() / currentAnswers.size());
    }

    private Double calculateCorrectAnswerRatio(List<AnswerEntity> currentAnswers) {
        return calculateCorrectAnswerRatio(currentAnswers, -1);
    }

    private Double calculateCorrectAnswerRatio(List<AnswerEntity> currentAnswers, int difficulty) {
        if(currentAnswers.size() == 0
                || currentAnswers.stream().filter(answerEntity -> answerEntity.getQuestion().getDifficulty() == difficulty).toList().size() == 0)
            return 0.;
        return (double) (currentAnswers.stream()
                        .filter(answerEntity -> answerEntity.getPoint() > 0
                                && (difficulty < 0 || answerEntity.getQuestion().getDifficulty() == difficulty))
                        .toList().size()
                        /
                        (difficulty < 0 ?
                                currentAnswers.size():
                                currentAnswers.stream().filter(answerEntity -> answerEntity.getQuestion().getDifficulty() == difficulty).toList().size()));
    }
}
