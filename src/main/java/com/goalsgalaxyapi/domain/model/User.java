package com.goalsgalaxyapi.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@ToString
@EqualsAndHashCode
@Table(name = "app_user")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @Column(unique = true)
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Goal> goals;

    public User(String name, String email, String encryptedPassword) {
        this.name = name;
        this.email = email;
        this.password = encryptedPassword;
    }
}
