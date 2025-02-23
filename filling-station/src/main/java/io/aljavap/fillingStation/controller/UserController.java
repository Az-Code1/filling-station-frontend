package io.aljavap.fillingStation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.aljavap.fillingStation.entity.User;
import io.aljavap.fillingStation.service.UserService;

@RestController
@RequestMapping("/filling-station/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }
    
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }
    
    @GetMapping("/username")
    public User getUserByHisUsername(@RequestParam String username){
        return userService.getByUsername(username);
    }
    
    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.getByUsername(username);
    }
    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }
    @GetMapping("/phone/{phoneNumber}")
    public User getUserByPhoneNumber(@PathVariable String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }

    @GetMapping("/details/{details}/{password}")
    public boolean getUserByDetails(@PathVariable String details,@PathVariable String password){
        return userService.getUserByDetails(details, password);
    }
    
}