package com.erensayar.HrAppealModuleApi.model.mapper;

import com.erensayar.HrAppealModuleApi.model.dto.JobPublicDto;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperOfJob {

  //Job toEntity(JobCreateOrUpdateDto jobCreateOrUpdateDto);

  JobPublicDto toJobPublicDto(Job job);

  List<JobPublicDto> toJobPublicDtoList(List<Job> jobs);

}
