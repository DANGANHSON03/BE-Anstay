package com.anstay.service;

import com.anstay.dto.TourImageDTO;
import com.anstay.entity.Tour;
import com.anstay.entity.TourImage;
import com.anstay.repository.TourImageRepository;
import com.anstay.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourImageService {

    @Autowired
    private TourImageRepository tourImageRepository;

    @Autowired
    private TourRepository tourRepository;

    // 🟢 Lấy danh sách ảnh theo tour_id
    public List<TourImageDTO> getImagesByTourId(Integer tourId) {
        return tourImageRepository.findByTourId(tourId) // Gọi method từ repository
                .stream()
                .map(img -> new TourImageDTO(
                        img.getId(),
                        img.getTour().getId(),
                        img.getImageUrl(),
                        img.getIsFeatured()))
                .collect(Collectors.toList());
    }

    // 🟢 Thêm ảnh mới vào tour
    public TourImageDTO addTourImage(TourImageDTO tourImageDTO) {
        Optional<Tour> tour = tourRepository.findById(tourImageDTO.getTourId());
        if (tour.isPresent()) {
            TourImage image = new TourImage();
            image.setTour(tour.get());
            image.setImageUrl(tourImageDTO.getImageUrl());
            image.setIsFeatured(tourImageDTO.getIsFeatured());

            TourImage savedImage = tourImageRepository.save(image);
            return new TourImageDTO(
                    savedImage.getId(),
                    savedImage.getTour().getId(),
                    savedImage.getImageUrl(),
                    savedImage.getIsFeatured()
            );
        }
        return null;
    }

    // 🟢 Xóa ảnh theo ID
    public boolean deleteTourImage(Integer id) {
        if (tourImageRepository.existsById(id)) {
            tourImageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
