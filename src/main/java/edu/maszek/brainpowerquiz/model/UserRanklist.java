package edu.maszek.brainpowerquiz.model;

import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
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
    private UserPropertyEntity user;
    @Min(0)
    private Integer points;
}
