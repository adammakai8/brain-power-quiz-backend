package edu.maszek.brainpowerquiz.model;

import javax.validation.constraints.NotNull;

import lombok.*;
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

    public Integer getAge() {
        return LocalDate.now().getYear() - birthYear;
    }
}
