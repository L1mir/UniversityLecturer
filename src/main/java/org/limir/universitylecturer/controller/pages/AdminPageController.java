package org.limir.universitylecturer.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminPageController {

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/users-panel")
    public String usersPanelPage() {
        return "users_panelA";
    }

    @GetMapping("teachers-panel")
    public String teachersPanelPage() {
        return "teachers_panelA";
    }

}
