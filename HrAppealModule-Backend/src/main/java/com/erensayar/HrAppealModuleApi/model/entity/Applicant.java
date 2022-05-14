package com.erensayar.HrAppealModuleApi.model.entity;

import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Entity
public class Applicant {

  @Id
  private String id;

  @Size(min = 2, max = 20, message = "{hrwebapp.constraint.name.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Column(length = 20, nullable = false)
  private String name;

  @Size(min = 2, max = 10, message = "{hrwebapp.constraint.surname.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Column(length = 10, nullable = false)
  private String surname;

  @Size(min = 2, max = 50, message = "{hrwebapp.constraint.mail.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Pattern(regexp = "^(.+)@(.+)$", message = "{hrwebapp.constraint.mail.Pattern.message}")
  @Column(length = 50, nullable = false)
  private String mail;

  @Size(min = 10, max = 13, message = "{hrwebapp.constraint.telephone.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Column(length = 10, nullable = false)
  private String telephone;

  @Size(min = 2, max = 15, message = "{hrwebapp.constraint.country.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Column(length = 15, nullable = false)
  private String country;

  @Size(min = 2, max = 15, message = "{hrwebapp.constraint.residence.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Column(length = 15, nullable = false)
  private String city;

  @Size(min = 2, max = 15, message = "{hrwebapp.constraint.district.Size.message}")
  @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
  @Column(length = 15, nullable = false)
  private String district;

  @Size(min = 11, max = 100, message = "{hrwebapp.constraint.link.Size.message}")
  @Column(length = 100)
  private String gitLink;

  @Size(min = 13, max = 100, message = "{hrwebapp.constraint.link.Size.message}")
  @Column(length = 100)
  private String linkedInLink;

  @Size(min = 13, max = 100, message = "{hrwebapp.constraint.link.Size.message}")
  @Column(length = 100)
  private String twitterLink;

  @Enumerated(EnumType.ORDINAL)
  private ApplicantStatus applicantStatus;

  private LocalDateTime applicationDateAndTime;

  @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
  private FileAttachment cv;

  private Boolean personalInfoStoragePermission;

  private Boolean isArchived;

  public Applicant(String name, String surname, String mail, String telephone,
      String country, String city, String district, String gitLink, String linkedInLink,
      String twitterLink, FileAttachment cv, Boolean personalInfoStoragePermission) {
    this.name = name;
    this.surname = surname;
    this.mail = mail;
    this.telephone = telephone;
    this.country = country;
    this.city = city;
    this.district = district;
    this.gitLink = gitLink;
    this.linkedInLink = linkedInLink;
    this.twitterLink = twitterLink;
    this.cv = cv;
    this.personalInfoStoragePermission = personalInfoStoragePermission;
    // Defaults
    this.id = "APL" + UUID.randomUUID().toString().replaceAll("-", "");
    this.applicantStatus = ApplicantStatus.TO_BE_EVALUATED;
    this.applicationDateAndTime = LocalDateTime.now();
    this.isArchived = false;
  }

  public Applicant() {
    // Defaults
    this.id = "APL" + UUID.randomUUID().toString().replaceAll("-", "");
    this.applicantStatus = ApplicantStatus.TO_BE_EVALUATED;
    this.applicationDateAndTime = LocalDateTime.now();
    this.isArchived = false;
  }
}
