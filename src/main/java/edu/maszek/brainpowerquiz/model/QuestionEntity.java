package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="question")
public class QuestionEntity {
    @Id
    private int id;
    @NonNull
    private String text;
    @NonNull
    private int difficulty;
    @NonNull
    private List<Answer> answers;
}
