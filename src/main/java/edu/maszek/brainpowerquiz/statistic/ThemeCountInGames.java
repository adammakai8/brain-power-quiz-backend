package edu.maszek.brainpowerquiz.statistic;

import edu.maszek.brainpowerquiz.model.ThemeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThemeCountInGames {
    private String text;
    private Integer gameCount;

    public ThemeCountInGames(ThemeEntity themeEntity) {
        this.text = themeEntity.getText();
        this.gameCount = (themeEntity.getGames() == null ? 0 : themeEntity.getGames().size());
    }
}
