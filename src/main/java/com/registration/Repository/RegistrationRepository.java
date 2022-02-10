package com.registration.Repository;

import com.registration.Entities.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories
public interface RegistrationRepository extends JpaRepository<Registration,String> {


}