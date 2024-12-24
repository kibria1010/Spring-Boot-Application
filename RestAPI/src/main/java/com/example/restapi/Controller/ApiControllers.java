package com.example.restapi.Controller;

import com.example.restapi.Models.Users;
import com.example.restapi.Repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiControllers {

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/")
    public String sayHello(Model model) {
        return "hello";
    }

    @GetMapping("/users")
    public List<Users> getUsers(Model model) {
        return usersRepo.findAll();
    }

    @PostMapping("/save")
    public String saveUsers(@RequestBody Users users) {
        usersRepo.save(users);
        return "saved users";
    }

    @PutMapping(value = "/update/{id}")
    public String updateUsers(@PathVariable int id, @RequestBody Users users) {
        Users update = usersRepo.findById(id).get();
        update.setName(users.getName());
        update.setEmail(users.getEmail());
        update.setPassword(users.getPassword());
        usersRepo.save(update);
        return "Updated users";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUsers(@PathVariable int id) {
        Users delete = usersRepo.findById(id).get();
        usersRepo.delete(delete);
        return "deleted user with the id:" + id;
    }
}
