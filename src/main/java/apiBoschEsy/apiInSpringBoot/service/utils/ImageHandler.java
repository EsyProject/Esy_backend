package apiBoschEsy.apiInSpringBoot.service.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Component
public class ImageHandler {

    @Value("${upload.dir}")
    private String uploadDir;
    private final String baseUrl = "http://localhost:6967";


    public String saveImageToUploadDir(MultipartFile multipartImage) {
        String fileName = "";
        if(multipartImage !=null && !multipartImage.isEmpty()) {
            try {
                fileName = String.format("%s-%s", System.currentTimeMillis(), Objects.requireNonNull(multipartImage.getOriginalFilename()).replace(" ", "-"));
                Path imgPath = Paths.get(uploadDir, fileName);
                Files.write(imgPath, multipartImage.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return String.format("%s/images/%s", baseUrl, fileName);
        } else {
            return null;
        }
    }

}