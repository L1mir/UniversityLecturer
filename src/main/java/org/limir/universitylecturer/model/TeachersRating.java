package org.limir.universitylecturer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "teachers_ratings",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "teacher_id", "criteria_id"}))
public class TeachersRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "criteria_id", nullable = false)
    private RatingCriterion criteria;

    @Column(name = "rating", nullable = false)
    @Min(value = 0, message = "Рейтинг не может быть меньше 0!")
    @Max(value = 10, message = "Рейтинг не может быть выше 10!")
    private Short rating;
}