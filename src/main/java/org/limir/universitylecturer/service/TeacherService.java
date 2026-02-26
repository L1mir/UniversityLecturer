package org.limir.universitylecturer.service;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.TeacherDTO;
import org.limir.universitylecturer.mappers.TeacherMapper;
import org.limir.universitylecturer.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository teacherRepository;
    private TeacherMapper teacherMapper;

    public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
        teacherRepository.save(teacherMapper.teacherDTOToTeacher(teacherDTO));
        return teacherDTO;
    }

    public List<TeacherDTO> findTeachers() {
        return teacherRepository
                .findAll()
                .stream()
                .map(teacherMapper::teacherToTeacherDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO findTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(teacherMapper::teacherToTeacherDTO)
                .orElse(null);
    }

    public Long deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
        return id;
    }
}
