package com.erensayar.HrAppealModuleApi.model.dto.response_dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileAttachmentDto {

  private String id;
  private LocalDateTime createOrUpdateTime;

}
