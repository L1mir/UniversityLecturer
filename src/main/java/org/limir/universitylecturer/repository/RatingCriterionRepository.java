package org.limir.universitylecturer.repository;

import org.limir.universitylecturer.model.RatingCriterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingCriterionRepository extends JpaRepository<RatingCriterion, Long> {

}
