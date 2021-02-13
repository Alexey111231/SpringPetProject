package ru.vk.sladkiipirojok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.vk.sladkiipirojok.model.Role;
import ru.vk.sladkiipirojok.model.User;
import ru.vk.sladkiipirojok.repository.UserRepository;
import ru.vk.sladkiipirojok.service.UserService;

import javax.validation.Valid;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (bindingResult.hasErrors()) {
            model.mergeAttributes(ControllerUtils.getErrorMap(bindingResult));

            return "registration";
        }
        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("message", "Activation code in not found");
        }
        return "login";
    }
}
