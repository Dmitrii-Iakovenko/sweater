package com.wutreg.sweater.controller;

import com.wutreg.sweater.domen.Role;
import com.wutreg.sweater.entity.User;
import com.wutreg.sweater.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "userList";
    }

    @GetMapping("/{id}")
    public String editUserForm(@PathVariable("id") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String saveUser(
        @RequestParam("id") User user,
        @RequestParam String username,
        @RequestParam Map<String, String> formRoles
    ) {
        user.setUsername(username);

        Set<Role> roles = Arrays.stream(Role.values())
            .filter(role -> formRoles.containsKey(role.name()))
            .collect(Collectors.toSet());
        user.setRoles(roles);

        userService.save(user);
        return "redirect:/users";
    }

}
