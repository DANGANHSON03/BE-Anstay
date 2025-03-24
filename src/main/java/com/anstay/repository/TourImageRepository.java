package com.anstay.repository;

import com.anstay.entity.TourImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourImageRepository extends JpaRepository<TourImage, Integer> {
    List<TourImage> findByTourId(Integer tourId);
}
