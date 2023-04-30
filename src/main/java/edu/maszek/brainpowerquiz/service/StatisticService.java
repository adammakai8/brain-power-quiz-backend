package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.model.UserRanklist;
import edu.maszek.brainpowerquiz.statistic.GlobalStatistics;
import edu.maszek.brainpowerquiz.statistic.ThemeCountInGames;
import edu.maszek.brainpowerquiz.statistic.UserStatistics;

import java.util.List;

public interface StatisticService {
    public List<ThemeCountInGames> getThemesByPopularity();
    GlobalStatistics getGlobalStatistics();
    List<UserRanklist> getRanklist();
    List<UserRanklist> getGameResults(String gameId);
    public List<ThemeCountInGames> getUserFavouriteThemes(String username);
    UserStatistics getUserStatistics(String id);
}
