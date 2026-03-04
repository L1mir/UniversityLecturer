package org.limir.universitylecturer.controller.pages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserPageController {

    @GetMapping
    public String userPage() {
        return "user";
    }

    @GetMapping("/users-panel")
    public String usersPanelPage() {
        return "users_panelU";
    }
}