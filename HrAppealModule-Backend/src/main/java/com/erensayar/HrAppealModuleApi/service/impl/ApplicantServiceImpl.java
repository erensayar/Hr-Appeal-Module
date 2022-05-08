package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.mapper.ApplicantMapper;
import com.erensayar.HrAppealModuleApi.repo.ApplicantRepo;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import com.erensayar.HrAppealModuleApi.service.util.UtilClass;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicantServiceImpl implements ApplicantService {

  private final ApplicantRepo applicantRepo;
  private final ApplicantMapper applicantMapper;
  private final JobService jobService;
  private final UtilClass utilClass;


  @Override
  public Applicant createApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
    applicantCreateOrUpdateDto.setId("APL" + UUID.randomUUID().toString().replaceAll("-", ""));
    return applicantRepo.save(applicantMapper.dtoToEntity(applicantCreateOrUpdateDto));
  }

  @Override
  public Applicant getApplicantById(String id) {
    return applicantRepo.findById(id).orElseThrow(NoContentException::new);
  }

  @Override
  public List<Applicant> getApplicants() {
    List<Applicant> applicants = applicantRepo.findAll();
    if (applicants.isEmpty()) {
      throw new NoContentException();
    }
    return applicants;
  }

  @Override
  public Applicant updateApplicant(ApplicantCreateOrUpdateDto applicantCreateOrUpdateDto) {
    if (applicantCreateOrUpdateDto.getId() == null) {
      throw new BadRequestException("Id can not be empty");
    }
    return applicantRepo.save(applicantMapper.dtoToEntity(applicantCreateOrUpdateDto));
  }

  @Override
  public void deleteApplicantById(String id) {
    // is exist?
    applicantRepo.findById(id).orElseThrow(NoContentException::new);

    // Remove relation applicant and job
    List<Job> jobs = jobService.getJobs();
    for (Job j : jobs) {
      List<Applicant> applicantsInJob = j.getApplicants();
      applicantsInJob.removeIf(a -> a.getId().equals(id));
    }
    // TODO: This solution is not very good for remove relation.
    //  Look at hibernate tricks. (I think cascade option is not helpful for this situation)

    // Now we can delete
    applicantRepo.deleteById(id);
  }

  @Override
  public Applicant patchApplicant(String id, Map<String, Object> fields) {
    Applicant applicant = utilClass.optEmptyControl(applicantRepo.findById(id));
    fields.forEach((key, value) -> {
      Field field = ReflectionUtils.findRequiredField(Applicant.class, key);
      field.setAccessible(true);
      if (key.equals("cv")) {
        FileAttachment fileAttachment = applicantMapper.getFileAttachment((String) value);
        ReflectionUtils.setField(field, applicant, fileAttachment);
      } else {
        ReflectionUtils.setField(field, applicant, value);
      }
    });
    return applicantRepo.save(applicant);
  }


}
