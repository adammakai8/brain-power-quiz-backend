package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ThemeCollectionException;
import edu.maszek.brainpowerquiz.model.ThemeEntity;
import edu.maszek.brainpowerquiz.repository.ThemeRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ThemeServiceImpl implements ThemeService{
    @Autowired
    private ThemeRepository themeRepository;
    @Override
    public List<ThemeEntity> getAllThemes() {
        List<ThemeEntity> themesOptional = themeRepository.findAll();
        if(themesOptional.size() > 0) return themesOptional;
        else return new ArrayList<>();
    }

    @Override
    public ThemeEntity getThemeByID(String id) throws ThemeCollectionException {
        Optional<ThemeEntity> theme = themeRepository.findById(id);
        if(theme.isPresent()) return theme.get();
        else throw new ThemeCollectionException(ThemeCollectionException.NotFoundException(id));
    }

    @Override
    public ThemeEntity getThemeByText(String text) throws ThemeCollectionException {
        Optional<ThemeEntity> theme = themeRepository.findByText(text);
        if(theme.isPresent()) return theme.get();
        else throw new ThemeCollectionException(ThemeCollectionException.NotFoundByTextException(text));
    }

    @Override
    public void createTheme(ThemeEntity themeEntity) throws ConstraintViolationException, ThemeCollectionException {
        String text = themeEntity.getText();
        Optional<ThemeEntity> themeOptional = themeRepository.findByText(text);
        if(themeOptional.isPresent()) throw new ThemeCollectionException(ThemeCollectionException.AlreadyExists(text));
        else themeRepository.save(themeEntity);
    }

    @Override
    public void updateTheme(ThemeEntity themeEntity) throws ThemeCollectionException {
        String themeID = themeEntity.get_id();
        Optional<ThemeEntity> themeOptional = themeRepository.findById(themeID);

        if(themeOptional.isPresent()) {
            ThemeEntity themeToUpdate = themeOptional.get();

            themeToUpdate.setText(themeEntity.getText());
            themeToUpdate.setQuestions(themeEntity.getQuestions());
            themeToUpdate.setGames(themeEntity.getGames());
            themeRepository.save(themeToUpdate);
        } else throw new ThemeCollectionException(ThemeCollectionException.NotFoundException(themeID));
    }

    @Override
    public void deleteThemeByID(String id) throws ThemeCollectionException {
        Optional<ThemeEntity> themeOptional = themeRepository.findById(id);
        if(themeOptional.isPresent()) themeRepository.deleteById(id);
        else throw new ThemeCollectionException(ThemeCollectionException.NotFoundException(id));
    }
}
