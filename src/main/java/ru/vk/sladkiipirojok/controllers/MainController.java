package ru.vk.sladkiipirojok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vk.sladkiipirojok.model.Message;
import ru.vk.sladkiipirojok.model.User;
import ru.vk.sladkiipirojok.repository.MessagesRepository;

@Controller
public class MainController {
    @Autowired
    MessagesRepository messagesRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<Message> messages = messagesRepository.findAll();
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Model model) {
        Message message = new Message(text, tag, user);

        messagesRepository.save(message);

        Iterable<Message> messages = messagesRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        Iterable<Message> messages;
        if (filter != null && !filter.isEmpty()) {
            messages = messagesRepository.findByTag(filter);
        } else {
            messages = messagesRepository.findAll();
        }
        model.addAttribute("messages", messages);
        return "main";
    }
}