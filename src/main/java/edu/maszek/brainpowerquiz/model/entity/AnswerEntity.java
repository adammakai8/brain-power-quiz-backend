package edu.maszek.brainpowerquiz.model.entity;

import edu.maszek.brainpowerquiz.model.property.GamePropertyEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import edu.maszek.brainpowerquiz.statistic.QuestionStatisticEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="answer")
public class AnswerEntity {
    @Id
    private String _id;
    @Min(0)
    private Integer point = 0;
    @NotNull
    @DBRef
    private UserPropertyEntity user;
    @NotNull
    @DBRef
    private GamePropertyEntity game;
    @NotNull
    @DBRef
    private QuestionStatisticEntity question;
}
