package com.erensayar.HrWebApplicationApi.service;

import com.erensayar.HrWebApplicationApi.model.dto.AdminDto;
import com.erensayar.HrWebApplicationApi.model.entity.Admin;

import java.util.List;

public interface AdminService {

    Admin createAdmin(AdminDto adminDto);

    Admin getAdminById(Integer id);

    List<Admin> getAdmins();

    Admin updateAdmin(AdminDto adminDto);

    void deleteAdminById(Integer id);

}
