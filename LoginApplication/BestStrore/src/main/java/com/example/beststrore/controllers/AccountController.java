package com.example.beststrore.controllers;


import com.example.beststrore.repository.AppUserRepository;
import com.example.beststrore.model.AppUser;
import com.example.beststrore.model.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @Autowired
    AppUserRepository repo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @Validated @ModelAttribute RegisterDto registerDto, BindingResult result) {


        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(new FieldError("registerDto", "registerDto", "Pass dont match"));
        }

        AppUser appUser = repo.findByEmail(registerDto.getEmail());
        if (appUser != null) {
            result.addError(new FieldError("registerDto", "email", "Email already in use"));
        }

        if (result.hasErrors()) {
            return "register";
        }

        try {
            // create new account
            var bCryptPasswordEncoder = new BCryptPasswordEncoder();
            AppUser newUaser = new AppUser();
            newUaser.setFirstName(registerDto.getFirstName());
            newUaser.setLastName(registerDto.getLastName());
            newUaser.setEmail(registerDto.getEmail());
            newUaser.setPhoneNumber(registerDto.getPhoneNumber());
            newUaser.setRole("Client");
            newUaser.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));

            repo.save(newUaser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);

        } catch (Exception e){
            result.addError(new FieldError("registerDto", "password", e.getMessage()));
        }

        return "register";
    }
}
