package edu.maszek.brainpowerquiz.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @NonNull
    private String text;
    @NonNull
    private Boolean isCorrect;
}
