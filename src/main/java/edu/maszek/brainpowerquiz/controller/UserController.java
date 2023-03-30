package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.UserCollectionException;
import edu.maszek.brainpowerquiz.model.UserEntity;
import edu.maszek.brainpowerquiz.service.UserService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        if(users.size() > 0) return new ResponseEntity<>(users, HttpStatus.OK);
        else return new ResponseEntity<>("No users available", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{previousName}")
    public ResponseEntity<?> updateUser(@PathVariable("previousName") String previousName, @RequestBody @Valid UserEntity userEntity) {
        try {
            userService.updateUser(previousName, userEntity);
            return new ResponseEntity<>("Updated user with name " + userEntity.getUsername(), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("Deleted user with id " + id, HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
