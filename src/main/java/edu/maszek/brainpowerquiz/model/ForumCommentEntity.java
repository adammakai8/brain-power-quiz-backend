package edu.maszek.brainpowerquiz.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="forumcomment")
public class ForumCommentEntity {
    @Id
    private String _id;
    @NotNull
    private String text;
    @NotNull
    @DBRef
    private ForumPropertyEntity parent;
    @NotNull
    @DBRef
    private UserPropertyEntity author;
}
