package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCollectionException;
import edu.maszek.brainpowerquiz.model.entity.ForumEntity;
import edu.maszek.brainpowerquiz.model.property.UserPropertyEntity;
import edu.maszek.brainpowerquiz.model.request.ForumRequest;
import edu.maszek.brainpowerquiz.repository.ForumRepository;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ForumServiceImpl implements ForumService {
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConnectionUpdateServiceImpl connectionUpdateService;

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
    public void createForum(ForumRequest forumRequest) throws ConstraintViolationException, ForumCollectionException {
        if(forumRequest.getQuestion().length() > 250) throw new ForumCollectionException(ForumCollectionException.TooLongQuestion());

        UserPropertyEntity user = new UserPropertyEntity(userRepository.findByUsername(forumRequest.getAuthor()).get());
        ForumEntity forumEntity = new ForumEntity(forumRequest.getQuestion(), user);
        forumRepository.save(forumEntity);
        connectionUpdateService.updateUserConnection("forum", forumEntity, "create");
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
        if(forumOptional.isPresent()) {
            ForumEntity forumEntity = forumOptional.get();
            connectionUpdateService.updateUserConnection("forum", forumEntity, "delete");
            connectionUpdateService.updateForumCommentConnection("forum", forumEntity, "delete");
            forumRepository.deleteById(id);
        }
        else throw new ForumCollectionException(ForumCollectionException.NotFoundException(id));
    }
}
