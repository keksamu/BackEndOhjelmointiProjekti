package com.example.projekti.nba_players.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.projekti.nba_players.model.AppUser;
import com.example.projekti.nba_players.model.AppUserRepository;

@Controller
public class RegistrationController {

    private final AppUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            Model model) {
        
        // Check if username already exists
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        // Create new user with USER role
        AppUser newUser = new AppUser(
            username,
            passwordEncoder.encode(password),
            email,
            "USER"
        );
        
        userRepository.save(newUser);
        
        return "redirect:/login?registered";
    }
}
