package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.entity.ForumCommentEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import edu.maszek.brainpowerquiz.model.request.ForumCommentRequest;
import edu.maszek.brainpowerquiz.repository.ForumCommentRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {
    @Autowired
    private ForumCommentRepository forumCommentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConnectionUpdateServiceImpl connectionUpdateService;

    @Override
    public List<ForumCommentEntity> getAllForumComments() {
        List<ForumCommentEntity> forumCommentsOptional = forumCommentRepository.findAll();
        if(forumCommentsOptional.size() > 0) return forumCommentsOptional;
        else return new ArrayList<>();
    }

    @Override
    public ForumCommentEntity getForumCommentByID(String id) throws ForumCommentCollectionException {
        Optional<ForumCommentEntity> forumComment = forumCommentRepository.findById(id);
        if(forumComment.isPresent()) return forumComment.get();
        else throw new ForumCommentCollectionException(ForumCommentCollectionException.NotFoundException(id));
    }

    @Override
    public void createForumComment(ForumCommentRequest forumCommentRequest) throws ConstraintViolationException, ForumCommentCollectionException {
        if(forumCommentRequest.getText().length() > 250)
            throw new ForumCommentCollectionException(ForumCommentCollectionException.TooLongAnswer());

        UserPropertyEntity user = new UserPropertyEntity(userRepository.findByUsername(forumCommentRequest.getAuthor()).get());
        ForumCommentEntity forumCommentEntity =
                new ForumCommentEntity(
                        forumCommentRequest.getText(),
                        forumCommentRequest.getParent(),
                        user);
        forumCommentRepository.save(forumCommentEntity);
        connectionUpdateService.updateForumConnection("forumcomment", forumCommentEntity, "create");
        connectionUpdateService.updateUserConnection("forumcomment", forumCommentEntity, "create");
    }

    @Override
    public void updateForumComment(ForumCommentEntity forumCommentEntity) throws ForumCommentCollectionException {
        String forumCommentID = forumCommentEntity.get_id();
        Optional<ForumCommentEntity> forumCommentOptional = forumCommentRepository.findById(forumCommentID);

        if(forumCommentOptional.isPresent()) {
            ForumCommentEntity forumCommentToUpdate = forumCommentOptional.get();

            forumCommentToUpdate.setText(forumCommentEntity.getText());
            forumCommentRepository.save(forumCommentToUpdate);
        } else throw new ForumCommentCollectionException(ForumCommentCollectionException.NotFoundException(forumCommentID));
    }

    @Override
    public void deleteForumCommentByID(String id) throws ForumCommentCollectionException {
        Optional<ForumCommentEntity> forumCommentOptional = forumCommentRepository.findById(id);
        if(forumCommentOptional.isPresent()) {
            ForumCommentEntity forumCommentEntity = forumCommentOptional.get();
            connectionUpdateService.updateForumConnection("forumcomment", forumCommentEntity, "delete");
            connectionUpdateService.updateUserConnection("forumcomment", forumCommentEntity, "delete");
            forumCommentRepository.deleteById(id);
        }
        else throw new ForumCommentCollectionException(ForumCommentCollectionException.NotFoundException(id));
    }
}
