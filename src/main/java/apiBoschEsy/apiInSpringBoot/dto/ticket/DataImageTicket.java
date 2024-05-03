package apiBoschEsy.apiInSpringBoot.dto.ticket;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record DataImageTicket(
        List<MultipartFile> images
) {

}
