package edu.maszek.brainpowerquiz.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import edu.maszek.brainpowerquiz.model.entity.QuestionEntity;
import edu.maszek.brainpowerquiz.model.property.QuestionPropertyEntity;
import edu.maszek.brainpowerquiz.model.entity.ThemeEntity;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import edu.maszek.brainpowerquiz.repository.RoleRepository;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import edu.maszek.brainpowerquiz.role.Role;
import edu.maszek.brainpowerquiz.util.QuestionObjectMother;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
            final ThemeRepository themeRepository,
            final UserRepository userRepository,
            final RoleRepository roleRepository,
            final QuestionRepository questionRepository
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

    private Role createRole(String name) {
        final Role role = new Role();
        role.setName(name);
        return role;
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
