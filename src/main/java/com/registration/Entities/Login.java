package com.registration.Entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name="registration")
@Setter
@Getter
@ToString
public class Login {

    @Id
    @Column(name="email", unique = true)
    private String email;


    @Column(name="password", nullable = false)
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
