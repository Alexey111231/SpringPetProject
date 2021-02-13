package ru.vk.sladkiipirojok.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.vk.sladkiipirojok.model.User;
import ru.vk.sladkiipirojok.model.dto.CaptchaResponseDTO;
import ru.vk.sladkiipirojok.service.UserService;

import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Collections;

@Controller
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    UserService userService;

    @Value("${recaptcha.secret}")
    String recaptchaSecret;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam("g-recaptcha-response") String captchaResponse,
                          @RequestParam("password2")
                                  String passwordConfirm,
                          @Valid User user,
                          BindingResult bindingResult,
                          Model model) {
        String urlCaptcha = String.format(CAPTCHA_URL, recaptchaSecret, captchaResponse);
        CaptchaResponseDTO response = restTemplate.postForObject(urlCaptcha, Collections.emptyList(), CaptchaResponseDTO.class);
        if (response == null || !response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation cannot be empty");
        }
        if (user.getPassword() != null && !user.equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different");
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || response == null || !response.isSuccess()) {
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
            model.addAttribute("messageType", "success");
        } else {
            model.addAttribute("message", "Activation code in not found");
            model.addAttribute("messageType", "danger");
        }
        return "login";
    }
}
