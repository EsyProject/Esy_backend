package apiBoschEsy.apiInSpringBoot.Service;

import apiBoschEsy.apiInSpringBoot.entity.ImageData;
import apiBoschEsy.apiInSpringBoot.repository.IStorageRepository;
import apiBoschEsy.apiInSpringBoot.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private IStorageRepository repository;

//    public String uploadImage(MultipartFile file) {
//        ImageData imageData = repository.save(ImageData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .imageData(file.getBytes()));
//        return "file uploaded successfully: " + file.getOriginalFilename();
//    }
//
//    public byte[] downloadImage(String fileName){
//        Optional<ImageData> dbImageData = repository.findByName(fileName);
//        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
//        return images;
//    }

}
