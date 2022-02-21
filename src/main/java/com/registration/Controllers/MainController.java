package com.registration.Controllers;

import com.registration.Entities.Registration;
import com.registration.services.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.regex.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class MainController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/home")
    public String welcome()
    {
        String text="this is private page";
        text+="this page is not allowed to unauthenticated user";
        return text;
    }


    @PostMapping("/register")
    public Registration register(@RequestBody Registration registration) throws Exception {
        Registration reg;
        String emailRegex = "^(.+)@(.+)$";
//        String regex="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(registration.getEmail());
        System.out.println("email "+matcher.matches());


        String passwordValidation="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern patternn = Pattern.compile(passwordValidation);
        Matcher matcherr = patternn.matcher(registration.getPassword());
        System.out.println("password "+matcherr.matches());


        String mobileValidation="[6789][0-9]{9}";
        Pattern patternnn = Pattern.compile(mobileValidation);
        Matcher matcherrr = patternnn.matcher(registration.getMobile());
        System.out.println("mobile "+matcherrr.matches());
        if(matcher.matches()==true && matcherr.matches()==true && matcherrr.matches()==true){
            registration.setPassword(this.bCryptPasswordEncoder.encode(registration.getPassword()));
            reg=customUserDetailsService.addUser(registration);
            return reg;
        }else {
            throw new UsernameNotFoundException("user enter wrong format values");
        }


    }

    @GetMapping("/users")
    public List<Registration> users()
    {
        return customUserDetailsService.getAllUser();
    }

    @GetMapping("/user/{email}")
    public Registration updateUser(@PathVariable("email") String email)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        System.out.println("matcher "+matcher.matches());
        if(matcher.matches()==true)
        {
            return customUserDetailsService.getUser(email);
        }else{
            throw new UsernameNotFoundException("user does not exits");
        }
    }

    @PostMapping("/update/{email}")
    public Registration updateUser(@PathVariable("email") String email,@RequestBody Registration registration)
    {
        Registration reg;
        String emailRegex = "^(.+)@(.+)$";
//        String regex="^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(registration.getEmail());
        System.out.println("email "+matcher.matches());


        String passwordValidation="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern patternn = Pattern.compile(passwordValidation);
        Matcher matcherr = patternn.matcher(registration.getPassword());
        System.out.println("password "+matcherr.matches());


        String mobileValidation="[6789][0-9]{9}";
        Pattern patternnn = Pattern.compile(mobileValidation);
        Matcher matcherrr = patternnn.matcher(registration.getMobile());
        System.out.println("mobile "+matcherrr.matches());
        if(matcher.matches()==true && matcherr.matches()==true && matcherrr.matches()==true){
        registration.setPassword(this.bCryptPasswordEncoder.encode(registration.getPassword()));
        reg=customUserDetailsService.update(email, registration);
        return reg;
        }else {
            throw new UsernameNotFoundException("user enter wrong format values");
        }
    }




}
