package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.QuestionCollectionException;
import edu.maszek.brainpowerquiz.model.QuestionEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface QuestionService {
    public List<QuestionEntity> getAllQuestions();
    public QuestionEntity getQuestionByID(String id) throws QuestionCollectionException;
    public void createQuestion(QuestionEntity questionEntity) throws ConstraintViolationException, QuestionCollectionException;
    public void updateQuestion(QuestionEntity questionEntity) throws QuestionCollectionException;
    public void deleteQuestionByID(String id) throws QuestionCollectionException;
}
