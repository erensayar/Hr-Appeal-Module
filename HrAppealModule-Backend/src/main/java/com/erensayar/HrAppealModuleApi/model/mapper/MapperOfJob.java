package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.response_dto.GetJobPublicDto;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOfJob {

  //Job toEntity(JobCreateOrUpdateDto jobCreateOrUpdateDto);

  GetJobPublicDto toJobPublicDto(Job job);

  List<GetJobPublicDto> toJobPublicDtoList(List<Job> jobs);

}
