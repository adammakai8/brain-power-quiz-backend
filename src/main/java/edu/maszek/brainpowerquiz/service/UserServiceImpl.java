package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.UserEntity;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> usersOptional = userRepository.findAll();
        if(usersOptional.size() > 0) return usersOptional;
        else return new ArrayList<>();
    }

    @Override
    public UserEntity getUserByName(String name) throws UserCollectionException {
        Optional<UserEntity> user = userRepository.findByName(name);
        if(user.isPresent()) return user.get();
        else throw new UserCollectionException(UserCollectionException.NotFoundException(name));
    }

    @Override
    public void registerUser(UserEntity userEntity) throws ConstraintViolationException, UserCollectionException {
        String name = userEntity.getName();
        Optional<UserEntity> userOptional = userRepository.findByName(name);
        if(userOptional.isPresent()) throw new UserCollectionException(UserCollectionException.AlreadyExists(name));
        else userRepository.save(userEntity);
    }

    @Override
    public void updateUser(String previousName, UserEntity userEntity) throws UserCollectionException {
        String userName = userEntity.getName();
        Optional<UserEntity> userOptional = userRepository.findByName(userName);

        // User with given name already exists
        if(userOptional.isPresent()) throw new UserCollectionException(UserCollectionException.AlreadyExists(userName));
        else {
            deleteUser(previousName);
            registerUser(userEntity);
        }
    }

    @Override
    public void deleteUser(String id) throws UserCollectionException {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) userRepository.deleteById(id);
        else throw new UserCollectionException(UserCollectionException.NotFoundException(id));
    }
}
