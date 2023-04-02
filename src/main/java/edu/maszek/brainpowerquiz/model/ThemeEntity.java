package edu.maszek.brainpowerquiz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class ThemeEntity
{
    @Id
    private String _id;
    @Indexed(unique = true)
    private String text;
    @DBRef
    private List<QuestionPropertyEntity> questions;
    @DBRef
    private List<GamePropertyEntity> games;
}
