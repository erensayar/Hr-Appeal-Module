package com.erensayar.HrWebApplicationApi.service;

import com.erensayar.HrWebApplicationApi.model.entity.FileAttachment;
import org.springframework.web.multipart.MultipartFile;

public interface FileAttachmentService {

    // Save to disk as binary
    FileAttachment saveFile(MultipartFile multipartFile, String applicantName);

    // After run the saveFile() method then createFile() method run for save file information to db
    FileAttachment createFileInfoInDb(FileAttachment fileAttachment);

    FileAttachment getFileById(String id);

    //FileAttachment updateFileAttachment(FileAttachment fileAttachment);

    //Boolean fileTypeIsAllowed();

}