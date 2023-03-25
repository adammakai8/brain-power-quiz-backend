package edu.maszek.brainpowerquiz.model;

import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import edu.maszek.brainpowerquiz.service.QuestionService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    @Autowired
    private QuestionService questionService;
    private String _id;
    @NotNull
    private String text;
    @NotNull
    private Integer difficulty;
    @NotNull
    private List<Answer> answers;
    private List<ThemeEntity> themes;
    private List<GameEntity> games;

    public QuestionDTO(QuestionEntity questionEntity) {
        this._id = questionEntity.get_id();
        this.text = questionEntity.getText();
        this.difficulty = questionEntity.getDifficulty();
        this.answers = questionEntity.getAnswers();
        this.themes = questionService.convertThemeIDsToEntities(questionEntity.getThemes());
        this.games = questionService.convertGameIDsToEntities(questionEntity.getGames());
    }
}
