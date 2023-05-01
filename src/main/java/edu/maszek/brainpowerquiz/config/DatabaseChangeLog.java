package edu.maszek.brainpowerquiz.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.model.AnswerEntityCreationData;
import edu.maszek.brainpowerquiz.model.GameCreationData;
import edu.maszek.brainpowerquiz.model.entity.AnswerEntity;
import edu.maszek.brainpowerquiz.model.entity.ForumEntity;
import edu.maszek.brainpowerquiz.model.entity.GameEntity;
import edu.maszek.brainpowerquiz.model.entity.QuestionEntity;
import edu.maszek.brainpowerquiz.model.entity.ThemeEntity;
import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import edu.maszek.brainpowerquiz.model.property.GamePropertyEntity;
import edu.maszek.brainpowerquiz.model.property.QuestionPropertyEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import edu.maszek.brainpowerquiz.model.request.ForumCommentRequest;
import edu.maszek.brainpowerquiz.repository.AnswerRepository;
import edu.maszek.brainpowerquiz.repository.ForumRepository;
import edu.maszek.brainpowerquiz.repository.GameRepository;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import edu.maszek.brainpowerquiz.repository.RoleRepository;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import edu.maszek.brainpowerquiz.role.Role;
import edu.maszek.brainpowerquiz.service.ForumCommentService;
import edu.maszek.brainpowerquiz.service.GameService;
import edu.maszek.brainpowerquiz.statistic.QuestionStatisticEntity;
import edu.maszek.brainpowerquiz.util.QuestionObjectMother;
import jakarta.validation.ConstraintViolationException;

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
                new GameCreationData("Quiz 1", 8, new Date(123, Calendar.JUNE, 1), getRandomThemes(themes), 3, 5, 2));
        GameEntity game2 = gameService.createGame(
                new GameCreationData("Quiz 2", 3, new Date(123, Calendar.MAY, 17), getRandomThemes(themes), 3, 5, 2));
        GameEntity game3 = gameService.createGame(
                new GameCreationData("Mid March Quiz", 8, new Date(123, Calendar.MARCH, 15), getRandomThemes(themes), 5, 5, 0));
        GameEntity game4 = gameService.createGame(
                new GameCreationData("Quiz Masters Fall", 10, new Date(123, Calendar.SEPTEMBER, 1), getRandomThemes(themes), 5, 1, 4));

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

    @ChangeSet(order = "003", id = "forumAndCommentsTestData", author = "adamax")
    public void forumAndCommentsTestData(
            final ForumRepository forumRepository,
            final UserRepository userRepository,
            final ForumCommentService forumCommentService
    ) throws ConstraintViolationException, ForumCommentCollectionException {
        // Admin should be there
        final UserEntity admin = userRepository.findByUsername("admin").get();

        // Getting other default users
        final UserEntity user1 = userRepository.findByUsername("John").orElse(createUser(new Role(), "John", 1995));
        final UserEntity user2 = userRepository.findByUsername("Mary").orElse(createUser(new Role(), "Mary", 1984));
        final UserEntity user3 = userRepository.findByUsername("Greg").orElse(createUser(new Role(), "Greg", 1971));
        final UserEntity user4 = userRepository.findByUsername("Amy").orElse(createUser(new Role(), "Amy", 2006));
        final UserEntity user5 = userRepository.findByUsername("Zun").orElse(createUser(new Role(), "Zun", 1977));

        // Creating forums
        final ForumEntity forum1 = forumRepository.insert(
                new ForumEntity("Hogyan számítja a rendszer a pontokat? Nem látom benne a logikát.",
                        new UserPropertyEntity(user1)));
        final ForumEntity forum2 = forumRepository.insert(
                new ForumEntity("A kérdésbank nem lehet, kissé elavult? A megyéket nem írták át vármegyékre.",
                        new UserPropertyEntity(user2)));
        final ForumEntity forum3 = forumRepository.insert(
                new ForumEntity("Túl nagy a különbség nehézség terén, ki mit gondol erről?",
                        new UserPropertyEntity(user3)));

        // Creating forum comments
        final List<ForumCommentRequest> comments = List.of(
                // Forum 1
                new ForumCommentRequest(
                        "Ez jó kérdés, én is gondolkoztam már ezen... Valaki tudja?",
                        forum1,
                        user2.getUsername()),
                new ForumCommentRequest(
                        "Nem tudom a részleteket, de a lényeg: minél nehezebb a kérdés annál több pontot kapsz rá, de minél tovább gondolkozol rajta annál kevesebbet.",
                        forum1,
                        user3.getUsername()),
                new ForumCommentRequest(
                        "Van egy képlet, ami a jó válaszokra számolja a pontokat az idő függvényében, és azt beszorozza a kérdés nehézségi szintjével. Szívesen :D",
                        forum1,
                        user5.getUsername()),
                //Forum 2
                new ForumCommentRequest(
                        "Úgy tudom a kérdésbank a régi asztali verzióból lett átemelve, amit 2020 óta nem frissítettek, szólni kell az supportnak (admin)",
                        forum2,
                        user5.getUsername()),
                new ForumCommentRequest(
                        "Tényleg két földrajzos kérdésnél is látom, szörnyű hogy az adminok ezt nem vették észre :(",
                        forum2,
                        user4.getUsername()),
                new ForumCommentRequest(
                        "Szerintem nem túl nagy dolog ez, a lényegen nem változtat",
                        forum2,
                        user1.getUsername()),
                new ForumCommentRequest(
                        "Update: nem csak a vármegyékkel van baj, az angolos kérdéseknél is változtatni kell (rip :/)",
                        forum2,
                        user2.getUsername()),
                new ForumCommentRequest(
                        "Átvizsgáljuk a kérdésbankot, kijavítjuk a hibákat",
                        forum2,
                        admin.getUsername()),
                new ForumCommentRequest(
                        "Megvagyunk, elvileg most már minden kérdés up to date",
                        forum2,
                        admin.getUsername()),
                new ForumCommentRequest(
                        "Igen, most már jó, köszönjük :)",
                        forum2,
                        user2.getUsername()),
                // Forum 3
                new ForumCommentRequest(
                        "Skill issue :P",
                        forum3,
                        user4.getUsername()),
                new ForumCommentRequest(
                        "Valóban néhány kérdés pofon egyszerű, néhányon meg csak pislogok mi ez... De hát valahogy a nagyokosokat is el kell szórakoztatni",
                        forum3,
                        user2.getUsername()),
                new ForumCommentRequest(
                        "Nem tudom mi a probléma, nekem mindegyik kérdés egyszerűnek tűnik :)",
                        forum3,
                        user5.getUsername()),
                new ForumCommentRequest(
                        "Viccet félretéve, lehet valaki nem túl jó matekból és azokat a könnyű kérdések is elgondolkodtatják, mások meg még a nehezekre is egyből rányomják a jó megoldást, nem vagyunk egyformák",
                        forum3,
                        user5.getUsername())
        );

        for (ForumCommentRequest comment : comments) {
            forumCommentService.createForumComment(comment);
        }
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
