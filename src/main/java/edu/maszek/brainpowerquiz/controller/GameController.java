package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.AnswerEntityCreationData;
import edu.maszek.brainpowerquiz.model.GameCreationData;
import edu.maszek.brainpowerquiz.model.entity.GameEntity;
import edu.maszek.brainpowerquiz.service.GameService;
import jakarta.validation.ConstraintViolationException;
import javassist.tools.web.BadHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@Slf4j
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<?> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameByID(@PathVariable("id") String _id) {
        try {
            return new ResponseEntity<>(gameService.getGameByID(_id), HttpStatus.OK);
        } catch (GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getGameByName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(gameService.getGameByName(name), HttpStatus.OK);
        } catch (GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/played")
    public ResponseEntity<?> getPlayedGameIds(final Principal principal) {
        return new ResponseEntity<>(gameService.getPlayedGamesByUser(principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/start/{gameId}")
    public ResponseEntity<?> startGame(final @PathVariable("gameId") String gameId, final Principal principal)
            throws UserCollectionException, GameCollectionException, BadHttpRequest {
        return new ResponseEntity<>(gameService.startGame(gameId, principal.getName()), HttpStatus.OK);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuiz(final @RequestBody AnswerEntityCreationData[] answers, final Principal principal)
            throws UserCollectionException {
        gameService.submitQuiz(answers, principal.getName());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestBody GameCreationData gameData) {
        try {
            final GameEntity game = gameService.createGame(gameData);
            final String msg = "Created game with id " + game.get_id();
            log.debug(msg);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateGame(@RequestBody GameEntity gameEntity) {
        try {
            gameService.updateGame(gameEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGameByID(@PathVariable("id") String id) {
        try {
            gameService.deleteGameByID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
