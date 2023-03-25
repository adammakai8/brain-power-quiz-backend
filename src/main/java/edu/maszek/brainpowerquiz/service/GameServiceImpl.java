package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.exception.GameCollectionException;
import edu.maszek.brainpowerquiz.model.Answer;
import edu.maszek.brainpowerquiz.model.GameEntity;
import edu.maszek.brainpowerquiz.model.GameEntity;
import edu.maszek.brainpowerquiz.repository.GameRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameRepository gameRepository;
    @Override
    public List<GameEntity> getAllGames() {
        List<GameEntity> gamesOptional = gameRepository.findAll();
        if(gamesOptional.size() > 0) return gamesOptional;
        else return new ArrayList<>();
    }

    @Override
    public GameEntity getGameByID(String id) throws GameCollectionException {
        Optional<GameEntity> game = gameRepository.findById(id);
        if(game.isPresent()) return game.get();
        else throw new GameCollectionException(GameCollectionException.NotFoundException(id));
    }

    public GameEntity getGameByName(String name) throws GameCollectionException {
        Optional<GameEntity> game = gameRepository.findByName(name);
        if(game.isPresent()) return game.get();
        else throw new GameCollectionException(GameCollectionException.NotFoundByNameException(name));
    }

    @Override
    public void createGame(GameEntity gameEntity) throws ConstraintViolationException {
        gameRepository.save(gameEntity);
    }

    @Override
    public void updateGame(GameEntity gameEntity) throws GameCollectionException {
        String gameID = gameEntity.get_id();
        Optional<GameEntity> gameOptional = gameRepository.findById(gameID);

        if(gameOptional.isPresent()) {
            GameEntity gameToUpdate = gameOptional.get();

            gameToUpdate.setName(gameEntity.getName());
            gameToUpdate.setMaximalPlayerNumber(gameEntity.getMaximalPlayerNumber());
            gameToUpdate.setCloseDate(gameEntity.getCloseDate());
            gameRepository.save(gameToUpdate);
        } else throw new GameCollectionException(GameCollectionException.NotFoundException(gameID));
    }

    @Override
    public void deleteGameByID(String id) throws GameCollectionException {
        Optional<GameEntity> gameOptional = gameRepository.findById(id);
        if(gameOptional.isPresent()) gameRepository.deleteById(id);
        else throw new GameCollectionException(GameCollectionException.NotFoundException(id));
    }
}
