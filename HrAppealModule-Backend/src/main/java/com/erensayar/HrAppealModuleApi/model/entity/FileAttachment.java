package com.erensayar.HrAppealModuleApi.model.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

  private LocalDateTime createOrUpdateTime;

  //@OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "cv")
  //private Applicant applicant;

}