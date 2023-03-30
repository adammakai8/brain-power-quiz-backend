package edu.maszek.brainpowerquiz.role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "role")
public class Role {
    @Id
    private String _id;
    @Indexed(unique = true)
    private String name;
}
