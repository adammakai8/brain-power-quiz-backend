package edu.maszek.brainpowerquiz.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsGroup {
    String groupName;
    Double averagePoints;
    Double correctAnswerRatioEasy;
    Double correctAnswerRatioMedium;
    Double correctAnswerRatioHard;
    Double correctAnswerRatioAll;

    public StatisticsGroup(
            int currIndex,
            int range,
            Double averagePoints,
            Double correctAnswerRatioEasy,
            Double correctAnswerRatioMedium,
            Double correctAnswerRatioHard,
            Double correctAnswerRatioAll) {
        this(Math.max(1, currIndex * range) + "-" + (currIndex + 1) * range,
                averagePoints,
                correctAnswerRatioEasy,
                correctAnswerRatioMedium,
                correctAnswerRatioHard,
                correctAnswerRatioAll);
    }
}
