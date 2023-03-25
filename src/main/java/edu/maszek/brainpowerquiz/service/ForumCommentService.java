package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.ForumCommentEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ForumCommentService {
    public List<ForumCommentEntity> getAllForumComments();
    public ForumCommentEntity getForumCommentByID(String id) throws ForumCommentCollectionException;
    public void createForumComment(ForumCommentEntity forumEntity) throws ConstraintViolationException;
    public void updateForumComment(ForumCommentEntity forumEntity) throws ForumCommentCollectionException;
    public void deleteForumCommentByID(String id) throws ForumCommentCollectionException;
}
