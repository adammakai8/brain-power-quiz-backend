package edu.maszek.brainpowerquiz.model.mapper;

import edu.maszek.brainpowerquiz.model.ForumEntity;
import edu.maszek.brainpowerquiz.model.dto.ForumDTO;
import jakarta.annotation.PostConstruct;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class ForumMapper {
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected ForumCommentMapper commentMapper;
    @PostConstruct
    public void init() {
        commentMapper.setForumMapper(this);
    }

    @Mapping(target = "_id", source = "_id")
    @Mapping(target = "question", source = "question")
    @Mapping(target = "author", expression = "java(userMapper.mapToDto(entity.getAuthor()))")
    @Mapping(target = "comments", ignore = true)
    public abstract ForumDTO mapToDto(ForumEntity entity);

    @AfterMapping
    protected void mapComments(final ForumEntity entity, @MappingTarget final ForumDTO dto) {
        dto.setComments(entity.getComments().stream()
                .map(comment -> commentMapper.mapToDtoWithParent(comment, dto))
                .collect(Collectors.toList()));
    }
}
