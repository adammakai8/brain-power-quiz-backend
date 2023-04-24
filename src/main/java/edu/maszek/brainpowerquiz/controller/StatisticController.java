package edu.maszek.brainpowerquiz.controller;

import edu.maszek.brainpowerquiz.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/themes/popular")
    public ResponseEntity<?> getThemesByPopularity() {
        return new ResponseEntity<>(statisticService.getThemesByPopularity(), HttpStatus.OK);
    }

    @GetMapping("/global")
    public ResponseEntity<?> getGlobalStatistics() {
        return new ResponseEntity<>(statisticService.getGlobalStatistics(), HttpStatus.OK);
    }

    @GetMapping("/ranklist")
    public ResponseEntity<?> getRanklist() {
        return new ResponseEntity<>(statisticService.getRanklist(), HttpStatus.OK);
    }

    @GetMapping("/users/{_id}/themes/popular")
    public ResponseEntity<?> getUserFavouriteThemes(@PathVariable("_id") String _id) {
        return new ResponseEntity<>(statisticService.getUserFavouriteThemes(_id), HttpStatus.OK);
    }

    @GetMapping("/users/{_id}")
    public ResponseEntity<?> getUserStatistics(@PathVariable("_id") String _id) {
        return new ResponseEntity<>(statisticService.getUserStatistics(_id), HttpStatus.OK);
    }
}
