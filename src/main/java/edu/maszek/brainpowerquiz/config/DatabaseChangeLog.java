package edu.maszek.brainpowerquiz.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.model.AnswerEntityCreationData;
import edu.maszek.brainpowerquiz.model.GameCreationData;
import edu.maszek.brainpowerquiz.model.entity.*;
import edu.maszek.brainpowerquiz.model.property.GamePropertyEntity;
import edu.maszek.brainpowerquiz.model.property.QuestionPropertyEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import edu.maszek.brainpowerquiz.repository.*;
import edu.maszek.brainpowerquiz.role.Role;
import edu.maszek.brainpowerquiz.service.GameService;
import edu.maszek.brainpowerquiz.statistic.QuestionStatisticEntity;
import edu.maszek.brainpowerquiz.util.QuestionObjectMother;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

@ChangeLog
public class DatabaseChangeLog {

    private static final String MATEMATIKA = "Matematika";
    private static final String TORTENELEM = "Történelem";
    private static final String FIZIKA = "Fizika";
    private static final String KEMIA = "Kémia";
    private static final String BIOLOGIA = "Biológia";
    private static final String FOLDRAJZ = "Földrajz";
    private static final String SPORT = "Sport";
    private static final String INFORMATIKA = "Informatika";
    private static final String ANGOL = "Angol";
    private static final String GASZTRONOMIA = "Gasztronómia";
    private static final String IRODALOM = "Irodalom";

    @ChangeSet(order = "001", id = "initializeData", author = "adamax")
    public void initializeData(
            final UserRepository userRepository,
            final RoleRepository roleRepository,
            final QuestionRepository questionRepository,
            final ThemeRepository themeRepository
    ) {
        final Map<String, ThemeEntity> themes = themeRepository.insert(List.of(
                createTheme(MATEMATIKA),
                createTheme(TORTENELEM),
                createTheme(FIZIKA),
                createTheme(KEMIA),
                createTheme(BIOLOGIA),
                createTheme(FOLDRAJZ),
                createTheme(SPORT),
                createTheme(INFORMATIKA),
                createTheme(ANGOL),
                createTheme(GASZTRONOMIA),
                createTheme(IRODALOM)
        )).stream()
                .collect(Collectors.toMap(ThemeEntity::get_id, Function.identity()));

        roleRepository.insert(List.of(
                createRole("USER"),
                createRole("ADMIN")
        ));
        userRepository.insert(createAdmin(roleRepository.findByName("ADMIN")));

        final List<QuestionEntity> questions = QuestionObjectMother.createQuestions(
                questionRepository,
                themes.values().stream().collect(Collectors.toMap(ThemeEntity::getText,
                        theme -> new ThemePropertyEntity(theme.get_id(), theme.getText()))));


        questions.forEach(question -> question.getThemes()
                .forEach(theme -> themes.get(theme.get_id())
                        .getQuestions().add(new QuestionPropertyEntity(
                                question.get_id(),
                                question.getText(),
                                question.getDifficulty(),
                                question.getAnswers()
                        ))));
        themeRepository.saveAll(themes.values());
    }

    @ChangeSet(order = "002", id = "initializeUsersAndGames", author = "adamax")
    public void initializeUsersAndGames(
            final UserRepository userRepository,
            final RoleRepository roleRepository,
            final ThemeRepository themeRepository,
            final GameRepository gameRepository,
            final AnswerRepository answerRepository,
            final GameService gameService
            ) throws GameCollectionException {
        final Role userRole = roleRepository.findByName("USER");

        final UserEntity user1 = userRepository.insert(createUser(userRole, "John", 1995));
        final UserEntity user2 = userRepository.insert(createUser(userRole, "Mary", 1984));
        final UserEntity user3 = userRepository.insert(createUser(userRole, "Greg", 1971));
        final UserEntity user4 = userRepository.insert(createUser(userRole, "Amy", 2006));
        final UserEntity user5 = userRepository.insert(createUser(userRole, "Zun", 1977));

        final List<ThemeEntity> themes = themeRepository.findAll();

        GameEntity game1 = gameService.createGame(
                new GameCreationData("Quiz 1", 8, new Date(2023, Calendar.JUNE, 1), getRandomThemes(themes), 3, 5, 2));
        GameEntity game2 = gameService.createGame(
                new GameCreationData("Quiz 2", 3, new Date(2023, Calendar.MAY, 17), getRandomThemes(themes), 3, 5, 2));
        GameEntity game3 = gameService.createGame(
                new GameCreationData("Mid March Quiz", 8, new Date(2023, Calendar.MARCH, 15), getRandomThemes(themes), 5, 5, 0));
        GameEntity game4 = gameService.createGame(
                new GameCreationData("Quiz Masters Fall", 10, new Date(2023, Calendar.SEPTEMBER, 1), getRandomThemes(themes), 5, 1, 4));

        playGame(game1, user1, gameRepository, answerRepository);
        playGame(game1, user2, gameRepository, answerRepository);
        playGame(game1, user3, gameRepository, answerRepository);
        playGame(game1, user4, gameRepository, answerRepository);
        playGame(game1, user5, gameRepository, answerRepository);
        playGame(game2, user5, gameRepository, answerRepository);
        playGame(game2, user4, gameRepository, answerRepository);
        playGame(game2, user3, gameRepository, answerRepository);
        playGame(game3, user1, gameRepository, answerRepository);
        playGame(game3, user2, gameRepository, answerRepository);
        playGame(game3, user3, gameRepository, answerRepository);
        playGame(game3, user4, gameRepository, answerRepository);
        playGame(game3, user5, gameRepository, answerRepository);
        playGame(game4, user1, gameRepository, answerRepository);
        playGame(game4, user2, gameRepository, answerRepository);
        playGame(game4, user3, gameRepository, answerRepository);
        playGame(game4, user4, gameRepository, answerRepository);
        playGame(game4, user5, gameRepository, answerRepository);
    }

    private void playGame(GameEntity game, final UserEntity player, final GameRepository gameRepository,
                          final AnswerRepository answerRepository) {
        game.getPlayers().add(mapToUserProperty(player));
        game = gameRepository.save(game);
        answerRepository.saveAll(Arrays.stream(createAnserData(game))
                .map(answer -> AnswerEntity.builder()
                        .point(answer.getPoint())
                        .user(mapToUserProperty(player))
                        .game(answer.getGame())
                        .question(answer.getQuestion())
                        .build())
                .collect(Collectors.toList()));
    }

    private GameEntity createGame(final GameCreationData gameData) {
        return GameEntity.builder()
                .name(gameData.getName())
                .maximalPlayerNumber(gameData.getMaximalPlayerNumber())
                .closeDate(gameData.getCloseDate())
                .themes(gameData.getThemes())
//                .questions(generatedQuestions)
                .build();
    }

    private AnswerEntityCreationData[] createAnserData(final GameEntity game) {
        final Random rng = new Random();
        final List<AnswerEntityCreationData> result = new ArrayList<>();
        for (int i = 0; i < game.getQuestions().size(); i++) {
            result.add(AnswerEntityCreationData.builder()
                    .point(rng.nextInt(2) * rng.nextInt(300))
                    .game(mapToGameProperty(game))
                    .question(mapToQuestionStat(game.getQuestions().get(i)))
                    .build());
        }
        return result.toArray(new AnswerEntityCreationData[0]);
    }

    private GamePropertyEntity mapToGameProperty(final GameEntity game) {
        return GamePropertyEntity.builder()
                ._id(game.get_id())
                .name(game.getName())
                .closeDate(game.getCloseDate())
                .maximalPlayerNumber(game.getMaximalPlayerNumber())
                .build();
    }

    private QuestionStatisticEntity mapToQuestionStat(final QuestionPropertyEntity quest) {
        return QuestionStatisticEntity.builder()
                ._id(quest.get_id())
                .answers(quest.getAnswers())
                .difficulty(quest.getDifficulty())
                .text(quest.getText())
                .build();
    }

    private UserPropertyEntity mapToUserProperty(final UserEntity user) {
        return UserPropertyEntity.builder()
                ._id(user.get_id())
                .birthYear(user.getBirthYear())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    private List<ThemePropertyEntity> getRandomThemes(final List<ThemeEntity> themes) {
        return themes.stream()
                .map(ThemePropertyEntity::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                }))
                .subList(0, new Random().nextInt(1, 5));
    }

    private Role createRole(String name) {
        final Role role = new Role();
        role.setName(name);
        return role;
    }

    private UserEntity createUser(final Role userRole, final String username, final Integer birthYear) {
        return UserEntity.builder()
                .role(userRole)
                .username(username)
                .birthYear(birthYear)
                .email("-")
                .password("$2a$10$oCNewvCWtOSniQogwZHCmuXswb3e1g4qv8ukjIG6m6GImxgx/BKKK")
                .build();
    }

    private UserEntity createAdmin(Role adminRole) {
        return UserEntity.builder()
                .role(adminRole)
                .username("admin")
                .birthYear(0)
                .email("-")
                .password("$2a$10$oCNewvCWtOSniQogwZHCmuXswb3e1g4qv8ukjIG6m6GImxgx/BKKK")
                .build();
    }

    private ThemeEntity createTheme(String text) {
        final ThemeEntity theme = new ThemeEntity();
        theme.setText(text);
        theme.setQuestions(new ArrayList<>());
        return theme;
    }
}
