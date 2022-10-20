package com.wutreg.sweater.controller;

import com.wutreg.sweater.entity.Message;
import com.wutreg.sweater.entity.User;
import com.wutreg.sweater.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static com.wutreg.sweater.controller.ControllerUtils.getErrors;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MessageService messageService;

    @Value("${upload.path}")
    private String uploadPath;

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
        @Valid Message message,
        BindingResult bindingResult,
        Model model,
        @RequestParam MultipartFile file
    ) throws IOException {
        message.setAuthor(author);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = getErrors(bindingResult);
            model.mergeAttributes(errorMap);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFileName));

                message.setFilename(resultFileName);
            }

            messageService.save(message);
            model.addAttribute("message", null);
        }

        List<Message> messages = messageService.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

}
