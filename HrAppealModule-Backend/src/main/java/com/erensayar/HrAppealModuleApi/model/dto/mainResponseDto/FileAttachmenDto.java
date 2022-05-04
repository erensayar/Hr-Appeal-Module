package com.erensayar.HrAppealModuleApi.model.dto.returnDto;

import java.time.LocalDateTime;
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
public class FileAttachmenDto {

  private String id;
  private String name;
  private String filePath;
  private LocalDateTime createOrUpdateTime;
  //private ApplicantDto applicant;

}
