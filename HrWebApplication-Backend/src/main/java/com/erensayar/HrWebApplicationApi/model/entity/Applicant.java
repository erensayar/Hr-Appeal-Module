package com.erensayar.HrWebApplicationApi.model.entity;

import com.erensayar.HrWebApplicationApi.model.enums.ApplicantStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Applicant {

    @Id
    private String id;

    @Size(min = 2, max = 20, message = "{hrwebapp.constraint.name.Size.message}")
    @Column(length = 20, nullable = false)
    @NotNull(message = "{hrwebapp.constraint.name.NotNull.message}")
    private String name;

    @Size(min = 2, max = 10, message = "{hrwebapp.constraint.surname.Size.message}")
    @Column(length = 10, nullable = false)
    @NotNull(message = "{hrwebapp.constraint.surname.NotNull.message}")
    private String surname;

    @Size(min = 2, max = 50, message = "{hrwebapp.constraint.mail.Size.message}")
    @Column(length = 50, nullable = false)
    @NotNull(message = "{hrwebapp.constraint.mail.NotNull.message}")
    private String mail;

    @Size(min = 13, max = 13, message = "{hrwebapp.constraint.telephone.Size.message}")
    @Column(length = 13, nullable = false)
    @NotNull(message = "{hrwebapp.constraint.telephone.NotNull.message}")
    private String telephone;

    @Size(min = 2, max = 15, message = "{hrwebapp.constraint.residence.Size.message}")
    @Column(length = 15)
    private String city;

    @Size(min = 2, max = 15, message = "{hrwebapp.constraint.district.Size.message}")
    @Column(length = 15)
    private String district;

    @Size(min = 11, max = 100, message = "{hrwebapp.constraint.gitLink.Size.message}")
    @Column(length = 100)
    private String gitLink;

    @Size(min = 13, max = 100, message = "{hrwebapp.constraint.linkedInLink.Size.message}")
    @Column(length = 100)
    private String linkedInLink;

    @Enumerated(EnumType.ORDINAL)
    private ApplicantStatus applicantStatus;

    private LocalDate applicationDate;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private FileAttachment cv;

    private Boolean personalInfoStoragePermission;

}
