package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.ForumCommentEntity;
import edu.maszek.brainpowerquiz.model.dto.ForumCommentDTO;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ForumCommentService {
    public List<ForumCommentDTO> getAllForumComments();
    public ForumCommentDTO getForumCommentByID(String id) throws ForumCommentCollectionException;
    public String createForumComment(ForumCommentDTO forumEntity) throws ConstraintViolationException;
    public void updateForumComment(ForumCommentEntity forumEntity) throws ForumCommentCollectionException;
    public void deleteForumCommentByID(String id) throws ForumCommentCollectionException;
}
