package com.example.explorecaliimages.repo;

import com.example.explorecaliimages.model.IdName;
import com.example.explorecaliimages.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends MongoRepository<Image, String> {
        Optional<Image> findByFileName(String fileName);

        List<IdName> findIdNameBy();
}
