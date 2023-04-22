package edu.maszek.brainpowerquiz.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
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
@Builder
@Document(collection="game")
public class GameEntity {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String name;
    @NotNull
    private Integer maximalPlayerNumber;
    @NotNull
    private Date closeDate;
    @NotNull
    @DBRef
    private List<ThemePropertyEntity> themes;
    @NotNull
    @DBRef
    private List<UserPropertyEntity> players;
    @NotNull
    @DBRef
    private List<QuestionPropertyEntity> questions;
}
