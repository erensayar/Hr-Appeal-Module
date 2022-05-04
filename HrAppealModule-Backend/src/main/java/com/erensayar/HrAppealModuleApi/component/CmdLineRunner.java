package com.erensayar.HrAppealModuleApi.component;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.AdminCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.ApplicantCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.dto.request_dto.JobCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Admin;
import com.erensayar.HrAppealModuleApi.model.entity.Applicant;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import com.erensayar.HrAppealModuleApi.model.entity.Job;
import com.erensayar.HrAppealModuleApi.model.enums.ApplicantStatus;
import com.erensayar.HrAppealModuleApi.security.UserRole;
import com.erensayar.HrAppealModuleApi.service.AdminService;
import com.erensayar.HrAppealModuleApi.service.ApplicantService;
import com.erensayar.HrAppealModuleApi.service.FileAttachmentService;
import com.erensayar.HrAppealModuleApi.service.JobService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@SuppressWarnings("PMD")
public class CmdLineRunner implements CommandLineRunner {

  private final AdminService adminService;
  private final ApplicantService applicantService;
  private final JobService jobService;
  private final FileAttachmentService fileAttachmentService;

  @Override
  @Profile("dev")
  public void run(String... args) throws Exception {

    Admin admin1 = adminService.createAdmin(AdminCreateOrUpdateDto.builder()
        .username("JohnSmith")
        .name("John")
        .surname("Smith")
        .personalMail("john.smith@hedehodo.com")
        .workMail("john.smith@company.com")
        .password("Abcd1234")
        .userRoles(new ArrayList<>(List.of(UserRole.ROLE_ADMIN)))
        .build());

    Admin admin2 = adminService.createAdmin(AdminCreateOrUpdateDto.builder()
        .username("EgorMykhailovic")
        .name("Egor")
        .surname("Mykhailovic")
        .personalMail("egorm@hedehodo.com")
        .workMail("egor.mykhailovic@company.com")
        .password("Abcd1234")
        .userRoles(new ArrayList<>(List.of(UserRole.ROLE_ADMIN)))
        .build());

    FileAttachment fileAttachment1 = fileAttachmentService.createFileInfoInDb(
        FileAttachment.builder()
            .name("cv_HaroldStu_job1")
            .filePath("/files/cv")
            .build());

    FileAttachment fileAttachment2 = fileAttachmentService.createFileInfoInDb(
        FileAttachment.builder()
            .name("cv_AragornElessar_job1")
            .filePath("/files/cv")
            .build());

    FileAttachment fileAttachment3 = fileAttachmentService.createFileInfoInDb(
        FileAttachment.builder()
            .name("cv_LegolasHedehede_job2")
            .filePath("/files/cv")
            .build());

    Applicant applicant1 = applicantService.createApplicant(ApplicantCreateOrUpdateDto.builder()
        .name("Harold")
        .surname("Stu")
        .mail("harold.stu@mail.com")
        .telephone("+905335550000")
        .country("Turkey")
        .city("İstanbul")
        .district("Sarıyer")
        .gitLink("github.com/haroldStu")
        .linkedInLink("linkedin.com/in/haroldStu")
        .twitterLink("twitter.com/ehdehödö")
        .cv(fileAttachment1.getId())
        .personalInfoStoragePermission(true)
        .build());

    Applicant applicant2 = applicantService.createApplicant(ApplicantCreateOrUpdateDto.builder()
        .name("Aragorn")
        .surname("Elessar")
        .mail("aragornelessar@mail.com")
        .telephone("+905335550000")
        .country("Turkey")
        .city("İstanbul")
        .district("Sarıyer")
        .gitLink("github.com/aragornelessar")
        .linkedInLink("linkedin.com/in/aragornelessar")
        .twitterLink("twitter.com/ehdehödö")
        .cv(fileAttachment2.getId())
        .personalInfoStoragePermission(true)
        .build());

    Applicant applicant3 = applicantService.createApplicant(ApplicantCreateOrUpdateDto.builder()
        .name("Legolas")
        .surname("hedehödö")
        .mail("legolashawk@mail.com")
        .telephone("+905335550000")
        .country("Turkey")
        .city("İstanbul")
        .district("Beşiktaş")
        .gitLink("github.com/legolasss")
        .linkedInLink("linkedin.com/in/legolasss")
        .twitterLink("twitter.com/ehdehödö")
        .cv(fileAttachment3.getId())
        .personalInfoStoragePermission(true)
        .build());

    List<String> applicantList1 = new ArrayList<>();
    applicantList1.add(applicant1.getId());
    applicantList1.add(applicant2.getId());

    List<String> applicantList2 = new ArrayList<>();
    applicantList2.add(applicant3.getId());

    Job job1 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Java Software Engineer")
        .summary("We searching software engineer to develop mobile app back end module with spring boot, hibernate, maven")
        .description("We search software engineer to develop mobile app back end module with spring boot, hibernate, maven, java 11 hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList1)
        .isArchived(false)
        .location("Ankara-Office")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job2 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("React Front End Developer")
        .summary("We searching React Front End Developer to develop web app front end module with react.js, redux toolkit, axios etc")
        .description("We search software engineer to develop mobile app back end module with spring boot, hibernate, maven, java 11 hede hödö We search software engineer to develop mobile app back end module with spring boot, hibernate, maven, java 11 hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(false)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job3 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer")
        .summary("We searching Vue Front End Developer")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job4 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer4")
        .summary("We searching Vue Front End Developer4")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job5 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer5")
        .summary("We searching Vue Front End Developer5")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job6 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer6")
        .summary("We searching Vue Front End Developer6")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job7 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer7")
        .summary("We searching Vue Front End Developer7")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job8 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer8")
        .summary("We searching Vue Front End Developer8")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());

    Job job9 = jobService.createJob(JobCreateOrUpdateDto.builder()
        .name("Vue Front End Developer9")
        .summary("We searching Vue Front End Developer9")
        .description("hede hödö")
        .expectedQualification("Analytic capabilites hede hödö")
        .numberOfToHire(2)
        .lastApplicationDate(LocalDate.of(2022, 2, 22))
        .applicants(applicantList2)
        .isArchived(true)
        .location("İstanbul-Hybrid")
        .benefits("Multinet")
        .creationDate(LocalDate.of(2022, 2, 1))
        .build());


  }

}
