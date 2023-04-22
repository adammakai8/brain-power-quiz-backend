package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.exception.ThemeCollectionException;
import edu.maszek.brainpowerquiz.model.ThemeEntity;
import edu.maszek.brainpowerquiz.service.ThemeService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping
    public ResponseEntity<?> getAllThemes() {
        return new ResponseEntity<>(themeService.getAllThemes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getThemeByID(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(themeService.getThemeByID(id), HttpStatus.OK);
        } catch (ThemeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{text}")
    public ResponseEntity<?> getThemeByName(@PathVariable("text") String text) {
        try {
            return new ResponseEntity<>(themeService.getThemeByText(text), HttpStatus.OK);
        } catch (ThemeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createTheme(@RequestBody ThemeEntity themeEntity) {
        try {
            themeService.createTheme(themeEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ThemeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ResponseEntity<?> updateTheme(@RequestBody ThemeEntity themeEntity) {
        try {
            themeService.updateTheme(themeEntity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (ThemeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteThemeByID(@PathVariable("id") String id) {
        try {
            themeService.deleteThemeByID(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ThemeCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
