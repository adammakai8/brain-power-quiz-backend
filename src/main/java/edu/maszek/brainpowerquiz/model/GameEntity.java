package edu.maszek.brainpowerquiz.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="game")
public class GameEntity {
    @Id
    private String _id;
    @NotNull
    private String name;
    @NotNull
    private Integer maximalPlayerNumber;
    @NotNull
    private Date closeDate;
}
