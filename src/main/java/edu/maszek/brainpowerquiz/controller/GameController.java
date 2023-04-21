package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.model.GameCreationData;
import edu.maszek.brainpowerquiz.model.GameEntity;
import edu.maszek.brainpowerquiz.service.GameService;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/games")
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<?> getAllGames() {
        List<GameEntity> games = gameService.getAllGames();
        if(games.size() > 0) return new ResponseEntity<>(games, HttpStatus.OK);
        else return new ResponseEntity<>("No games available", HttpStatus.NOT_FOUND);
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

    @PostMapping("/create")
    public ResponseEntity<?> createGame(@RequestBody GameCreationData gameData) {
        try {
            final GameEntity game = gameService.createGame(gameData);
            final String msg = "Created game with id " + game.get_id();
            log.debug(msg);
            return new ResponseEntity<>(msg, HttpStatus.OK);
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
            return new ResponseEntity<>("Updated game " + gameEntity.get_id(), HttpStatus.OK);
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
            return new ResponseEntity<>("Deleted game " + id, HttpStatus.OK);
        } catch (GameCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
