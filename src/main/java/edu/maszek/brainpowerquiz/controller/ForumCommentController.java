package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.ForumCommentCollectionException;
import edu.maszek.brainpowerquiz.model.entity.ForumCommentEntity;
import edu.maszek.brainpowerquiz.service.ForumCommentService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forumComments")
public class ForumCommentController {
    @Autowired
    private ForumCommentService forumCommentService;

    @GetMapping
    public ResponseEntity<?> getAllForumComments() {
        return new ResponseEntity<>(forumCommentService.getAllForumComments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getForumCommentByID(@PathVariable("id") String _id) {
        try {
            return new ResponseEntity<>(forumCommentService.getForumCommentByID(_id), HttpStatus.OK);
        } catch (ForumCommentCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createForumComment(@RequestBody ForumCommentEntity forumCommentEntity) {
        try {
            forumCommentService.createForumComment(forumCommentEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateForumComment(@RequestBody ForumCommentEntity forumCommentEntity) {
        try {
            forumCommentService.updateForumComment(forumCommentEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ForumCommentCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteForumCommentByID(@PathVariable("id") String id) {
        try {
            forumCommentService.deleteForumCommentByID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ForumCommentCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
