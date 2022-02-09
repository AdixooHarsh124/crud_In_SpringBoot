package com.registration.Controllers;

import com.registration.Entities.Login;
import com.registration.Entities.Registration;
import com.registration.services.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MainController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/home")
    public String welcome()
    {
        String text="this is private page";
        text+="this page is not allowed to unauthenticated user";
        return text;
    }

    @PostMapping("/register")
    public String register(@RequestBody Registration registration)
    {
        registration.setPassword(this.bCryptPasswordEncoder.encode(registration.getPassword()));
        return customUserDetailsService.addUser(registration);
    }

    @GetMapping("/users")
    public List<Registration> users()
    {
        System.out.println("controller");
        return customUserDetailsService.getAllUser();
    }

    @GetMapping("/user/{email}")
    public Registration updateUser(@PathVariable("email") String email)
    {
        return customUserDetailsService.getUser(email);
    }


}
