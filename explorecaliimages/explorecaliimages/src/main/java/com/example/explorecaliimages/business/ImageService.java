package com.example.explorecaliimages.business;


import com.example.explorecaliimages.model.IdName;
import com.example.explorecaliimages.model.Image;

import com.example.explorecaliimages.repo.ImageRepository;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.util.List;
import java.util.Optional;
/**
 * Business Service
 */
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public Optional<Image> getImage(String id) {
        return imageRepository.findById(id);
    }

    public Optional<Image> findByName(String name) {
        return imageRepository.findByFileName(name);
    }

    public List<IdName> findIdNames() {
        return imageRepository.findIdNameBy();
    }
}