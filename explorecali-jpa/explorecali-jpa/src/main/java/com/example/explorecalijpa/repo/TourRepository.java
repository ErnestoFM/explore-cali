package com.example.explorecalijpa.repo;

import com.example.explorecalijpa.model.Difficulty;
import com.example.explorecalijpa.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer> {

    List<Tour> findByDifficulty(Difficulty difficulty);

    List<Tour> findByTourPackageCode(String code);
}
