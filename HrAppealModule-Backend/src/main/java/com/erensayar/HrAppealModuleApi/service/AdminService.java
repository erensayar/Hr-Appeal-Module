package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.AdminDto;
import com.erensayar.HrAppealModuleApi.model.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin createAdmin(AdminDto adminDto);

    Admin getAdminById(Integer id);

    Admin getAdminByUsername(String username);

    Optional<Admin> getOptAdminByUsername(String username);

    List<Admin> getAdmins();

    Admin updateAdmin(AdminDto adminDto);

    void deleteAdminById(Integer id);

}
