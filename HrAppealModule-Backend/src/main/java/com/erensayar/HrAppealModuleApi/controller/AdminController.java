package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.model.dto.requestDto.AdminCreateOrUpdateDto;
import com.erensayar.HrAppealModuleApi.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
@RestController
public class AdminController {

  private final AdminService adminService;

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAdminById(@PathVariable("id") Integer id) {
    return new ResponseEntity<>(adminService.getAdminById(id), HttpStatus.OK);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> getAdmins() {
    return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.OK);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createAdmin(@RequestBody AdminCreateOrUpdateDto adminCreateOrUpdateDto) {
    return new ResponseEntity<>(adminService.createAdmin(adminCreateOrUpdateDto), HttpStatus.CREATED);
  }

  @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateAdmin(@RequestBody AdminCreateOrUpdateDto updatedAdmin) {
    return new ResponseEntity<>(adminService.updateAdmin(updatedAdmin), HttpStatus.OK);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> deleteAdminById(@PathVariable("id") Integer id) {
    adminService.deleteAdminById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}