package edu.maszek.brainpowerquiz.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="user")
public class UserEntity {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String name;
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
}
