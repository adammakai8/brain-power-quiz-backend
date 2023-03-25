package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.ForumCommentEntity;
import edu.maszek.brainpowerquiz.repository.ForumCommentRepository;
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
    public void createForumComment(ForumCommentEntity forumCommentEntity) throws ConstraintViolationException {
        forumCommentRepository.save(forumCommentEntity);
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
        if(forumCommentOptional.isPresent()) forumCommentRepository.deleteById(id);
        else throw new ForumCommentCollectionException(ForumCommentCollectionException.NotFoundException(id));
    }
}
