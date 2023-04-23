package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.entity.ThemeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends MongoRepository<ThemeEntity, String> {
    Optional<ThemeEntity> findByText(String text);
}
