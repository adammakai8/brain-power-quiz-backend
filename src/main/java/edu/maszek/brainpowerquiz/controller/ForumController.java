package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.ForumCollectionException;
import edu.maszek.brainpowerquiz.model.entity.ForumEntity;
import edu.maszek.brainpowerquiz.service.ForumService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forums")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping
    public ResponseEntity<?> getAllForums() {
        return new ResponseEntity<>(forumService.getAllForums(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getForumByID(@PathVariable("id") String _id) {
        try {
            return new ResponseEntity<>(forumService.getForumByID(_id), HttpStatus.OK);
        } catch (ForumCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createForum(@RequestBody ForumEntity forumEntity) {
        try {
            forumService.createForum(forumEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateForum(@RequestBody ForumEntity forumEntity) {
        try {
            forumService.updateForum(forumEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ForumCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForumByID(@PathVariable("id") String id) {
        try {
            forumService.deleteForumByID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ForumCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
