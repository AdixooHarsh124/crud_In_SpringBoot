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
            return new User(username,registration.getPassword(),new ArrayList<>());
    }

    /**
     *
     *Get ALL Users
     *
     */

    public List<Registration> getAllUser()
    {
        List<Registration> regi=registrationRepository.findAll();

        return regi;
    }

    /**
     *
     *  Add New User in Mysqldb
     *
     */

    public Registration addUser(Registration registration)
    {
        Registration reg=null;
        if(registrationRepository.existsById(registration.getEmail()))
        {
            System.out.println("this email is already exist");
        }else{
            try{
                reg=registrationRepository.save(registration);
            }catch(Exception e)
            {
                System.out.println("this mobile number already exist");
            }
        }
        return reg;
    }

    /**
     *
     * Get a User by Email
     *
     **/

    public Registration getUser(String email)
    {
        List<Registration> users=registrationRepository.findAll();
        int result=0;
        for (Registration user : users)
        {
            if (user.getEmail().equals(email)) {
                user.setFirstname(user.getFirstname());
                user.setLastname(user.getLastname());
                user.setEmail(user.getEmail());
                user.setMobile(user.getMobile());
                user.setPassword(user.getPassword());
                result=1;
                return user;
            }
        }
        if(result==0){
        throw new UsernameNotFoundException("user not found!!!");
        }
        return null;
    }


    public Registration update(String email,Registration registration)
    {
        System.out.println("update");
        System.out.println(email);
        System.out.println(registration);
       if(registrationRepository.existsById(email)!=true)
       {
          throw new UsernameNotFoundException("user does not exist ");
       }else{
           System.out.println(registrationRepository.save(registration));
          return registrationRepository.save(registration);
       }

    }
}
