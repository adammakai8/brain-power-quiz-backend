package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class UserEntity {
    @Id
    private String name;
    @NonNull
    private String email;
    @NonNull
    private int birthYear;
    @NonNull
    private String password;
}
