package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.entity.QuestionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {

}
