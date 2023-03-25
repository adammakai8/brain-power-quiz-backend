package edu.maszek.brainpowerquiz.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
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
    private List<String> themes;
    private List<String> games;

    public QuestionEntity(QuestionDTO questionDTO) {
        this._id = questionDTO.get_id();
        this.text = questionDTO.getText();
        this.difficulty = questionDTO.getDifficulty();
        this.answers = questionDTO.getAnswers();
        this.themes = new ArrayList<>(questionDTO.getThemes().stream().map(ThemeEntity::get_id).toList());
        this.games = new ArrayList<>(questionDTO.getGames().stream().map(GameEntity::get_id).toList());
    }
}
