package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.GameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends MongoRepository<GameEntity, String> {
    Optional<GameEntity> findByName(String name);
}
