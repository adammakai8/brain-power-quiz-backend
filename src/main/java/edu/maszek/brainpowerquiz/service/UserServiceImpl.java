package edu.maszek.brainpowerquiz.service;

import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.entity.UserEntity;
import edu.maszek.brainpowerquiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConnectionUpdateServiceImpl connectionUpdateService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getAllUsers() {
        final List<UserEntity> usersOptional = userRepository.findAll();
        if(usersOptional.size() > 0) return usersOptional;
        else return new ArrayList<>();
    }

    @Override
    public UserEntity getUserByName(String name) throws UserCollectionException {
        Optional<UserEntity> user = userRepository.findByUsername(name);
        if(user.isPresent()) return user.get();
        else throw new UserCollectionException(UserCollectionException.NotFoundException(name));
    }

    @Override
    public void updateUser(String previousName, UserEntity userEntity) throws UserCollectionException {
        String userName = userEntity.getUsername();

        if(userRepository.findByUsername(userName).isPresent())
            throw new UserCollectionException(UserCollectionException.AlreadyExists(userName));
        else {
            UserEntity userToUpdate = userRepository.findByUsername(previousName).get();

            userToUpdate.setUsername(userEntity.getUsername());
            userToUpdate.setEmail(userEntity.getEmail());
            userToUpdate.setPassword(passwordEncoder.encode(userEntity.getPassword()));
            userToUpdate.setForums(userEntity.getForums());
            userToUpdate.setForumComments(userEntity.getForumComments());
            userRepository.save(userToUpdate);
        }
    }

    @Override
    public void deleteUser(String id) throws UserCollectionException {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            UserEntity userEntity = userOptional.get();
            connectionUpdateService.updateForumConnection("user", userEntity, "delete");
            connectionUpdateService.updateForumCommentConnection("user", userEntity, "delete");
            userRepository.deleteById(id);
        }
        else throw new UserCollectionException(UserCollectionException.NotFoundException(id));
    }
}
