package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.QuestionCollectionException;
import edu.maszek.brainpowerquiz.exception.ThemeCollectionException;
import edu.maszek.brainpowerquiz.model.QuestionDTO;
import edu.maszek.brainpowerquiz.model.QuestionEntity;
import edu.maszek.brainpowerquiz.service.QuestionService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions();
        if(questions.size() > 0) return new ResponseEntity<>(questions, HttpStatus.OK);
        else return new ResponseEntity<>("No questions available", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionByID(@PathVariable("id") String _id) {
        try {
            return new ResponseEntity<>(questionService.getQuestionByID(_id), HttpStatus.OK);
        } catch (QuestionCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questionDTO) {
        try {
            questionService.createQuestion(questionDTO);
            return new ResponseEntity<>("Created question with id " + questionDTO.get_id(), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (QuestionCollectionException | ThemeCollectionException | GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDTO questionDTO) {
        try {
            questionService.updateQuestion(questionDTO);
            return new ResponseEntity<>("Updated question " + questionDTO.get_id(), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (QuestionCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestionByID(@PathVariable("id") String id) {
        try {
            questionService.deleteQuestionByID(id);
            return new ResponseEntity<>("Deleted question " + id, HttpStatus.OK);
        } catch (QuestionCollectionException | ThemeCollectionException | GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
