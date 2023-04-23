package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.AnswerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AnswerRepository extends MongoRepository<AnswerEntity, String> {

}
