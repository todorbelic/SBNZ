package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByAppUserId(Long appUserId);

}
