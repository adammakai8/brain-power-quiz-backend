package edu.maszek.brainpowerquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForumDTO {
    private String _id;
    @NotNull
    private String question;
    @NotNull
    private UserDTO author;
    @NotNull
    private List<ForumCommentDTO> comments;
}
