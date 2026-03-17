package org.limir.universitylecturer.repository;

import org.limir.universitylecturer.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT DISTINCT t FROM Teacher t " +
            "WHERE (:firstName IS NULL OR t.firstName = :firstName) " +
            "AND (:lastName IS NULL OR t.lastName = :lastName) " +
            "AND (:patronymic IS NULL OR t.patronymic = :patronymic)")
    List<Teacher> findTeachersFullName(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("patronymic") String patronymic
    );
}
