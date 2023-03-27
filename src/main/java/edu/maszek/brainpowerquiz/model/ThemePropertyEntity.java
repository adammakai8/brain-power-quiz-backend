package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="theme")
public class ThemePropertyEntity {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String text;
}
