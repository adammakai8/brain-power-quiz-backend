package edu.maszek.brainpowerquiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class GameCreationData {
    @NotNull
    private String name;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer maximalPlayerNumber;
    @NotNull
    private Date closeDate;
    @NotNull
    @Size(min = 1)
    private List<ThemePropertyEntity> themes;
    @NotNull
    @Max(7)
    @Positive
    private Integer easyQuestions;
    @NotNull
    @Max(7)
    @Positive
    private Integer mediumQuestions;
    @NotNull
    @Max(7)
    @Positive
    private Integer hardQuestions;
}
