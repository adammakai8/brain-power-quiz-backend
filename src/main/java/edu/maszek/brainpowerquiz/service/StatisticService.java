package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.statistic.GlobalStatistics;
import edu.maszek.brainpowerquiz.statistic.ThemeCountInGames;
import edu.maszek.brainpowerquiz.statistic.UserStatistics;

import java.util.List;

public interface StatisticService {
    public List<ThemeCountInGames> getThemesByPopularity();
    GlobalStatistics getGlobalStatistics();
    public List<ThemeCountInGames> getUserFavouriteThemes(String username);
    UserStatistics getUserStatistics(String id);
}
