package edu.maszek.brainpowerquiz.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String _id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private Integer birthYear;
    @NotNull
    private String password;
}
