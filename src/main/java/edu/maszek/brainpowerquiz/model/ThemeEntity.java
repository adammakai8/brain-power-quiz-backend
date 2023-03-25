package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="theme")
public class ThemeEntity {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String text;
    @DBRef(lazy = true)
    private List<QuestionEntity> questions;
    @DBRef(lazy = true)
    private List<GameEntity> games;

    public void updateCollections(List<QuestionEntity> questionEntities, List<GameEntity> gameEntities) {
        // Ha egy adminfelületen módosíthatjuk a kapcsolatokat egyedek között, akkor felülírja a változtatás
        // az eredetit
        if(questionEntities != null) {
            questions = questionEntities;
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
