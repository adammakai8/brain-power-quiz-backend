package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.model.entity.AnswerEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.repository.AnswerRepository;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import edu.maszek.brainpowerquiz.statistic.ThemeCountInGames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    public List<ThemeCountInGames> getUserFavouriteThemes(String _id) {
        // get all answerEntity
        List<AnswerEntity> answers = answerRepository.findAll();
        // get user answerEntities
        answers = answers.stream().filter(answerEntity -> answerEntity.getUser().get_id().equals(_id)).collect(Collectors.toList());


        var themeCountInUserGames = answers.stream().map(answerEntity -> answerEntity.getQuestion().getThemes())    // get the themes
                .flatMap(themePropertyEntities -> themePropertyEntities.stream().map(ThemePropertyEntity::getText)) // get the themes in String, and in only one array
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
    public List<AnswerEntity> getAllAnswers() {
        List<AnswerEntity> answerEntities = answerRepository.findAll();
        if(answerEntities.size() > 0) return answerEntities;
        else return new ArrayList<>();
    }
}
