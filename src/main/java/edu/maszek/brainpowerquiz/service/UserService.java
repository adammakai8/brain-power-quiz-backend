package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserEntity> getAllUsers();
    public UserEntity getUserByName(String name) throws UserCollectionException;
    public void updateUser(String previousName, UserEntity userEntity) throws UserCollectionException;
    public void deleteUser(String name) throws UserCollectionException;
}
