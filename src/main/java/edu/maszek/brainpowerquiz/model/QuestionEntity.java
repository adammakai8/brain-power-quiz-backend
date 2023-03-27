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
@Document(collection="question")
public class QuestionEntity {
    @Id
    private String _id;
    @NotNull
    private String text;
    @NotNull
    private Integer difficulty;
    @NotNull
    private List<Answer> answers;
    @DBRef(lazy = true)
    private List<ThemePropertyEntity> themes;
    @DBRef(lazy = true)
    private List<GamePropertyEntity> games;
}
