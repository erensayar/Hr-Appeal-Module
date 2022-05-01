package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.AdminCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MapperOfAdmin {

  public Admin toEntity(AdminCreateOrUpdateDto adminCreateOrUpdateDto) {
    return Admin.builder()
        .id(adminCreateOrUpdateDto.getId())
        .name(adminCreateOrUpdateDto.getName())
        .username(adminCreateOrUpdateDto.getUsername())
        .surname(adminCreateOrUpdateDto.getSurname())
        .personalMail(adminCreateOrUpdateDto.getPersonalMail())
        .workMail(adminCreateOrUpdateDto.getWorkMail())
        .password(adminCreateOrUpdateDto.getPassword())
        .userRoles(adminCreateOrUpdateDto.getUserRoles())
        .build();
  }

}
