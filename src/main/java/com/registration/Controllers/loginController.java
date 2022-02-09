package com.registration.Controllers;

import com.registration.Entities.Login;
import com.registration.services.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class loginController {

    @Autowired
    private loginService LoginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login)
    {
        return LoginService.login(login.getEmail(),login.getPassword());
    }
}
