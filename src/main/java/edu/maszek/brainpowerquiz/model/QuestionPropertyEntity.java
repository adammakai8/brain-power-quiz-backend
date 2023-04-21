package edu.maszek.brainpowerquiz.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="question")
public class QuestionPropertyEntity {
    @Id
    private String _id;
    @NotNull
    private String text;
    @NotNull
    private Integer difficulty;
    @NotNull
    private List<Answer> answers;
}
