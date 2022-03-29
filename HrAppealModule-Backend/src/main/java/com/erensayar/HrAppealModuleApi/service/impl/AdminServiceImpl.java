package com.erensayar.HrAppealModuleApi.service.impl;

import com.erensayar.HrAppealModuleApi.error.exception.BadRequestException;
import com.erensayar.HrAppealModuleApi.error.exception.NoContentException;
import com.erensayar.HrAppealModuleApi.model.dto.AdminCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.model.entity.Admin;
import com.erensayar.HrAppealModuleApi.repo.AdminRepo;
import com.erensayar.HrAppealModuleApi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {

    // INJECTION
    //<================================================================================================================>
    private final AdminRepo adminRepo;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // PUBLIC METHODS
    //<================================================================================================================>
    @Override
    public Admin createAdmin(AdminCreateOrUpdateDto adminCreateOrUpdateDto) {
        adminCreateOrUpdateDto.setId(null);
        adminCreateOrUpdateDto.setPassword(passwordEncoder.encode(adminCreateOrUpdateDto.getPassword()));
        return adminRepo.save(this.converterOfEmployee(adminCreateOrUpdateDto));
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminRepo.findById(id).orElseThrow(NoContentException::new);
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminRepo.findByUsername(username).orElseThrow(NoContentException::new);
    }

    @Override
    public Optional<Admin> getOptAdminByUsername(String username) {
        return adminRepo.findByUsername(username);
    }

    @Override
    public List<Admin> getAdmins() {
        return adminRepo.findAll();
    }

    @Override
    public Admin updateAdmin(AdminCreateOrUpdateDto adminCreateOrUpdateDto) {
        if (adminCreateOrUpdateDto.getId() == null)
            throw new BadRequestException("Id can not be empty");
        return adminRepo.save(this.converterOfEmployee(adminCreateOrUpdateDto));
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

    private Admin converterOfEmployee(AdminCreateOrUpdateDto adminCreateOrUpdateDto) {
        return Admin.builder()
                .id(adminCreateOrUpdateDto.getId())
                .username(adminCreateOrUpdateDto.getUsername())
                .name(adminCreateOrUpdateDto.getName())
                .surname(adminCreateOrUpdateDto.getSurname())
                .personalMail(adminCreateOrUpdateDto.getPersonalMail())
                .workMail(adminCreateOrUpdateDto.getWorkMail())
                .password(adminCreateOrUpdateDto.getPassword())
                .build();
    }
}
