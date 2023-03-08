package edu.maszek.brainpowerquiz.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    @NonNull
    private String text;
    @NonNull
    private boolean isCorrect;
}
