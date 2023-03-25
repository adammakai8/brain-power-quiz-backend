package edu.maszek.brainpowerquiz.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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
    private List<ThemeEntity> themes;
    @DBRef(lazy = true)
    private List<GameEntity> games;

    public void updateCollections(List<ThemeEntity> themeEntities, List<GameEntity> gameEntities) {
        // Ha egy adminfelületen módosíthatjuk a kapcsolatokat egyedek között, akkor felülírja a változtatás
        // az eredetit
        if(themeEntities != null) {
            themes = themeEntities;
        }
        // Ha pedig nem módosítunk (mint pl. a játék is), akkor mindig hozzáfűzünk a meglévőhöz
        if(gameEntities != null) {
            if(games == null) {
                games = new ArrayList<>();
            }
            games = Stream.concat(games.stream(), gameEntities.stream()).toList();
        }
    }
}
