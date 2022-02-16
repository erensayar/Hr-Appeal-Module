package com.erensayar.HrWebApplicationApi.repo;

import com.erensayar.HrWebApplicationApi.model.entity.FileAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileAttachmentRepo extends JpaRepository<FileAttachment, String> {
}
