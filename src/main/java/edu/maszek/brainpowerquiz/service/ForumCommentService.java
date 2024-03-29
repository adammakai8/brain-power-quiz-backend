package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.entity.ForumCommentEntity;
import edu.maszek.brainpowerquiz.model.request.ForumCommentRequest;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ForumCommentService {
    public List<ForumCommentEntity> getAllForumComments();
    public ForumCommentEntity getForumCommentByID(String id) throws ForumCommentCollectionException;
    public void createForumComment(ForumCommentRequest forumCommentRequest) throws ConstraintViolationException, ForumCommentCollectionException;
    public void updateForumComment(ForumCommentEntity forumEntity) throws ForumCommentCollectionException;
    public void deleteForumCommentByID(String id) throws ForumCommentCollectionException;
}
