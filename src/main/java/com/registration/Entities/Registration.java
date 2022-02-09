package com.registration.Entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

/**
 * registration table
 */
@NoArgsConstructor
@Entity
@Table(name="registration")
@Setter
@Getter
@ToString
public class Registration {


    @Column(name="username", nullable = false)
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Id
    @Column(name="email", unique = true)
    private String email;

    @Column(name="mobile",unique = true)
    private String mobile;

    @Column(name="password", nullable = false)
    private String password;


    public Registration(String firstname, String lastname, String email, String mobile, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

}
