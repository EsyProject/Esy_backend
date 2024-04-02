package apiBoschEsy.apiInSpringBoot.service;

import apiBoschEsy.apiInSpringBoot.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String fileId) throws Exception;
}
