package com.erensayar.HrWebApplicationApi.service.impl;

import com.erensayar.HrWebApplicationApi.error.exception.BadRequestException;
import com.erensayar.HrWebApplicationApi.error.exception.NoContentException;
import com.erensayar.HrWebApplicationApi.model.dto.AdminDto;
import com.erensayar.HrWebApplicationApi.model.entity.Admin;
import com.erensayar.HrWebApplicationApi.repo.AdminRepo;
import com.erensayar.HrWebApplicationApi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    // INJECTION
    //<================================================================================================================>
    private final AdminRepo adminRepo;

    @Override
    public Admin createAdmin(AdminDto adminDto) {
        adminDto.setId(null);
        return adminRepo.save(this.converterOfEmployee(adminDto));
    }

    // PUBLIC METHODS
    //<================================================================================================================>

    @Override
    public Admin getAdminById(Integer id) {
        return adminRepo.findById(id).orElseThrow(NoContentException::new);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin updateAdmin(AdminDto adminDto) {
        if (adminDto.getId() == null)
            throw new BadRequestException("Id cannot be empty");
        return adminRepo.save(this.converterOfEmployee(adminDto));
    }

    @Override
    public void deleteAdminById(Integer id) {
        if (adminRepo.findById(id).isPresent()) {
            adminRepo.deleteById(id);
        } else {
            throw new NoContentException();
        }
    }

    // PRIVATE METHODS
    //<================================================================================================================>

    private Admin converterOfEmployee(AdminDto adminDto) {
        return Admin.builder()
                .id(adminDto.getId())
                .name(adminDto.getName())
                .surname(adminDto.getSurname())
                .personalMail(adminDto.getPersonalMail())
                .workMail(adminDto.getWorkMail())
                .password(adminDto.getPassword())
                .build();
    }
}
