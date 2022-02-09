package com.registration.services;

import com.registration.Entities.Registration;
import com.registration.Repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class CustomUserDetailsService implements UserDetailsService{


    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        Registration registration =  registrationRepository.getById(username);
        System.out.println("user details"+registration.getPassword());
            return new User(username,registration.getPassword(),new ArrayList<>());
    }

    /**
     *
     *Get ALL Users
     *
     */

    public List<Registration> getAllUser()
    {
        return registrationRepository.findAll();
    }

    /**
     *
     *  Add New User in Mysqldb
     *
     */

    public String addUser(Registration registration)
    {
        Registration reg=registrationRepository.save(registration);
        System.out.println("the success message "+reg);
        return "new user";
    }

    /**
     *
     * Get a User by Email
     *
     **/

    public Registration getUser(String email)
    {
        Registration student=null;
        List<Registration> users=registrationRepository.findAll();
        for (Registration user : users) {
            if (user.getEmail().equals(email)) {
                student = user;
                break;
            }else{
                throw new UsernameNotFoundException("User not found!");
            }
        }
        return student;
    }


}
