package com.erensayar.HrWebApplicationApi.repo;

import com.erensayar.HrWebApplicationApi.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
}
