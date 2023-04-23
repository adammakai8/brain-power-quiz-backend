package edu.maszek.brainpowerquiz.repository;

import edu.maszek.brainpowerquiz.model.entity.ForumCommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumCommentRepository extends MongoRepository<ForumCommentEntity, String> {

}
