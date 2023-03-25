package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.QuestionCollectionException;
import edu.maszek.brainpowerquiz.exception.ThemeCollectionException;
import edu.maszek.brainpowerquiz.model.GameEntity;
import edu.maszek.brainpowerquiz.model.QuestionDTO;
import edu.maszek.brainpowerquiz.model.QuestionEntity;
import edu.maszek.brainpowerquiz.model.ThemeEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface QuestionService {
    public List<QuestionDTO> getAllQuestions();
    public QuestionDTO getQuestionByID(String id) throws QuestionCollectionException;
    public List<ThemeEntity> convertThemeIDsToEntities(List<String> themes);
    public List<GameEntity> convertGameIDsToEntities(List<String> games);
    public void createQuestion(QuestionDTO questionDTO) throws ConstraintViolationException, QuestionCollectionException, ThemeCollectionException, GameCollectionException;
    public void updateQuestion(QuestionDTO questionDTO) throws QuestionCollectionException;
    public void deleteQuestionByID(String id) throws QuestionCollectionException, ThemeCollectionException, GameCollectionException;
}
