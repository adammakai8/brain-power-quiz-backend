package edu.maszek.brainpowerquiz.model;

import edu.maszek.brainpowerquiz.statistic.QuestionStatisticEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="answer")
public class AnswerEntity {
    @Id
    private String _id;
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
