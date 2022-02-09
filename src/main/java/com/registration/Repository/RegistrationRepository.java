package com.registration.Repository;

import com.registration.Entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.User;


@EnableJpaRepositories
public interface RegistrationRepository extends JpaRepository<Registration,String> {


}