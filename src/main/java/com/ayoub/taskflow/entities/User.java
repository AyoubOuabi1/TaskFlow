package com.ayoub.taskflow.entities;

import com.ayoub.taskflow.entities.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int token;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;



}
