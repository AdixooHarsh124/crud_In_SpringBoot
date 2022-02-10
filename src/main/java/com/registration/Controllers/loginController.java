package com.registration.Controllers;

import com.registration.Entities.Login;
import com.registration.services.loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class loginController {

    @Autowired
    private loginService LoginService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login)
    {
        String emailRegex = "^(.+)@(.+)$";
//        String regex="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(login.getEmail());
        System.out.println("matcher "+matcher.matches());

//        String mobileRegex="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$";
        String passwordValidation="/^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{7,}$/";
        Pattern patternn = Pattern.compile(passwordValidation);
        Matcher matcherr = pattern.matcher(login.getPassword());
        System.out.println("matcherr "+matcher.matches());
        if(matcher.matches()==true && matcherr.matches()==true){
        return LoginService.login(login.getEmail(), login.getPassword());
        }else {
            System.out.println("else");
            throw new UsernameNotFoundException("user credential wrong");
        }
    }
}
