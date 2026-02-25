package org.limir.universitylecturer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherRatingDTO{
    private Long id;
    private Short rating;
    private Long userId;
    private Long teacherId;
    private Long criteriaId;
}
