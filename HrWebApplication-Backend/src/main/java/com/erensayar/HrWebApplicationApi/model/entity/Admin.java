package com.erensayar.HrWebApplicationApi.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Column(nullable = false)
    private String surname;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Column(nullable = false, unique = true)
    private String personalMail;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Column(nullable = false, unique = true)
    private String workMail;

    @Size(min = 8, max = 255, message = "{hrwebapp.constraint.password.Size.message}")
    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "{hrwebapp.constraint.password.Pattern.message}")
    @Column(nullable = false)
    private String password;

}