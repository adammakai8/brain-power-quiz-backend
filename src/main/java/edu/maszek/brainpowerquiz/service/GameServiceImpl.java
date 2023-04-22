package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.model.*;
import edu.maszek.brainpowerquiz.repository.GameRepository;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ConnectionUpdateServiceImpl connectionUpdateService;

    @Override
    public List<GameEntity> getAllGames() {
        List<GameEntity> gamesOptional = gameRepository.findAll();
        if(gamesOptional.size() > 0) return gamesOptional;
        else return new ArrayList<>();
    }

    @Override
    public GameEntity getGameByID(String id) throws GameCollectionException {
        Optional<GameEntity> game = gameRepository.findById(id);
        if(game.isPresent()) return game.get();
        else throw new GameCollectionException(GameCollectionException.NotFoundException(id));
    }

    public GameEntity getGameByName(String name) throws GameCollectionException {
        Optional<GameEntity> game = gameRepository.findByName(name);
        if(game.isPresent()) return game.get();
        else throw new GameCollectionException(GameCollectionException.NotFoundByNameException(name));
    }

    @Override
    public GameEntity createGame(GameCreationData gameData) {
        final List<String> gameThemeIds = gameData.getThemes().stream().map(ThemePropertyEntity::get_id).toList();
        final List<QuestionEntity> questionsOfThemes = questionRepository.findAll().stream()
                .filter(question -> question.getThemes().stream()
                        .map(ThemePropertyEntity::get_id)
                        .anyMatch(gameThemeIds::contains))
                .toList();

        final List<QuestionPropertyEntity> generatedQuestions = getRandomQuestionsbyDifficulty(
                questionsOfThemes, gameData.getEasyQuestions(), 0);
        generatedQuestions.addAll(getRandomQuestionsbyDifficulty(
                questionsOfThemes, gameData.getMediumQuestions(), 1));
        generatedQuestions.addAll(getRandomQuestionsbyDifficulty(
                questionsOfThemes, gameData.getHardQuestions(), 2));


        final GameEntity gameEntity = GameEntity.builder()
                .name(gameData.getName())
                .maximalPlayerNumber(gameData.getMaximalPlayerNumber())
                .closeDate(gameData.getCloseDate())
                .themes(gameData.getThemes())
                .questions(generatedQuestions)
                .build();
        gameRepository.save(gameEntity);
        connectionUpdateService.updateThemeConnection("game", gameEntity, "create");
        connectionUpdateService.updateQuestionConnection("game", gameEntity, "create");
        return gameEntity;
    }

    @Override
    public void updateGame(GameEntity gameEntity) throws GameCollectionException {
        String gameID = gameEntity.get_id();
        Optional<GameEntity> gameOptional = gameRepository.findById(gameID);

        if(gameOptional.isPresent()) {
            GameEntity gameToUpdate = gameOptional.get();

            gameToUpdate.setName(gameEntity.getName());
            gameToUpdate.setMaximalPlayerNumber(gameEntity.getMaximalPlayerNumber());
            gameToUpdate.setCloseDate(gameEntity.getCloseDate());
            connectionUpdateService.updateThemeConnection("game", gameEntity, "update");
            connectionUpdateService.updateQuestionConnection("game", gameEntity, "update");
            gameRepository.save(gameToUpdate);
        } else throw new GameCollectionException(GameCollectionException.NotFoundException(gameID));
    }

    @Override
    public void deleteGameByID(String id) throws GameCollectionException {
        Optional<GameEntity> gameOptional = gameRepository.findById(id);
        if(gameOptional.isPresent()) {
            GameEntity gameEntity = gameOptional.get();
            connectionUpdateService.updateThemeConnection("game", gameEntity, "delete");
            connectionUpdateService.updateQuestionConnection("game", gameEntity, "delete");
            gameRepository.deleteById(id);
        }
        else throw new GameCollectionException(GameCollectionException.NotFoundException(id));
    }

    private List<QuestionPropertyEntity> getRandomQuestionsbyDifficulty(
            final List<QuestionEntity> questionPool,
            final Integer questionQuantity,
            final Integer difficulty
    ) {
        return questionPool.stream()
                .filter(question -> Objects.equals(question.getDifficulty(), difficulty))
                .map(question -> QuestionPropertyEntity.builder()
                        ._id(question.get_id())
                        .text(question.getText())
                        .difficulty(question.getDifficulty())
                        .answers(question.getAnswers())
                        .build())
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                })).subList(0, questionQuantity);
    }
}
