package com.erensayar.HrAppealModuleApi.controller;

import com.erensayar.HrAppealModuleApi.service.FileAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@RestController
public class FileAttachmentController {

  private final FileAttachmentService fileAttachmentService;

  @PostMapping(value = "/upload",
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> saveFile(
      @RequestPart("file") MultipartFile file,
      @RequestHeader("applicant-id") String applicantId) {
    return ResponseEntity.ok(fileAttachmentService.saveFile(file, applicantId));
  }

}
