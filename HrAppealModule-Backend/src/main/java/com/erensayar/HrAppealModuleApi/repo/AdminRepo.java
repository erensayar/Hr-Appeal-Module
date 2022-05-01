package com.erensayar.HrAppealModuleApi.repo;

import com.erensayar.HrAppealModuleApi.model.entity.Admin;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

  Optional<Admin> findByUsername(String username);

}
