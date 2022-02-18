package com.erensayar.HrWebApplicationApi.service.impl;

import com.erensayar.HrWebApplicationApi.error.exception.BadRequestException;
import com.erensayar.HrWebApplicationApi.error.exception.NoContentException;
import com.erensayar.HrWebApplicationApi.model.dto.AdminDto;
import com.erensayar.HrWebApplicationApi.model.entity.Admin;
import com.erensayar.HrWebApplicationApi.repo.AdminRepo;
import com.erensayar.HrWebApplicationApi.service.AdminService;
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
    public Admin createAdmin(AdminDto adminDto) {
        adminDto.setId(null);
        adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        return adminRepo.save(this.converterOfEmployee(adminDto));
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
    public Admin updateAdmin(AdminDto adminDto) {
        if (adminDto.getId() == null)
            throw new BadRequestException("Id can not be empty");
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
                .username(adminDto.getUsername())
                .name(adminDto.getName())
                .surname(adminDto.getSurname())
                .personalMail(adminDto.getPersonalMail())
                .workMail(adminDto.getWorkMail())
                .password(adminDto.getPassword())
                .build();
    }
}
