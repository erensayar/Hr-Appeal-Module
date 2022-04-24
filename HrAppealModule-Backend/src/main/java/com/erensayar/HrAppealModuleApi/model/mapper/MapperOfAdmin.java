package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.AdminCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOfAdmin {

  Admin toEntity(AdminCreateOrUpdateDto adminCreateOrUpdateDto);

}
