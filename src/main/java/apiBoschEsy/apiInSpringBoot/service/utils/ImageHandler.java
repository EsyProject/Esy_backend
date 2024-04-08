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
            // Retornar o caminho completo do arquivo
            return String.format("%s/images/%s", uploadDir, fileName);
        } else {
            return null; // Ou você pode retornar uma string vazia ou tratar esse caso de outra forma
        }
    }

}