package com.wutreg.sweater.controller;

import com.wutreg.sweater.entity.User;
import com.wutreg.sweater.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/registration")
    public String showPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("password2Error", "Пароли не равны");
        }
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }

        if (!userService.add(user)) {
            model.addAttribute("usernameError", "Такой пользователь уже существует!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(@PathVariable String code, Model model) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "Пользователь успешно активирован");
        } else {
            model.addAttribute("message", "Код активации не найден!");
        }

        return "login";
    }
}
