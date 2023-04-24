package edu.maszek.brainpowerquiz.model;

import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRanklist {
    @NotNull
    private UserEntity user;
    @Min(0)
    private Integer points;
}
