package com.wutreg.sweater.controller;

import com.wutreg.sweater.entity.Message;
import com.wutreg.sweater.entity.User;
import com.wutreg.sweater.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageService messageService;

    @GetMapping("/main")
    public String showAll(
        @RequestParam(required = false, defaultValue = "") String filter,
        Model model
    ) {
        List<Message> messages = (Objects.isNull(filter) || filter.isEmpty())
            ? messageService.findAll()
            : messageService.findByTag(filter);
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String addNewAndShowAll(
        @AuthenticationPrincipal User author,
        @RequestParam String text,
        @RequestParam String tag,
        Model model
    ) {
        Message message = new Message(text, tag, author);
        messageService.save(message);

        List<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

}
