package edu.maszek.brainpowerquiz.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticsGroup {
    String groupName;
    Integer averagePoints;
    Double correctAnswerRatioEasy;
    Double correctAnswerRatioMedium;
    Double correctAnswerRatioHard;
    Double correctAnswerRatioAll;
}
