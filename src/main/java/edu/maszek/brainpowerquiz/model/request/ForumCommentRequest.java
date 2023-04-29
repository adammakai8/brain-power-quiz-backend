package edu.maszek.brainpowerquiz.model.request;

import edu.maszek.brainpowerquiz.model.entity.ForumEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ForumCommentRequest {
    private String text;
    private ForumEntity parent;
    private String author;
}
