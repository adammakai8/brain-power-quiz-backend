package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(statisticService.getAllAnswers(), HttpStatus.OK);
    }

    @GetMapping("/themes/popular")
    public ResponseEntity<?> getThemesByPopularity() {
        return new ResponseEntity<>(statisticService.getThemesByPopularity(), HttpStatus.OK);
    }

    @GetMapping("/users/{_id}/themes/popular")
    public ResponseEntity<?> getUserFavouriteThemes(@PathVariable("_id") String _id) {
        return new ResponseEntity<>(statisticService.getUserFavouriteThemes(_id), HttpStatus.OK);
    }
}
