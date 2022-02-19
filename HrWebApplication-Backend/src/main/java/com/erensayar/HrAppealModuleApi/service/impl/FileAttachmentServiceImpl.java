package com.erensayar.HrWebApplicationApi.service.impl;

import com.erensayar.HrWebApplicationApi.configuration.FileUploadConfig;
import com.erensayar.HrWebApplicationApi.error.exception.BadRequestException;
import com.erensayar.HrWebApplicationApi.error.exception.InternalServerErrorException;
import com.erensayar.HrWebApplicationApi.error.exception.NoContentException;
import com.erensayar.HrWebApplicationApi.error.exception.NotFoundException;
import com.erensayar.HrWebApplicationApi.model.entity.Applicant;
import com.erensayar.HrWebApplicationApi.model.entity.FileAttachment;
import com.erensayar.HrWebApplicationApi.model.enums.AllowedFileTypes;
import com.erensayar.HrWebApplicationApi.repo.FileAttachmentRepo;
import com.erensayar.HrWebApplicationApi.service.ApplicantService;
import com.erensayar.HrWebApplicationApi.service.FileAttachmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileAttachmentServiceImpl implements FileAttachmentService {

    // INJECTIONS
    //<================================================================================================================>
    private final FileAttachmentRepo fileAttachmentRepo;
    private final FileUploadConfig fileUploadConfig;

    private ApplicantService applicantService;

    @Autowired
    public void setApplicantService(ApplicantService applicantService) {
        this.applicantService = applicantService;
    }

    // PUBLIC METHODS
    //<================================================================================================================>
    @Override
    public FileAttachment saveFile(MultipartFile multipartFile, String applicantId) {

        // I used try/catch because 204 has no body.
        // If we had 204 then throw 404 for this situation. This more understandable.
        Applicant applicant = null;
        String applicantName = null;
        try {
            applicant = applicantService.getApplicantById(applicantId);
            applicantName = applicant.getName() + "_" + applicant.getSurname();
        } catch (NoContentException e) {
            throw new NotFoundException("Send Valid Applicant Id from header!");
        }

        try {
            Tika tika = new Tika();

            // Detect file type
            byte[] arr = multipartFile.getBytes();
            String detectedFileType = tika.detect(arr);

            // File format control (PDF)
            if (isAllowedFileType(detectedFileType)) {

                // Constants
                String fileName = "CV" + applicantName.replaceAll(" ", "") + "-" + applicantId;
                String storagePath = fileUploadConfig.getStoragePath() + "/cv/";
                String storagePathPlusFileName = storagePath + fileName + ".pdf";

                // FileAttachment object PREPARE for save the file information to DB
                FileAttachment attachment = new FileAttachment();
                attachment.setId("CV" + UUID.randomUUID().toString().replaceAll("-", ""));
                attachment.setName(fileName);
                attachment.setFilePath(storagePathPlusFileName);

                // Directory control. If not exist create directory
                if (!this.dirExistControl(storagePath)) {
                    this.createDirectories(storagePath);
                }

                File file = new File(storagePathPlusFileName);

                // Create File & Duplicate Control
                boolean isFileCreated = file.createNewFile();
                if (!isFileCreated) { // Duplicate condition
                    log.debug("File already exist. Deleted old file. And saved new file. File Name: " + fileName);
                    this.deleteFileFromHardDrive(storagePathPlusFileName);
                    attachment.setId(applicant.getCv().getId()); // Written to not save new file. this provide to update
                    this.updateFileAttachmentName(attachment); // update file name in db
                    // File create try again
                    if (!file.createNewFile()) {
                        throw new InternalServerErrorException("IO Exception. Data writing failed. ");
                    }
                }

                // Write file to hard drive
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(arr);
                fos.close();

                return this.createFileInfoInDb(attachment);
            } else {
                throw new BadRequestException("Just pdf format is acceptable.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException("IO Exception. Data write process is failed ");
        }
    }

    @Override
    public FileAttachment createFileInfoInDb(FileAttachment fileAttachment) {
        if (fileAttachment.getId() == null)
            fileAttachment.setId("CV" + UUID.randomUUID().toString().replaceAll("-", ""));
        fileAttachment.setCreateOrUpdateTime(LocalDateTime.now());
        return fileAttachmentRepo.save(fileAttachment);
    }

    @Override
    public FileAttachment getFileById(String id) {
        return fileAttachmentRepo.findById(id).orElseThrow(NoContentException::new);
    }

    private void updateFileAttachmentName(FileAttachment fileAttachmentUpdated) {
        FileAttachment fileInDb = this.getFileById(fileAttachmentUpdated.getId());
        fileInDb.setFilePath(fileAttachmentUpdated.getFilePath());
        fileAttachmentUpdated.setCreateOrUpdateTime(LocalDateTime.now());
        fileAttachmentRepo.save(fileAttachmentUpdated);
    }


    // PRIVATE METHODS
    //<================================================================================================================>

    /**
     * Delete From DB. It's not delete from hard drive!
     */
    private void deleteFileById(String id) {
        fileAttachmentRepo.deleteById(id);
    }

    private Boolean isAllowedFileType(String fileType) {
        fileType = fileType.substring(fileType.indexOf("/") + 1);
        List<AllowedFileTypes> fileTypeList = new ArrayList<>(Arrays.asList(AllowedFileTypes.values()));
        for (AllowedFileTypes type : fileTypeList) {
            if (fileType.equalsIgnoreCase(type.toString())) {
                return true;
            }
        }
        return false;
    }

    private void deleteFileFromHardDrive(String filePath) {
        Path path = Paths.get(filePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.debug("Delete File IO Exception: " + e);
        }
    }

    private Boolean dirExistControl(String path) {
        Path target = Paths.get(path);
        return Files.isDirectory(target);
    }

    private void createDirectories(String path) {
        Path target = Paths.get(path);
        try {
            Files.createDirectories(target);
        } catch (IOException e) {
            log.debug("Create Directories IO Exception: " + e);
        }
    }

}
