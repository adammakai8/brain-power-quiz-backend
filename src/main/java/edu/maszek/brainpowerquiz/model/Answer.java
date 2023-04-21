package edu.maszek.brainpowerquiz.model;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @NotNull
    private String text;
    @NotNull
    private Boolean isCorrect;
}
