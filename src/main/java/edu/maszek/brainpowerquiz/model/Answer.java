package edu.maszek.brainpowerquiz.model;

import lombok.*;

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
