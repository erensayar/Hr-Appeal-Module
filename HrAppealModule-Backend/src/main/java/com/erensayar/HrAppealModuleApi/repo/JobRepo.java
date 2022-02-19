package com.erensayar.HrAppealModuleApi.repo;

import com.erensayar.HrAppealModuleApi.model.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer> {

    // Doesnt work :/
    // @Query(value = "SELECT JOB_ID FROM RLT_JOB_APPLICANT WHERE APPLICANT_ID='?'", nativeQuery = true)
    // List<Integer> findAllByApplicantId(String applicantId);

}
