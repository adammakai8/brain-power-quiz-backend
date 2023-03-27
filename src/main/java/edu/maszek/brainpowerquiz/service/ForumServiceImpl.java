package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCollectionException;
import edu.maszek.brainpowerquiz.model.ForumEntity;
import edu.maszek.brainpowerquiz.repository.ForumRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Override
    public List<ForumEntity> getAllForums() {
        List<ForumEntity> forumsOptional = forumRepository.findAll();
        if(forumsOptional.size() > 0) return forumsOptional;
        else return new ArrayList<>();
    }

    @Override
    public ForumEntity getForumByID(String id) throws ForumCollectionException {
        Optional<ForumEntity> forum = forumRepository.findById(id);
        if(forum.isPresent()) return forum.get();
        else throw new ForumCollectionException(ForumCollectionException.NotFoundException(id));
    }

    @Override
    public void createForum(ForumEntity forumEntity) throws ConstraintViolationException {
        if (forumEntity.getComments() == null) {
            forumEntity.setComments(Collections.emptyList());
        }
        forumRepository.save(forumEntity);
    }

    @Override
    public void updateForum(ForumEntity forumEntity) throws ForumCollectionException {
        String forumID = forumEntity.get_id();
        Optional<ForumEntity> forumOptional = forumRepository.findById(forumID);

        if(forumOptional.isPresent()) {
            ForumEntity forumToUpdate = forumOptional.get();

            forumToUpdate.setQuestion(forumEntity.getQuestion());
            forumRepository.save(forumToUpdate);
        } else throw new ForumCollectionException(ForumCollectionException.NotFoundException(forumID));
    }

    @Override
    public void deleteForumByID(String id) throws ForumCollectionException {
        Optional<ForumEntity> forumOptional = forumRepository.findById(id);
        if(forumOptional.isPresent()) forumRepository.deleteById(id);
        else throw new ForumCollectionException(ForumCollectionException.NotFoundException(id));
    }
}
