package edu.maszek.brainpowerquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForumCommentDTO {
    private String _id;
    @NotNull
    private String text;
    @NotNull
    private UserDTO author;
    @NotNull
    private ForumDTO parent;
}
