package edu.maszek.brainpowerquiz.model;

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
    private int id;
    @NonNull
    private String name;
    @NonNull
    private int maximalPlayerNumber;
    @NonNull
    private Date closeDate;
}
