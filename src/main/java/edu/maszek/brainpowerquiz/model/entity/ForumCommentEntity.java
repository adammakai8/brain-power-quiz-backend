package edu.maszek.brainpowerquiz.model.entity;

import javax.validation.constraints.NotNull;

import edu.maszek.brainpowerquiz.model.property.ForumPropertyEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
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

    public ForumCommentEntity(String text, ForumEntity forum, UserPropertyEntity user) {
        this.text = text;
        this.parent = new ForumPropertyEntity(forum);
        this.author = user;
    }
}
