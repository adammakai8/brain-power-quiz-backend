package edu.maszek.brainpowerquiz.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="game")
public class GameDTO {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String name;
    @NotNull
    private Integer maximalPlayerNumber;
    @NotNull
    private Date closeDate;
    private List<ThemeEntity> themes;
    private List<QuestionEntity> questions;
}
