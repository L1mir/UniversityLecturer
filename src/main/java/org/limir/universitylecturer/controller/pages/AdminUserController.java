package org.limir.universitylecturer.controller.pages;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.limir.universitylecturer.dto.UserResponse;
import org.limir.universitylecturer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String showFindUsersPanel(Model model){
        List<UserResponse> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "find_users_panel";
    }

    @PatchMapping("/{name}/change-role")
    public String changeRole(@PathVariable String name) {
        userService.changeUserRole(name);
        return "redirect:/admin/users/list";
    }

    @DeleteMapping("{id}/delete")
    public String deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return  "redirect:/admin/users/list";
    }

}
