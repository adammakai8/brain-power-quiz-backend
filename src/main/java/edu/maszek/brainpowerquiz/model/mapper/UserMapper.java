package edu.maszek.brainpowerquiz.model.mapper;

import edu.maszek.brainpowerquiz.model.UserEntity;
import edu.maszek.brainpowerquiz.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @Mapping(target = "_id", source = "_id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "birthYear", source = "birthYear")
    @Mapping(target = "password", source = "password")
    public abstract UserDTO mapToDto(UserEntity entity);

    @Mapping(target = "_id", source = "_id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "birthYear", source = "birthYear")
    @Mapping(target = "password", source = "password")
    public abstract UserEntity mapToEntity(UserDTO dto);
}
