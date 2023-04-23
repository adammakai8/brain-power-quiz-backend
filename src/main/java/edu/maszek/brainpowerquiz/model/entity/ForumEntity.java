package edu.maszek.brainpowerquiz.model.entity;

import javax.validation.constraints.NotNull;

import edu.maszek.brainpowerquiz.model.property.ForumCommentPropertyEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
    @NotNull
    @DBRef
    private UserPropertyEntity author;
    @DBRef
    private List<ForumCommentPropertyEntity> comments;
}
