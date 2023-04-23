package edu.maszek.brainpowerquiz.model.property;


import edu.maszek.brainpowerquiz.model.entity.ThemeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public ThemePropertyEntity(ThemeEntity themeEntity) {
        this._id = themeEntity.get_id();
        this.text = themeEntity.getText();
    }
}
