package edu.maszek.brainpowerquiz.model.mapper;

import edu.maszek.brainpowerquiz.model.ForumCommentEntity;
import edu.maszek.brainpowerquiz.model.dto.ForumCommentDTO;
import edu.maszek.brainpowerquiz.model.dto.ForumDTO;
import edu.maszek.brainpowerquiz.repository.ForumRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ForumCommentMapper {
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected ForumRepository forumRepository;

    protected ForumMapper forumMapper;
    public void setForumMapper(final ForumMapper forumMapper) {
        this.forumMapper = forumMapper;
    }

    @Mapping(target = "_id", source = "entity._id")
    @Mapping(target = "text", source = "entity.text")
    @Mapping(target = "author", expression = "java(userMapper.mapToDto(entity.getAuthor()))")
    @Mapping(target = "parent", source = "parentDto")
    public abstract ForumCommentDTO mapToDtoWithParent(ForumCommentEntity entity, ForumDTO parentDto);

    @Mapping(target = "_id", source = "entity._id")
    @Mapping(target = "text", source = "entity.text")
    @Mapping(target = "author", expression = "java(userMapper.mapToDto(entity.getAuthor()))")
    @Mapping(target = "parent", ignore = true) // TODO implement getting parent
    public abstract ForumCommentDTO mapToDto(ForumCommentEntity entity);

    @Mapping(target = "_id", source = "_id")
    @Mapping(target = "text", source = "text")
    @Mapping(target = "author", expression = "java(userMapper.mapToEntity(dto.getAuthor()))")
    public abstract ForumCommentEntity mapToEntity(ForumCommentDTO dto);
}
