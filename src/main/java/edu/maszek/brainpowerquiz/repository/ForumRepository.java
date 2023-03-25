package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.ForumEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumRepository extends MongoRepository<ForumEntity, String> {

}
