package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Random;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByAppUser_Id(Long userId);

}
