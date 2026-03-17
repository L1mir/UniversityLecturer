package org.limir.universitylecturer.controller.pages.admin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/admin/users-panel")
@AllArgsConstructor
@NoArgsConstructor
public class AdminUsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showFindUsersPanel(Model model){
        List<UserResponse> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "find_users_panel";
    }

    @PatchMapping("/{name}/change-role")
    public String changeRole(@PathVariable String name,
                             @RequestHeader(value = "Referer", required = false) String referer) {
        userService.changeUserRole(name);
        return "redirect:" + referer;
    }

    @DeleteMapping("{id}/delete")
    public String deleteUserById(@PathVariable Long id,
                                 @RequestHeader(value = "Referer", required = false) String referer) {
        userService.deleteUserById(id);
        return "redirect:" + referer;
    }


    @GetMapping("/find_user_by_email_panel")
    public String searchUserByEmail(@RequestParam(required = false) String email, Model model) {
        List<UserResponse> users = null;

        if (email != null && !email.isEmpty()) {
            UserResponse user = userService.findUserByEmail(email);
            if (user != null) {
                users = Collections.singletonList(user);
            }
        }

        model.addAttribute("users", users);
        model.addAttribute("searched", true);

        return "find_user_by_email_panel";
    }

    private void prepareUsersFilter(String login,
                                    String email,
                                    Long role,
                                    Model model) {
        String normalizedLogin = (login == null || login.isBlank()) ? null : login;
        String normalizedEmail = (email == null || email.isBlank()) ? null : email;
        List<UserResponse> users = userService.findUsersByNameEmailRole(
                normalizedLogin, normalizedEmail, role);
        model.addAttribute("users", users);
        model.addAttribute("searched", true);
    }

    @GetMapping("/change_user_role_panel")
    public String changeUserRolePanel(@RequestParam(required = false) String login,
                                      @RequestParam(required = false) String email,
                                      @RequestParam(required = false) Long role,
                                      Model model) {
        prepareUsersFilter(login, email, role, model);
        return "change_user_role_panel";
    }

    @GetMapping("/delete_user_panel")
    public String deleteUserPanel(@RequestParam(required = false) String login,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) Long role,
                                  Model model) {
        prepareUsersFilter(login, email, role, model);
        return "delete_user_panel";
    }
}