package edu.maszek.brainpowerquiz.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
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
    @Min(0)
    @Max(2)
    private Integer difficulty;
    @NotNull
    @Size(min = 4, max = 4)
    private List<Answer> answers;
    @DBRef
    private List<ThemePropertyEntity> themes;
    @DBRef
    private List<GamePropertyEntity> games;
}
