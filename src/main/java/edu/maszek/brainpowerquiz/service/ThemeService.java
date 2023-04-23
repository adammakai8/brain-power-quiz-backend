package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ThemeCollectionException;
import edu.maszek.brainpowerquiz.model.entity.ThemeEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ThemeService {
    public List<ThemeEntity> getAllThemes();
    public ThemeEntity getThemeByID(String id) throws ThemeCollectionException;
    public ThemeEntity getThemeByText(String text) throws ThemeCollectionException;
    public void createTheme(ThemeEntity themeEntity) throws ConstraintViolationException, ThemeCollectionException;
    public void updateTheme(ThemeEntity themeEntity) throws ThemeCollectionException;
    public void deleteThemeByID(String id) throws ThemeCollectionException;
}
