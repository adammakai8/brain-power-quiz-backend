package edu.maszek.brainpowerquiz.model;

import edu.maszek.brainpowerquiz.role.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class UserEntity implements UserDetails {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String username;
    @NotNull
    private String email;
    @NotNull
    private Integer birthYear;
    @NotNull
    private String password;
    @DBRef
    private List<ForumPropertyEntity> forums;
    @DBRef
    private List<ForumCommentPropertyEntity> forumComments;
    @DBRef
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
