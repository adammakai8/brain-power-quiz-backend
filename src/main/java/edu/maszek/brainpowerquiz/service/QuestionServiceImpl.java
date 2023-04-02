package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.QuestionCollectionException;
import edu.maszek.brainpowerquiz.model.Answer;
import edu.maszek.brainpowerquiz.model.QuestionEntity;
import edu.maszek.brainpowerquiz.repository.QuestionRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ConnectionUpdateServiceImpl connectionUpdateService;
    @Override
    public List<QuestionEntity> getAllQuestions() {
        List<QuestionEntity> questionsOptional = questionRepository.findAll();
        if(questionsOptional.size() > 0) return questionsOptional;
        else return new ArrayList<>();
    }

    @Override
    public QuestionEntity getQuestionByID(String id) throws QuestionCollectionException {
        Optional<QuestionEntity> question = questionRepository.findById(id);
        if(question.isPresent()) return question.get();
        else throw new QuestionCollectionException(QuestionCollectionException.NotFoundException(id));
    }

    @Override
    public void createQuestion(QuestionEntity questionEntity) throws ConstraintViolationException, QuestionCollectionException {
        if(checkCorrectAnswerCount(questionEntity)) {
            questionRepository.save(questionEntity);
            connectionUpdateService.updateThemeConnection( "question", questionEntity, "create");
            connectionUpdateService.updateGameConnection("question", questionEntity, "create");
        }
        else throw new QuestionCollectionException(QuestionCollectionException.MultipleCorrectAnswerNotAccepted());
    }

    @Override
    public void updateQuestion(QuestionEntity questionEntity) throws QuestionCollectionException {
        String questionID = questionEntity.get_id();
        Optional<QuestionEntity> questionOptional = questionRepository.findById(questionID);

        if(questionOptional.isPresent()) {
            if(checkCorrectAnswerCount(questionEntity)) {
                QuestionEntity questionToUpdate = questionOptional.get();

                questionToUpdate.setText(questionEntity.getText());
                questionToUpdate.setDifficulty(questionEntity.getDifficulty());
                questionToUpdate.setAnswers(questionEntity.getAnswers());
                questionToUpdate.setThemes(questionEntity.getThemes());
                questionToUpdate.setGames(questionEntity.getGames());
                connectionUpdateService.updateThemeConnection( "question", questionEntity, "update");
                connectionUpdateService.updateGameConnection("question", questionEntity, "update");
                questionRepository.save(questionToUpdate);
            } else throw new QuestionCollectionException(QuestionCollectionException.MultipleCorrectAnswerNotAccepted());
        } else throw new QuestionCollectionException(QuestionCollectionException.NotFoundException(questionID));
    }

    @Override
    public void deleteQuestionByID(String id) throws QuestionCollectionException {
        Optional<QuestionEntity> questionOptional = questionRepository.findById(id);
        if(questionOptional.isPresent()) {
            QuestionEntity questionEntity = questionOptional.get();
            connectionUpdateService.updateThemeConnection( "question", questionEntity, "delete");
            connectionUpdateService.updateGameConnection("question", questionEntity, "delete");
            questionRepository.deleteById(id);
        }
        else throw new QuestionCollectionException(QuestionCollectionException.NotFoundException(id));
    }

    private boolean checkCorrectAnswerCount(QuestionEntity questionEntity) {
        long count = questionEntity.getAnswers().stream().filter(Answer::getIsCorrect).count();
        return count <= 1;
    }
}
