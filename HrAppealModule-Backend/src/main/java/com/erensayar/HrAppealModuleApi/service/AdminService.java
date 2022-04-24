package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.request_dto.AdminCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    Admin createAdmin(AdminCreateOrUpdateDto adminCreateOrUpdateDto);

    Admin getAdminById(Integer id);

    Admin getAdminByUsername(String username);

    Optional<Admin> getOptAdminByUsername(String username);

    List<Admin> getAdmins();

    Admin updateAdmin(AdminCreateOrUpdateDto adminCreateOrUpdateDto);

    void deleteAdminById(Integer id);

}
