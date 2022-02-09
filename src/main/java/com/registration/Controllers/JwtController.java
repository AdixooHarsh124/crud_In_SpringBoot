package com.registration.Controllers;

import com.registration.helper.JwtUtil;
import com.registration.model.JwtRequest;
import com.registration.model.JwtResponse;
import com.registration.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

//@RequiredArgsConstructor
@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{
            System.out.println("try");
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));
        }catch(UsernameNotFoundException e)
        {
            System.out.println("catch");
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }
        //fine area ....
        //than create token
        UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT :-"+token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
