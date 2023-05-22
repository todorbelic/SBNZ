package com.sbnz.sbnz.repository;

import com.sbnz.sbnz.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
