package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.UserEntity;
import jakarta.validation.ConstraintViolationException;

import java.util.List;

public interface UserService {
    public void registerUser(UserEntity userEntity) throws ConstraintViolationException, UserCollectionException;
    public List<UserEntity> getAllUsers();
}
