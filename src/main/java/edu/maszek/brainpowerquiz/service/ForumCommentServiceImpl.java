package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.ForumCommentEntity;
import edu.maszek.brainpowerquiz.model.dto.ForumCommentDTO;
import edu.maszek.brainpowerquiz.model.mapper.ForumCommentMapper;
import edu.maszek.brainpowerquiz.repository.ForumCommentRepository;
import edu.maszek.brainpowerquiz.repository.ForumRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ForumCommentServiceImpl implements ForumCommentService {
    @Autowired
    private ForumCommentRepository forumCommentRepository;
    @Autowired
    private ForumRepository forumRepository;
    @Autowired
    private ForumCommentMapper forumCommentMapper;

    @Override
    public List<ForumCommentDTO> getAllForumComments() {
        return forumCommentRepository.findAll().stream().map(forumCommentMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    public ForumCommentDTO getForumCommentByID(String id) throws ForumCommentCollectionException {
        Optional<ForumCommentEntity> forumComment = forumCommentRepository.findById(id);
        if(forumComment.isPresent()) {
            return forumCommentMapper.mapToDto(forumComment.get());
        } else {
            throw new ForumCommentCollectionException(ForumCommentCollectionException.NotFoundException(id));
        }
    }

    @Override
    public String createForumComment(final ForumCommentDTO forumCommentToSave) throws ConstraintViolationException {
        final String[] createdId = new String[1];
        forumRepository.findById(forumCommentToSave.getParent().get_id()).ifPresentOrElse(
                parentForum -> {
                    final ForumCommentEntity entity = forumCommentRepository
                            .save(forumCommentMapper.mapToEntity(forumCommentToSave));
                    parentForum.getComments().add(entity);
                    forumRepository.save(parentForum);
                    createdId[0] = entity.get_id();
                },
                () -> {
                    throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "Parent forum not found!");
                }
        );
        return createdId[0];
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
