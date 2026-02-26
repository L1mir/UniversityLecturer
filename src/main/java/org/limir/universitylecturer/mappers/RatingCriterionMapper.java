package org.limir.universitylecturer.mappers;

import org.limir.universitylecturer.dto.RatingCriterionDto;
import org.limir.universitylecturer.model.RatingCriterion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingCriterionMapper {
    RatingCriterion ratingCriterionDtoToRatingCriterion(RatingCriterionDto ratingCriterionDto);
    RatingCriterionDto ratingCriterionToRatingCriterionDto(RatingCriterion ratingCriterion);
}
