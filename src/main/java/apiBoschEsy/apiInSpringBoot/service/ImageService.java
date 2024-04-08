package apiBoschEsy.apiInSpringBoot.service;

import apiBoschEsy.apiInSpringBoot.entity.Event;
import apiBoschEsy.apiInSpringBoot.entity.Image;
import apiBoschEsy.apiInSpringBoot.service.utils.ImageHandler;
import apiBoschEsy.apiInSpringBoot.repository.IRepositoryImage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageHandler imageHandler;

    @Autowired
    private IRepositoryImage imageRepository;

    @Transactional

    public List<String> saveImages(Event product, List<MultipartFile> images) {
        List<String> imagesUrlList = new ArrayList<>();
        String imgUrl;
        Image image;
        for (MultipartFile imageMtp : images) {
            imgUrl = imageHandler.saveImageToUploadDir(imageMtp);
            imagesUrlList.add(imgUrl);
            image = new Image(imgUrl, product);
            imageRepository.save(image);
        }

        return imagesUrlList;
    }
}