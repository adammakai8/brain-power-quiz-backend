package edu.maszek.brainpowerquiz.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ForumRequest {
    private String question;
    private String author;
}
