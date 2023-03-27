package edu.maszek.brainpowerquiz.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="forum")
public class ForumEntity {
    @Id
    private String _id;
    @NotNull
    private String question;
    @DBRef(lazy = true)
    private UserEntity author;
    @DBRef(lazy = true)
    private List<ForumCommentEntity> comments;
}
