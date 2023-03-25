package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.model.GameEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface GameService {
    public List<GameEntity> getAllGames();
    public GameEntity getGameByID(String id) throws GameCollectionException;
    public GameEntity getGameByName(String name) throws GameCollectionException;
    public void createGame(GameEntity gameEntity) throws ConstraintViolationException, GameCollectionException;
    public void updateGame(GameEntity gameEntity) throws GameCollectionException;
    public void deleteGameByID(String id) throws GameCollectionException;
}
