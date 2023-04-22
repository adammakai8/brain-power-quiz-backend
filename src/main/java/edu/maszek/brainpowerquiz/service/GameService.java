package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.GameCreationData;
import edu.maszek.brainpowerquiz.model.GameEntity;
import jakarta.validation.ConstraintViolationException;
import javassist.tools.web.BadHttpRequest;

import java.util.List;

public interface GameService {
    public List<GameEntity> getAllGames();
    public GameEntity getGameByID(String id) throws GameCollectionException;
    public GameEntity getGameByName(String name) throws GameCollectionException;
    GameEntity startGame(String gameId, String username) throws UserCollectionException, GameCollectionException, BadHttpRequest;
    public GameEntity createGame(GameCreationData gameEntity) throws ConstraintViolationException, GameCollectionException;
    public void updateGame(GameEntity gameEntity) throws GameCollectionException;
    public void deleteGameByID(String id) throws GameCollectionException;
}
