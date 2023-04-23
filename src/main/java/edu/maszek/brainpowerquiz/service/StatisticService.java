package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.model.AnswerEntity;
import edu.maszek.brainpowerquiz.statistic.ThemeCountInGames;

import java.util.List;

public interface StatisticService {
    public List<ThemeCountInGames> getThemesByPopularity();
    public List<ThemeCountInGames> getUserFavouriteThemes(String username);
    public List<AnswerEntity> getAllAnswers();
}
