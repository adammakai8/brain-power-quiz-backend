package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.QuestionCollectionException;
import edu.maszek.brainpowerquiz.exception.ThemeCollectionException;
import edu.maszek.brainpowerquiz.mapper.lookup.QuestionLookup;
import edu.maszek.brainpowerquiz.model.*;
import edu.maszek.brainpowerquiz.repository.GameRepository;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ThemeService themeService;
    @Autowired
    private GameService gameService;

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream().map(QuestionDTO::new).toList();
    }

    @Override
    public QuestionDTO getQuestionByID(String id) throws QuestionCollectionException {
        Optional<QuestionEntity> question = questionRepository.findById(id);
        if(question.isPresent()) return new QuestionDTO(question.get());
        else throw new QuestionCollectionException(QuestionCollectionException.NotFoundException(id));
    }

    @Override
    public List<ThemeEntity> convertThemeIDsToEntities(List<String> themes) {
        List<ThemeEntity> themeEntities = new ArrayList<>();
        for(String theme: themes) {
            Optional<ThemeEntity> optionalTheme = themeRepository.findById(theme);

            optionalTheme.ifPresent(themeEntities::add);
        }
        return themeEntities;
    }

    @Override
    public List<GameEntity> convertGameIDsToEntities(List<String> games) {
        List<GameEntity> gameEntities = new ArrayList<>();
        for(String game: games) {
            Optional<GameEntity> optionalGame = gameRepository.findById(game);

            optionalGame.ifPresent(gameEntities::add);
        }
        return gameEntities;
    }

    @Override
    public void createQuestion(QuestionDTO questionDTO) throws ConstraintViolationException, QuestionCollectionException, ThemeCollectionException, GameCollectionException {
        QuestionEntity questionEntity = new QuestionEntity(questionDTO);
        if(checkCorrectAnswerCount(questionEntity)) {
            questionRepository.save(questionEntity);
            for(ThemeEntity themeEntity: questionDTO.getThemes()) {
                themeService.updateTheme(themeEntity);
            }
            for(GameEntity gameEntity: questionDTO.getGames()) {
                gameService.updateGame(gameEntity);
            }
        }
        else throw new QuestionCollectionException(QuestionCollectionException.MultipleCorrectAnswerNotAccepted());
    }

    @Override
    public void updateQuestion(QuestionDTO questionDTO) throws QuestionCollectionException {
        String questionID = questionDTO.get_id();
        Optional<QuestionEntity> questionOptional = questionRepository.findById(questionID);

        if(questionOptional.isPresent()) {
            QuestionEntity questionEntity = new QuestionEntity(questionDTO);

            if(checkCorrectAnswerCount(questionEntity)) {
                QuestionEntity questionToUpdate = questionOptional.get();

                questionToUpdate.setText(questionEntity.getText());
                questionToUpdate.setDifficulty(questionEntity.getDifficulty());
                questionToUpdate.setAnswers(questionEntity.getAnswers());
                questionToUpdate.setThemes(questionEntity.getThemes());
                questionToUpdate.setGames(questionEntity.getGames());
                questionRepository.save(questionToUpdate);
            } else throw new QuestionCollectionException(QuestionCollectionException.MultipleCorrectAnswerNotAccepted());
        } else throw new QuestionCollectionException(QuestionCollectionException.NotFoundException(questionID));
    }

    @Override
    public void deleteQuestionByID(String id) throws QuestionCollectionException, ThemeCollectionException, GameCollectionException {
        Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()) {
            QuestionEntity question = questionOptional.get();
            for(String theme: question.getThemes()) {
                ThemeEntity themeEntity = themeService.getThemeByID(theme);
                themeEntity.setQuestions(
                        themeEntity
                                .getQuestions()
                                .stream()
                                .filter(filterQuestion -> !filterQuestion.equals(question.get_id()))
                                .toList());
                themeService.updateTheme(themeEntity);
             }
            for(String game: question.getGames()) {
                GameEntity gameEntity = gameService.getGameByID(game);
                gameEntity.setQuestions(
                        gameEntity
                                .getQuestions()
                                .stream()
                                .filter(filterQuestion -> !filterQuestion.equals(question.get_id()))
                                .toList());
                gameService.updateGame(gameEntity);
            }
            questionRepository.deleteById(id);
        }
        else throw new QuestionCollectionException(QuestionCollectionException.NotFoundException(id));
    }

    private boolean checkCorrectAnswerCount(QuestionEntity questionEntity) {
        long count = questionEntity.getAnswers().stream().filter(Answer::getIsCorrect).count();
        return count <= 1;
    }
}
