package org.limir.universitylecturer.controller.pages.teacher;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.limir.universitylecturer.dto.TeacherDTO;
import org.limir.universitylecturer.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/teachers-panel")
@AllArgsConstructor
@NoArgsConstructor
public class AdminTeachersController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/add_teacher_panel")
    public String addTeacherPanel(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        return "teachers/add_teacher_panel";
    }

    @PostMapping("/add_teacher")
    public String addTeacher(@ModelAttribute TeacherDTO teacherDTO) {

        teacherService.saveTeacher(teacherDTO);

        return "redirect:/admin/teachers-panel/find_teachers_panel";
    }

    @GetMapping("/change_teacher_panel")
    public String changeTeacherPanel(String firstName,
                                     String lastName,
                                     String patronymic,
                                     Model model
    ) {
        prepareTeachersFilter(firstName, lastName, patronymic, model);

        return "teachers/change_teacher_panel";
    }

    @GetMapping("/delete_teacher_panel")
    public String deleteTeacherPanel(String firstName,
                                     String lastName,
                                     String patronymic,
                                     Model model
    ) {

        prepareTeachersFilter(firstName, lastName, patronymic, model);

        return "teachers/delete_teacher_panel";
    }

    private void prepareTeachersFilter(String firstName,
                                       String lastName,
                                       String patronymic,
                                       Model model) {

        String fn = (firstName == null || firstName.isBlank()) ? null : firstName;
        String ln = (lastName == null || lastName.isBlank()) ? null : lastName;
        String pt = (patronymic == null || patronymic.isBlank()) ? null : patronymic;

        List<TeacherDTO> teachers =
                teacherService.findTeacherByFullName(fn, ln, pt);

        model.addAttribute("teachers", teachers);
        model.addAttribute("searched", true);
    }

    @GetMapping("/find_teacher_by_fullname_panel")
    public String findTeacherByFullnamePanel(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String patronymic,
            Model model) {

        prepareTeachersFilter(firstName, lastName, patronymic, model);

        return "teachers/find_teacher_by_fullname_panel";
    }

    @GetMapping("/find_teachers_panel")
    public String findTeachersPanel(Model model) {
        List<TeacherDTO> teachers = teacherService.findTeachers();
        model.addAttribute("teachers", teachers);
        return "teachers/find_teachers_panel";
    }

}
