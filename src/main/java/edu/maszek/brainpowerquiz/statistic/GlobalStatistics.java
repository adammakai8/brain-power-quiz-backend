package edu.maszek.brainpowerquiz.statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GlobalStatistics {
    private List<ThemeCountInGames> popularThemes;
    private List<StatisticsGroup> statisticsByAge;
    private List<StatisticsGroup> statisticsByTheme;
}
