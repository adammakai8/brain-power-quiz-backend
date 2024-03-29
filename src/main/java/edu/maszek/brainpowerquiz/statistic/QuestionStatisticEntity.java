package edu.maszek.brainpowerquiz.statistic;

import edu.maszek.brainpowerquiz.model.property.AnswerOption;
import edu.maszek.brainpowerquiz.model.property.ThemePropertyEntity;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="question")
public class QuestionStatisticEntity {
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
    private List<AnswerOption> answers;
    @NotNull
    @DBRef
    private List<ThemePropertyEntity> themes;
}
