package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.*;
import edu.maszek.brainpowerquiz.model.entity.AnswerEntity;
import edu.maszek.brainpowerquiz.model.entity.GameEntity;
import edu.maszek.brainpowerquiz.model.entity.QuestionEntity;
import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import edu.maszek.brainpowerquiz.model.property.QuestionPropertyEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import edu.maszek.brainpowerquiz.repository.AnswerRepository;
import edu.maszek.brainpowerquiz.repository.GameRepository;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ConnectionUpdateServiceImpl connectionUpdateService;

    @Override
    public List<GameEntity> getAllGames() {
        List<GameEntity> gamesOptional = gameRepository.findAll();
        if(gamesOptional.size() > 0) return gamesOptional;
        else return new ArrayList<>();
    }

    @Override
    public GameEntity getGameByID(final String id) throws GameCollectionException {
        Optional<GameEntity> game = gameRepository.findById(id);
        if(game.isPresent()) return game.get();
        else throw new GameCollectionException(GameCollectionException.NotFoundException(id));
    }

    public GameEntity getGameByName(final String name) throws GameCollectionException {
        Optional<GameEntity> game = gameRepository.findByName(name);
        if(game.isPresent()) return game.get();
        else throw new GameCollectionException(GameCollectionException.NotFoundByNameException(name));
    }

    @Override
    public List<String> getPlayedGamesByUser(String username) {
        return answerRepository.findAll().stream()
                .filter(answer -> answer.getUser().getUsername().equals(username))
                .collect(Collectors.groupingBy(answer -> answer.getGame().get_id()))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() == 10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public GameEntity startGame(final String gameId, final String username)
            throws UserCollectionException, GameCollectionException, BadHttpRequest {
        final UserEntity currentUser = userService.getUserByName(username);

        final GameEntity gameToPlay = getGameByID(gameId);
        if (Objects.isNull(gameToPlay.getPlayers())) {
            gameToPlay.setPlayers(new ArrayList<>());
        }
        if (gameToPlay.getPlayers().stream().map(UserPropertyEntity::get_id).noneMatch(id -> id.equals(currentUser.get_id()))) {
            if (gameToPlay.isFull()) {
                throw new BadHttpRequest(new IllegalStateException("Game is already full"));
            }
            gameToPlay.getPlayers().add(mapUserToProperty(currentUser));
            return gameRepository.save(gameToPlay);
        } else {
            return gameToPlay;
        }
    }

    @Override
    public void submitQuiz(final AnswerEntityCreationData[] answers, final String username) throws UserCollectionException {
        final UserEntity player = userService.getUserByName(username);
        answerRepository.saveAll(Arrays.stream(answers)
                .map(answer -> AnswerEntity.builder()
                        .point(answer.getPoint())
                        .user(mapUserToProperty(player))
                        .game(answer.getGame())
                        .question(answer.getQuestion())
                        .build())
                .collect(Collectors.toList()));
    }

    @Override
    public GameEntity createGame(final GameCreationData gameData) {
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
    public void updateGame(final GameEntity gameEntity) throws GameCollectionException {
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
    public void deleteGameByID(final String id) throws GameCollectionException {
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

    private UserPropertyEntity mapUserToProperty(final UserEntity user) {
        return UserPropertyEntity.builder()
                ._id(user.get_id())
                .username(user.getUsername())
                .email(user.getEmail())
                .birthYear(user.getBirthYear())
                .password(user.getPassword())
                .build();
    }
}
