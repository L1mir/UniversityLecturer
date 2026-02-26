package org.limir.universitylecturer.mappers;

import org.limir.universitylecturer.dto.TeacherDTO;
import org.limir.universitylecturer.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher teacherDTOToTeacher(TeacherDTO teacherDTO);
    TeacherDTO teacherToTeacherDTO(Teacher teacher);
}
