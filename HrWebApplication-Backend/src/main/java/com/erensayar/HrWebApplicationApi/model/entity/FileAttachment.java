package com.erensayar.HrWebApplicationApi.model.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FileAttachment {

    @Id
    private String id;

    private String name;

    private String filePath;

    private LocalDateTime createdOrUpdatedTime;
}