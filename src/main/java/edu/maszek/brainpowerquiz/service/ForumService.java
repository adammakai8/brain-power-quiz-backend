package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCollectionException;
import edu.maszek.brainpowerquiz.model.ForumEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface ForumService {
    public List<ForumEntity> getAllForums();
    public ForumEntity getForumByID(String id) throws ForumCollectionException;
    public void createForum(ForumEntity forumEntity) throws ConstraintViolationException;
    public void updateForum(ForumEntity forumEntity) throws ForumCollectionException;
    public void deleteForumByID(String id) throws ForumCollectionException;
}
