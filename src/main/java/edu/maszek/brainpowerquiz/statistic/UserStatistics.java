package edu.maszek.brainpowerquiz.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserStatistics {
    private Double averagePoints;
    private List<StatisticsGroup> answersByDifficulty;
    private List<StatisticsGroup> answersByTheme;
}
