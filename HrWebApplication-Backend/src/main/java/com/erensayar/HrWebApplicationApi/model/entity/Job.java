package com.erensayar.HrWebApplicationApi.model.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Size(min = 10, max = 100, message = "{hrwebapp.constraint.job.name.Size.message}")
    @Column(unique = true, length = 100, nullable = false)
    private String name;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Size(max = 200, message = "{hrwebapp.constraint.job.summary.Size.message}")
    @Column(unique = true, length = 200, nullable = false)
    private String summary;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Size(max = 3000, message = "{hrwebapp.constraint.job.description.Size.message}")
    @Column(length = 3000, nullable = false)
    private String description;

    @NotNull(message = "{hrwebapp.constraint.NotNull.message}")
    @Size(max = 1000, message = "{hrwebapp.constraint.job.expected.qualification.Size.message}")
    @Column(length = 1000, nullable = false)
    private String expectedQualification;

    private Integer numberOfToHire;

    private LocalDate lastApplicationDate;

    @ManyToMany
    private List<Applicant> applicants;
}
