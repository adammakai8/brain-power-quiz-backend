package edu.maszek.brainpowerquiz.model.property;

import javax.validation.constraints.NotNull;

import edu.maszek.brainpowerquiz.model.entity.ForumEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="forum")
public class ForumPropertyEntity {
    @Id
    private String _id;
    @NotNull
    private String question;

    public ForumPropertyEntity(ForumEntity forumEntity) {
        this._id = forumEntity.get_id();
        this.question = forumEntity.getQuestion();
    }
}
