package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.model.*;
import edu.maszek.brainpowerquiz.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionUpdateServiceImpl {
    @Autowired
    private ForumCommentRepository forumCommentRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private UserRepository userRepository;

    public void updateThemeConnection(
            String collectionThatChanged,
            Object object,
            String mode) {
        var repository = themeRepository;
        List<ThemeEntity> themes = repository.findAll();
        switch (collectionThatChanged) {
            case "question" -> updateThemeQuestionConnection((QuestionEntity) object, mode, repository, themes);
            case "game" -> updateThemeGameConnection((GameEntity) object, mode, repository, themes);
        }
    }

    public void updateQuestionConnection(
            String collectionThatChanged,
            Object object,
            String mode) {
        var repository = questionRepository;
        List<QuestionEntity> questions = repository.findAll();
        switch (collectionThatChanged) {
            case "theme" -> updateQuestionThemeConnection((ThemeEntity) object, mode, questions);
            case "game" -> updateQuestionGameConnection((GameEntity) object, mode, repository, questions);
        }
    }

    public void updateGameConnection(
            String collectionThatChanged,
            Object object,
            String mode) {
        var repository = gameRepository;
        List<GameEntity> games = repository.findAll();
        switch (collectionThatChanged) {
            case "theme" -> updateGameThemeConnection((ThemeEntity) object, mode, repository, games);
            case "question" -> updateGameQuestionConnection((QuestionEntity) object, mode, repository, games);
        }
    }
    
    public void updateForumConnection(
            String collectionThatChanged,
            Object object,
            String mode) {
        var repository = forumRepository;
        List<ForumEntity> forums = repository.findAll();
        switch (collectionThatChanged) {
            case "user" -> updateForumUserConnection((UserEntity) object, mode, repository, forums);
            case "forumcomment" -> updateForumForumCommentConnection((ForumCommentEntity) object, mode, repository, forums);
        }
    }

    public void updateUserConnection(
            String collectionThatChanged,
            Object object,
            String mode) {
        var repository = userRepository;
        List<UserEntity> users = repository.findAll();
        switch (collectionThatChanged) {
            case "forum" -> updateUserForumConnection((ForumEntity) object, mode, repository, users);
            case "forumcomment" -> updateUserForumCommentConnection((ForumCommentEntity) object, mode, repository, users);
        }
    }

    public void updateForumCommentConnection(
            String collectionThatChanged,
            Object object,
            String mode) {
        var repository = forumCommentRepository;
        List<ForumCommentEntity> forumcomments = repository.findAll();
        switch (collectionThatChanged) {
            case "forum" -> updateForumCommentForumConnection((ForumEntity) object, mode, repository, forumcomments);
            case "user" -> updateForumCommentUserConnection((UserEntity) object, mode, repository, forumcomments);
        }
    }

    private void updateForumCommentUserConnection(UserEntity object, String mode, ForumCommentRepository repository, List<ForumCommentEntity> forumcomments) {
        List<String> forum_commentIDs_from_user = object.getForumComments().stream().map(ForumCommentPropertyEntity::get_id).toList();
        forumcomments = forumcomments.stream().filter(forumcomment -> forum_commentIDs_from_user.contains(forumcomment.get_id())).collect(Collectors.toList());
        if (mode.equals("delete")) {
            for (ForumCommentEntity forumcomment : forumcomments) {
                forumcomment.setParent(null);
                repository.save(forumcomment);
            }
        }
    }

    private void updateForumCommentForumConnection(ForumEntity object, String mode, ForumCommentRepository repository, List<ForumCommentEntity> forumcomments) {
        List<String> forum_commentIDs_from_forum = object.getComments().stream().map(ForumCommentPropertyEntity::get_id).toList();
        forumcomments = forumcomments.stream().filter(forumcomment -> forum_commentIDs_from_forum.contains(forumcomment.get_id())).collect(Collectors.toList());
        if (mode.equals("delete")) {
            for (ForumCommentEntity forumcomment : forumcomments) {
                forumcomment.setParent(null);
                repository.save(forumcomment);
            }
        }
    }

    private void updateUserForumCommentConnection(ForumCommentEntity object, String mode, UserRepository repository, List<UserEntity> users) {
        String userID_from_forum_comment = object.getAuthor().get_id();
        users = users.stream().filter(user -> userID_from_forum_comment.equals(user.get_id())).collect(Collectors.toList());
        if (mode.equals("create")) {
            for (UserEntity user : users) {
                if (user.getForumComments() != null) {
                    user.getForumComments().add(new ForumCommentPropertyEntity(
                            object.get_id(),
                            object.getText()
                    ));
                } else {
                    user.setForumComments(new ArrayList<>() {{
                        add(new ForumCommentPropertyEntity(
                                object.get_id(),
                                object.getText()
                        ));
                    }});
                }
                repository.save(user);
            }
        }
        if (mode.equals("delete")) {
            for (UserEntity user : users) {
                user.getForumComments().removeIf(forumcomment -> forumcomment.get_id().equals(object.get_id()));
                repository.save(user);
            }
        }
    }

    private void updateUserForumConnection(ForumEntity object, String mode, UserRepository repository, List<UserEntity> users) {
        String userID_from_forum = object.getAuthor().get_id();
        users = users.stream().filter(user -> userID_from_forum.equals(user.get_id())).collect(Collectors.toList());
        if (mode.equals("create")) {
            for (UserEntity user : users) {
                if (user.getForums() != null) {
                    user.getForums().add(new ForumPropertyEntity(
                            object.get_id(),
                            object.getQuestion()
                    ));
                } else {
                    user.setForums(new ArrayList<>() {{
                        add(new ForumPropertyEntity(
                                object.get_id(),
                                object.getQuestion()
                        ));
                    }});
                }
                repository.save(user);
            }
        }
        if (mode.equals("delete")) {
            for (UserEntity user : users) {
                user.getForums().removeIf(forum -> forum.get_id().equals(object.get_id()));
                repository.save(user);
            }
        }
    }

    private void updateForumForumCommentConnection(ForumCommentEntity object, String mode, ForumRepository repository, List<ForumEntity> forums) {
        String forumID_from_forum_comments = object.getParent().get_id();
        forums = forums.stream().filter(forum -> forumID_from_forum_comments.equals(forum.get_id())).collect(Collectors.toList());
        if (mode.equals("create")) {
            for (ForumEntity forum : forums) {
                if (forum.getComments() != null) {
                    forum.getComments().add(new ForumCommentPropertyEntity(
                            object.get_id(),
                            object.getText()
                    ));
                } else {
                    forum.setComments(new ArrayList<>() {{
                        add(new ForumCommentPropertyEntity(
                                object.get_id(),
                                object.getText()
                        ));
                    }});
                }
                repository.save(forum);
            }
        }
        if (mode.equals("delete")) {
            for (ForumEntity forum : forums) {
                forum.getComments().removeIf(comment -> comment.get_id().equals(object.get_id()));
                repository.save(forum);
            }
        }
    }

    private void updateForumUserConnection(UserEntity object, String mode, ForumRepository repository, List<ForumEntity> forums) {
        List<String> forumIDs_from_user = object.getForums().stream().map(ForumPropertyEntity::get_id).toList();
        forums = forums.stream().filter(forum -> forumIDs_from_user.contains(forum.get_id())).collect(Collectors.toList());
        if (mode.equals("delete")) {
            for (ForumEntity forum : forums) {
                forum.setAuthor(null);
                repository.save(forum);
            }
        }
    }

    private static void updateThemeQuestionConnection(QuestionEntity object, String mode, ThemeRepository repository, List<ThemeEntity> themes) {
        List<String> themeIDs_from_question = object.getThemes().stream().map(ThemePropertyEntity::get_id).toList();
        themes = themes.stream().filter(theme -> themeIDs_from_question.contains(theme.get_id())).collect(Collectors.toList());
        List<ThemeEntity> previousQuestionThemes = new ArrayList<>();
        for (ThemeEntity themeEntity : repository.findAll()) {
            if (themeEntity.getQuestions() != null) {
                for (QuestionPropertyEntity question : themeEntity.getQuestions()) {
                    if (question.get_id().equals(object.get_id()))
                        previousQuestionThemes.add(themeEntity);
                }
            }
        }
        if (mode.equals("create")) {
            for (ThemeEntity theme : themes) {
                if (theme.getQuestions() != null) {
                    theme.getQuestions().add(new QuestionPropertyEntity(
                            object.get_id(),
                            object.getText(),
                            object.getDifficulty(),
                            object.getAnswers()
                    ));
                } else {
                    theme.setQuestions(new ArrayList<>() {{
                        add(new QuestionPropertyEntity(
                                object.get_id(),
                                object.getText(),
                                object.getDifficulty(),
                                object.getAnswers()
                        ));
                    }});
                }
                repository.save(theme);
            }
        }
        if (mode.equals("delete")) {
            for (ThemeEntity theme : themes) {
                theme.getQuestions().removeIf(question -> question.get_id().equals(object.get_id()));
                repository.save(theme);
            }
        }
        if (mode.equals("update")) {
            for (ThemeEntity theme : previousQuestionThemes) {
                if (!themes.contains(theme)) {
                    theme.getQuestions().removeIf(question -> question.get_id().equals(object.get_id()));
                    repository.save(theme);
                } else {
                    themes.remove(theme);
                }
            }
            for (ThemeEntity theme : themes) {
                if (theme.getQuestions() != null) {
                    theme.getQuestions().add(new QuestionPropertyEntity(
                            object.get_id(),
                            object.getText(),
                            object.getDifficulty(),
                            object.getAnswers()
                    ));
                } else {
                    theme.setQuestions(new ArrayList<>() {{
                        add(new QuestionPropertyEntity(
                                object.get_id(),
                                object.getText(),
                                object.getDifficulty(),
                                object.getAnswers()
                        ));
                    }});
                }
                repository.save(theme);
            }
        }
    }

    private static void updateThemeGameConnection(GameEntity object, String mode, ThemeRepository repository, List<ThemeEntity> themes) {
        List<String> themeIDs_from_game = object.getThemes().stream().map(ThemePropertyEntity::get_id).toList();
        themes = themes.stream().filter(theme -> themeIDs_from_game.contains(theme.get_id())).collect(Collectors.toList());
        List<ThemeEntity> previousGameThemes = new ArrayList<>();
        for (ThemeEntity themeEntity : repository.findAll()) {
            if (themeEntity.getQuestions() != null) {
                for (GamePropertyEntity game : themeEntity.getGames()) {
                    if (game.get_id().equals(object.get_id()))
                        previousGameThemes.add(themeEntity);
                }
            }
        }
        if (mode.equals("create")) {
            for (ThemeEntity theme : themes) {
                if (theme.getGames() != null) {
                    theme.getGames().add(new GamePropertyEntity(
                            object.get_id(),
                            object.getName(),
                            object.getMaximalPlayerNumber(),
                            object.getCloseDate()
                    ));
                } else {
                    theme.setGames(new ArrayList<>() {{
                        add(new GamePropertyEntity(
                                object.get_id(),
                                object.getName(),
                                object.getMaximalPlayerNumber(),
                                object.getCloseDate()
                        ));
                    }});
                }
                repository.save(theme);
            }
        }
        if (mode.equals("delete")) {
            for (ThemeEntity theme : themes) {
                theme.getGames().removeIf(game -> game.get_id().equals(object.get_id()));
                repository.save(theme);
            }
        }
        if (mode.equals("update")) {
            for (ThemeEntity theme : previousGameThemes) {
                if (!themes.contains(theme)) {
                    theme.getGames().removeIf(game -> game.get_id().equals(object.get_id()));
                    repository.save(theme);
                } else {
                    themes.remove(theme);
                }
            }
            for (ThemeEntity theme : themes) {
                if (theme.getGames() != null) {
                    theme.getGames().add(new GamePropertyEntity(
                            object.get_id(),
                            object.getName(),
                            object.getMaximalPlayerNumber(),
                            object.getCloseDate()
                    ));
                } else {
                    theme.setGames(new ArrayList<>() {{
                        add(new GamePropertyEntity(
                                object.get_id(),
                                object.getName(),
                                object.getMaximalPlayerNumber(),
                                object.getCloseDate()
                        ));
                    }});
                }
                repository.save(theme);
            }
        }
    }

    private static void updateQuestionGameConnection(GameEntity object, String mode, QuestionRepository repository, List<QuestionEntity> questions) {
        List<String> questionIDs_from_game = object.getQuestions().stream().map(QuestionPropertyEntity::get_id).toList();
        questions = questions.stream().filter(question -> questionIDs_from_game.contains(question.get_id())).collect(Collectors.toList());
        List<QuestionEntity> previousGameQuestions = new ArrayList<>();
        for (QuestionEntity questionEntity : repository.findAll()) {
            if (questionEntity.getGames() != null) {
                for (GamePropertyEntity game : questionEntity.getGames()) {
                    if (game.get_id().equals(object.get_id()))
                        previousGameQuestions.add(questionEntity);
                }
            }
        }
        if (mode.equals("create")) {
            for (QuestionEntity question : questions) {
                if (question.getGames() != null) {
                    question.getGames().add(new GamePropertyEntity(
                            object.get_id(),
                            object.getName(),
                            object.getMaximalPlayerNumber(),
                            object.getCloseDate()
                    ));
                } else {
                    question.setGames(new ArrayList<>() {{
                        add(new GamePropertyEntity(
                                object.get_id(),
                                object.getName(),
                                object.getMaximalPlayerNumber(),
                                object.getCloseDate()
                        ));
                    }});
                }
                repository.save(question);
            }
        }
        if (mode.equals("delete")) {
            for (QuestionEntity question : questions) {
                question.getGames().removeIf(game -> game.get_id().equals(object.get_id()));
                repository.save(question);
            }
        }
        if (mode.equals("update")) {
            for (QuestionEntity question : previousGameQuestions) {
                if (!questions.contains(question)) {
                    question.getGames().removeIf(game -> game.get_id().equals(object.get_id()));
                    repository.save(question);
                } else {
                    questions.remove(question);
                }
            }
            for (QuestionEntity question : questions) {
                if (question.getGames() != null) {
                    question.getGames().add(new GamePropertyEntity(
                            object.get_id(),
                            object.getName(),
                            object.getMaximalPlayerNumber(),
                            object.getCloseDate()
                    ));
                } else {
                    question.setGames(new ArrayList<>() {{
                        add(new GamePropertyEntity(
                                object.get_id(),
                                object.getName(),
                                object.getMaximalPlayerNumber(),
                                object.getCloseDate()
                        ));
                    }});
                }
                repository.save(question);
            }
        }
    }

    private void updateGameQuestionConnection(QuestionEntity object, String mode, GameRepository repository, List<GameEntity> games) {
        List<String> gameIDs_from_question = object.getGames().stream().map(GamePropertyEntity::get_id).toList();
        games = games.stream().filter(game -> gameIDs_from_question.contains(game.get_id())).collect(Collectors.toList());
        List<GameEntity> previosGameQuestions = new ArrayList<>();
        for (GameEntity gameEntity : repository.findAll()) {
            if (gameEntity.getQuestions() != null) {
                for (QuestionPropertyEntity question : gameEntity.getQuestions()) {
                    if (question.get_id().equals(object.get_id()))
                        previosGameQuestions.add(gameEntity);
                }
            }
        }
        if (mode.equals("create")) {
            for (GameEntity game : games) {
                if (game.getQuestions() != null) {
                    game.getQuestions().add(new QuestionPropertyEntity(
                            object.get_id(),
                            object.getText(),
                            object.getDifficulty(),
                            object.getAnswers()
                    ));
                } else {
                    game.setQuestions(new ArrayList<>() {{
                        add(new QuestionPropertyEntity(
                                object.get_id(),
                                object.getText(),
                                object.getDifficulty(),
                                object.getAnswers()
                        ));
                    }});
                }
                repository.save(game);
            }
        }
        if (mode.equals("delete")) {
            for (GameEntity game : games) {
                game.getQuestions().removeIf(question -> question.get_id().equals(object.get_id()));
                repository.save(game);
            }
        }
        if (mode.equals("update")) {
            for (GameEntity game : previosGameQuestions) {
                if (!games.contains(game)) {
                    game.getQuestions().removeIf(question -> question.get_id().equals(object.get_id()));
                    repository.save(game);
                } else {
                    games.remove(game);
                }
            }
            for (GameEntity game : games) {
                if (game.getQuestions() != null) {
                    game.getQuestions().add(new QuestionPropertyEntity(
                            object.get_id(),
                            object.getText(),
                            object.getDifficulty(),
                            object.getAnswers()
                    ));
                } else {
                    game.setQuestions(new ArrayList<>() {{
                        add(new QuestionPropertyEntity(
                                object.get_id(),
                                object.getText(),
                                object.getDifficulty(),
                                object.getAnswers()
                        ));
                    }});
                }
                repository.save(game);
            }
        }
    }

    private void updateQuestionThemeConnection(ThemeEntity object, String mode, List<QuestionEntity> questions) {
        List<String> questionIDs_from_theme = object.getQuestions().stream().map(QuestionPropertyEntity::get_id).toList();
        questions = questions.stream().filter(question -> questionIDs_from_theme.contains(question.get_id())).collect(Collectors.toList());
        List<QuestionEntity> previousThemeQuestions = new ArrayList<>();
        for (QuestionEntity questionEntity : questionRepository.findAll()) {
            if (questionEntity.getThemes() != null) {
                for (ThemePropertyEntity theme : questionEntity.getThemes()) {
                    if (theme.get_id().equals(object.get_id()))
                        previousThemeQuestions.add(questionEntity);
                }
            }
        }
        if (mode.equals("create")) {
            for (QuestionEntity question : questions) {
                if (question.getThemes() != null) {
                    question.getThemes().add(new ThemePropertyEntity(
                            object.get_id(),
                            object.getText()
                    ));
                } else {
                    question.setThemes(new ArrayList<>() {{
                        add(new ThemePropertyEntity(
                                object.get_id(),
                                object.getText()
                        ));
                    }});
                }
                questionRepository.save(question);
            }
        }
        if (mode.equals("delete")) {
            for (QuestionEntity question : questions) {
                question.getThemes().removeIf(theme -> theme.get_id().equals(object.get_id()));
                questionRepository.save(question);
            }
        }
        if (mode.equals("update")) {
            for (QuestionEntity question : previousThemeQuestions) {
                if (!questions.contains(question)) {
                    question.getThemes().removeIf(theme -> theme.get_id().equals(object.get_id()));
                    questionRepository.save(question);
                } else {
                    questions.remove(question);
                }
            }
            for (QuestionEntity question : questions) {
                if (question.getThemes() != null) {
                    question.getThemes().add(new ThemePropertyEntity(
                            object.get_id(),
                            object.getText()
                    ));
                } else {
                    question.setThemes(new ArrayList<>() {{
                        add(new ThemePropertyEntity(
                                object.get_id(),
                                object.getText()
                        ));
                    }});
                }
                questionRepository.save(question);
            }
        }
    }

    private void updateGameThemeConnection(ThemeEntity object, String mode, GameRepository repository, List<GameEntity> games) {
        List<String> gameIDs_from_theme = object.getGames().stream().map(GamePropertyEntity::get_id).toList();
        games = games.stream().filter(game -> gameIDs_from_theme.contains(game.get_id())).collect(Collectors.toList());
        List<GameEntity> previousThemeGames = new ArrayList<>();
        for (GameEntity gameEntity : repository.findAll()) {
            if (gameEntity.getThemes() != null) {
                for (ThemePropertyEntity theme : gameEntity.getThemes()) {
                    if (theme.get_id().equals(object.get_id()))
                        previousThemeGames.add(gameEntity);
                }
            }
        }
        if (mode.equals("create")) {
            for (GameEntity game : games) {
                if (game.getThemes() != null) {
                    game.getThemes().add(new ThemePropertyEntity(
                            object.get_id(),
                            object.getText()
                    ));
                } else {
                    game.setThemes(new ArrayList<>() {{
                        add(new ThemePropertyEntity(
                                object.get_id(),
                                object.getText()
                        ));
                    }});
                }
                repository.save(game);
            }
        }
        if (mode.equals("delete")) {
            for (GameEntity game : games) {
                game.getThemes().removeIf(theme -> theme.get_id().equals(object.get_id()));
                repository.save(game);
            }
        }
        if (mode.equals("update")) {
            for (GameEntity game : previousThemeGames) {
                if (!games.contains(game)) {
                    game.getThemes().removeIf(theme -> theme.get_id().equals(object.get_id()));
                    repository.save(game);
                } else {
                    games.remove(game);
                }
            }
            for (GameEntity game : games) {
                if (game.getThemes() != null) {
                    game.getThemes().add(new ThemePropertyEntity(
                            object.get_id(),
                            object.getText()
                    ));
                } else {
                    game.setThemes(new ArrayList<>() {{
                        add(new ThemePropertyEntity(
                                object.get_id(),
                                object.getText()
                        ));
                    }});
                }
                repository.save(game);
            }
        }
    }
}
