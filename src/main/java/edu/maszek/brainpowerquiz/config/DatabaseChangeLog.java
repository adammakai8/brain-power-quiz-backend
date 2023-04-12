package edu.maszek.brainpowerquiz.config;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import edu.maszek.brainpowerquiz.model.ThemeEntity;
import edu.maszek.brainpowerquiz.model.UserEntity;
import edu.maszek.brainpowerquiz.repository.RoleRepository;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import edu.maszek.brainpowerquiz.role.Role;

import java.util.List;

@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "initializeData", author = "adamax")
    public void initializeData(
            ThemeRepository themeRepository,
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        themeRepository.insert(List.of(
                createTheme("Matematika"),
                createTheme("Történelem"),
                createTheme("Kémia"),
                createTheme("Biológia"),
                createTheme("Földrajz"),
                createTheme("Sport"),
                createTheme("Irodalom")
        ));
        roleRepository.insert(List.of(
                createRole("USER"),
                createRole("ADMIN")
        ));
        userRepository.insert(createAdmin(roleRepository.findByName("ADMIN")));
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
        return theme;
    }
}
