package org.limir.universitylecturer.controller;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.TeacherDTO;
import org.limir.universitylecturer.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {
    private TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDTO> saveTeacher(@RequestBody TeacherDTO teacherDTO) {
        return ResponseEntity.ok(teacherService.saveTeacher(teacherDTO));
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.findTeachers());
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherDTO> getTeacherById(@PathVariable long id) {
        return ResponseEntity.ok(teacherService.findTeacherById(id));
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Long> deleteTeacherById(@PathVariable long id) {
        return ResponseEntity.ok(teacherService.deleteTeacherById(id));
    }
}
