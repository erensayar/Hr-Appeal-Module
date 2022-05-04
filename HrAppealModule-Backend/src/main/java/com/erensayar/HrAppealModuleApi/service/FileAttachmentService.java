package com.erensayar.HrAppealModuleApi.service;

import com.erensayar.HrAppealModuleApi.model.dto.responseDto.FileAttachmentCreateDto;
import com.erensayar.HrAppealModuleApi.model.entity.FileAttachment;
import org.springframework.web.multipart.MultipartFile;

public interface FileAttachmentService {

  // Save to disk as binary
  FileAttachmentCreateDto saveFile(MultipartFile multipartFile, String applicantName);

  // After run the saveFile() method then createFile() method run for save file information to db
  FileAttachment createFileInfoInDb(FileAttachment fileAttachment);

  FileAttachment getFileById(String id);

}
