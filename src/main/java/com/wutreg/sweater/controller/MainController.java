package com.wutreg.sweater.controller;

import com.wutreg.sweater.entity.Message;
import com.wutreg.sweater.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public String showAll(Map<String, Object> model) {
        List<Message> messages = messageService.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String addNewAndShowAll(
        @RequestParam String text, @RequestParam String tag,
        Map<String, Object> model
    ) {
        Message message = new Message(text, tag);
        messageService.save(message);

        List<Message> messages = messageService.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filterAndShow(@RequestParam String filter, Map<String, Object> model) {
        List<Message> messages = (Objects.isNull(filter) || filter.isEmpty())
            ? messageService.findAll()
            : messageService.findByTag(filter);
        model.put("messages", messages);
        return "main";
    }
}
