package org.limir.universitylecturer.service;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.RatingCriterionDto;
import org.limir.universitylecturer.mappers.RatingCriterionMapper;
import org.limir.universitylecturer.repository.RatingCriterionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingCriterionService {
    private RatingCriterionRepository ratingCriterionRepository;
    private RatingCriterionMapper ratingCriterionMapper;

    public RatingCriterionDto saveRatingCriterion(RatingCriterionDto ratingCriterionDto) {
        ratingCriterionRepository.save(ratingCriterionMapper.ratingCriterionDtoToRatingCriterion(ratingCriterionDto));
        return ratingCriterionDto;
    }

    public List<RatingCriterionDto> findRatingCriterion() {
        return ratingCriterionRepository
                .findAll()
                .stream()
                .map(ratingCriterionMapper::ratingCriterionToRatingCriterionDto)
                .collect(Collectors.toList());
    }

    public RatingCriterionDto findRatingCriterionById(Long id) {
        return ratingCriterionRepository.findById(id)
                .map(ratingCriterionMapper::ratingCriterionToRatingCriterionDto)
                .orElse(null);
    }

    public Long deleteRatingCriterionById(Long id) {
        ratingCriterionRepository.deleteById(id);
        return id;
    }
}
