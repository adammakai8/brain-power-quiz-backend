package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="forumcomment")
public class ForumCommentEntity {
    @Id
    private int id;
    @NonNull
    private String text;
}
