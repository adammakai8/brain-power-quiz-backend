package edu.maszek.brainpowerquiz.model.property;

import javax.validation.constraints.NotNull;


import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="user")
public class UserPropertyEntity {
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

    public UserPropertyEntity(UserEntity user) {
        this._id = user.get_id();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.birthYear = user.getBirthYear();
        this.password = user.getPassword();
    }

    public Integer getAge() {
        return LocalDate.now().getYear() - birthYear;
    }
}
