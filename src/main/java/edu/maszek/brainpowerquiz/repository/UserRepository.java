package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

}
