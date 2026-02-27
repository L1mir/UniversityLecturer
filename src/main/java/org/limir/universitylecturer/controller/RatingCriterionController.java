package org.limir.universitylecturer.controller;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.RatingCriterionDto;
import org.limir.universitylecturer.service.RatingCriterionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rating-criterion")
@AllArgsConstructor
public class RatingCriterionController {
    private RatingCriterionService ratingCriterionService;

    @PostMapping
    public ResponseEntity<RatingCriterionDto> saveRatingCriterion(@RequestBody RatingCriterionDto teacherDTO) {
        return ResponseEntity.ok(ratingCriterionService.saveRatingCriterion(teacherDTO));
    }

    @GetMapping
    public ResponseEntity<List<RatingCriterionDto>> getAllRatingCriterion() {
        return ResponseEntity.ok(ratingCriterionService.findRatingCriterion());
    }

    @GetMapping("/rating-criterion/{id}")
    public ResponseEntity<RatingCriterionDto> getRatingCriterionById(@PathVariable long id) {
        return ResponseEntity.ok(ratingCriterionService.findRatingCriterionById(id));
    }

    @DeleteMapping("/rating-criterion/{id}")
    public ResponseEntity<Long> deleteRatingCriterionById(@PathVariable long id) {
        return ResponseEntity.ok(ratingCriterionService.deleteRatingCriterionById(id));
    }
}
