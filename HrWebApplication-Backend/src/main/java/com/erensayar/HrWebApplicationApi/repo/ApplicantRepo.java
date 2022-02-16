package com.erensayar.HrWebApplicationApi.repo;

import com.erensayar.HrWebApplicationApi.model.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantRepo extends JpaRepository<Applicant, String> {

    //List<Applicant> findAllByJobs(Job job);

}
