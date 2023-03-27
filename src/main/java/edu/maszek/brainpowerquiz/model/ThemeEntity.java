package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    private List<QuestionPropertyEntity> questions;
    @DBRef(lazy = true)
    private List<GamePropertyEntity> games;
}
