package com.bukeetcakir.gradebook.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @NotBlank
    @Column(nullable = false)
    private String number;

    @Email
    @NotBlank
    @Column(nullable = false)
    private String email;

    @Pattern(regexp = "^(05[0-9]{9})$", message = "Invalid phone number")
    @NotBlank
    @Column(nullable = false)
    private String gsmNumber;
}
