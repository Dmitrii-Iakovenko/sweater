package com.wutreg.sweater.controller;

import com.wutreg.sweater.domen.dto.CaptchaResponseDTO;
import com.wutreg.sweater.entity.User;
import com.wutreg.sweater.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    private final RestTemplate restTemplate;

    @Value("${captcha.url}")
    private String captchaURL;
    @Value("${captcha.server}")
    private String captchaServer;
    @Value("${captcha.client}")
    private String captchaClient;

    @GetMapping("/registration")
    public String showPage(Model model) {
        model.addAttribute("captchaClient", captchaClient);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirmation,
            @RequestParam("g-recaptcha-response") String captchaResponse,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("captchaClient", captchaClient);

        // POST запрос на сервер Google для проверки reCaptcha
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret", captchaServer);
        map.add("response", captchaResponse);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        CaptchaResponseDTO response = restTemplate.postForObject(captchaURL, request, CaptchaResponseDTO.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Заполните Капчу!");
        }

        boolean isConfirmEmpty = !StringUtils.hasText(passwordConfirmation);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Подтверждение пароля не может быть пустым!");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirmation)) {
            model.addAttribute("password2Error", "Пароли не равны");
        }
        if (isConfirmEmpty || bindingResult.hasErrors() || !response.isSuccess()) {
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
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "Пользователь успешно активирован");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Код активации не найден!");
        }

        return "login";
    }
}
