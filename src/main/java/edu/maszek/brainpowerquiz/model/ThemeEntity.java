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
    private List<QuestionDTO> questions;
    @DBRef(lazy = true)
    private List<GameDTO> games;
}
