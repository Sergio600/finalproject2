package com.sergio.domain;

import com.sergio.enums.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@Table (name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name="first_name")
    private String firstName;

    @NotNull
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Column(name = "role_id")
    @Enumerated(ORDINAL)
    private Role role;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;


}
