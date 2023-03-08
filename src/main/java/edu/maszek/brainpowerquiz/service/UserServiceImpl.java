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
    public void registerUser(UserEntity userEntity) throws ConstraintViolationException, UserCollectionException {
        Optional<UserEntity> userOptional = userRepository.findById(userEntity.getName());
        if(userOptional.isPresent()) throw new UserCollectionException(UserCollectionException.AlreadyExists());
        else userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        System.out.println("Before get all users");
        List<UserEntity> users = userRepository.findAll();
        System.out.println("After get all users");
        if(users.size() > 0) return users;
        else return new ArrayList<>();
    }
}
