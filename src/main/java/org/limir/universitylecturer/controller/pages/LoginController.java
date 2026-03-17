package org.limir.universitylecturer.controller.pages;

import lombok.AllArgsConstructor;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model) {

        UserResponse userResponse = userService.authenticate(email, password);

        if (userResponse != null) {
            if (userResponse.getRole().getId() == 2) {
                return "redirect:/admin";
            } else {
                return "redirect:/user";
            }
        } else {
            model.addAttribute("error", "Неверный логин или пароль");
            return "login";
        }
    }
}
