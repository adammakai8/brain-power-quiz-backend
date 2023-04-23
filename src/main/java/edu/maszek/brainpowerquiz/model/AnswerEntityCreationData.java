package edu.maszek.brainpowerquiz.model;

import edu.maszek.brainpowerquiz.model.property.GamePropertyEntity;
import edu.maszek.brainpowerquiz.statistic.QuestionStatisticEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerEntityCreationData {
    @Min(0)
    private Integer point = 0;
    @NotNull
    private GamePropertyEntity game;
    @NotNull
    private QuestionStatisticEntity question;
}
