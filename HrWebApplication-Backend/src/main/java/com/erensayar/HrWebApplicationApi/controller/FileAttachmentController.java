package com.erensayar.HrWebApplicationApi.controller;

import com.erensayar.HrWebApplicationApi.model.entity.FileAttachment;
import com.erensayar.HrWebApplicationApi.service.FileAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@RestController
public class FileAttachmentController {

    private final FileAttachmentService fileAttachmentService;

    // TODO: set consumes
    //@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping(value = "/upload")
    FileAttachment saveFile(@RequestPart("file") MultipartFile file,
                            @RequestHeader("applicant-id") String applicantId) {
        return fileAttachmentService.saveFile(file, applicantId);
    }

}
