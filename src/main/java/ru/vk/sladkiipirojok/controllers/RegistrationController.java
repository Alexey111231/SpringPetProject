package ru.vk.sladkiipirojok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vk.sladkiipirojok.model.Role;
import ru.vk.sladkiipirojok.model.User;
import ru.vk.sladkiipirojok.repository.UserRepository;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userDB = userRepository.findByUsername(user.getUsername());

        if (userDB != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
