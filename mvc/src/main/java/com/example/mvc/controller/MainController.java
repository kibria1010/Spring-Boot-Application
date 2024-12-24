package com.example.mvc.controller;

import com.example.mvc.model.Programmer;
import com.example.mvc.repository.ProgrammerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@ControllerAdvice
public class MainController {

    @Autowired
    ProgrammerRepo programmerRepo;

    @ModelAttribute
    public void welcome(Model model) {
        model.addAttribute("msg", "Welcome to Spring MVC");
    }

    @RequestMapping("/home")
    public String sayHello(Model model) {
        System.out.println("Greatting...");
        model.addAttribute("message", "Welcome to spring ...");
        return "home";
    }

    @PostMapping("/addProgrammer")
    public ModelAndView addProgrammer(@ModelAttribute Programmer programmer) {
        programmerRepo.save(programmer);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }

    @PostMapping("/findWithId")
    public String findWithId(@RequestParam int pId, Model model) {
        Programmer programmer = programmerRepo.getOne(pId);
        model.addAttribute("programmer", programmer);
        return "programmerInfo";
    }

    @PostMapping("/updateProgrammer")
    public String updateProgrammer(@ModelAttribute Programmer programmer) {
        Programmer p = programmerRepo.getOne(programmer.getpId());
        p.setpName(programmer.getpName());
        p.setpLang(programmer.getpLang());
        programmerRepo.save(p);
        return "programmerInfo";
    }

    @GetMapping("/deleteWithId")
    public String deleteWithId(@RequestParam int pId, Model model) {
        programmerRepo.deleteById(pId);
        return "redirect:/home";
    }

    @GetMapping("/allProgrammer")
    public String allProgrammer(Model model) {
        List<Programmer> p = new ArrayList<Programmer>();
        p.add(new Programmer(123, "KI", "java"));
        p.add(new Programmer(233, "dd", "c"));
        p.add(new Programmer(345, "ee", "c++"));

        model.addAttribute("programmers", p);

        return ("allProgrammer");
    }
}


