package com.erensayar.HrAppealModuleApi.model.dto.response_dto;

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
public class FileAttachmentGetDto {

  private String id;
  private String name;
  private String filePath;
  private LocalDateTime createOrUpdateTime;

}
