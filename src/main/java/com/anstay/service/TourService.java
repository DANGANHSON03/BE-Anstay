package com.anstay.service;

import com.anstay.dto.TourDTO;
import com.anstay.dto.TourScheduleDTO;
import com.anstay.dto.TourImageDTO;
import com.anstay.entity.Tour;
import com.anstay.repository.TourRepository;
import com.anstay.repository.TourScheduleRepository;
import com.anstay.repository.TourImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    @Autowired
    private TourImageRepository tourImageRepository;

    // 🟢 Lấy danh sách tất cả Tour kèm lịch trình và hình ảnh
    public List<TourDTO> getAllTours() {
        List<Tour> tours = tourRepository.findAll();

        return tours.stream().map(tour -> {
            // Lấy danh sách lịch trình
            List<TourScheduleDTO> schedules = tourScheduleRepository.findByTourId(tour.getId()).stream()
                    .map(schedule -> new TourScheduleDTO(
                            schedule.getId(),
                            schedule.getTour().getId(),
                            schedule.getDayNumber(),
                            schedule.getTitle(),
                            schedule.getDescription()
                    )).collect(Collectors.toList());

            // Lấy danh sách hình ảnh
            List<TourImageDTO> images = tourImageRepository.findByTourId(tour.getId()).stream()
                    .map(image -> new TourImageDTO(
                            image.getId(),
                            image.getTour().getId(),
                            image.getImageUrl(),
                            image.isFeatured()
                    )).collect(Collectors.toList());

            return new TourDTO(
                    tour.getId(),
                    tour.getName(),
                    tour.getDescription(),
                    tour.getPrice(),
                    tour.getCheckInDate(),
                    tour.getDurationDays(),
                    tour.getDiscountPercent(),
                    tour.getCreatedAt(),
                    schedules, // 🟢 Gán lịch trình vào DTO
                    images     // 🟢 Gán hình ảnh vào DTO
            );
        }).collect(Collectors.toList());
    }
    public TourDTO getTourById(Integer id) {
        Optional<Tour> optionalTour = tourRepository.findById(id);
        if (optionalTour.isPresent()) {
            Tour tour = optionalTour.get();

            // Lấy danh sách lịch trình của Tour
            List<TourScheduleDTO> schedules = tourScheduleRepository.findByTourId(tour.getId()).stream()
                    .map(schedule -> new TourScheduleDTO(
                            schedule.getId(),
                            schedule.getTour().getId(),
                            schedule.getDayNumber(),
                            schedule.getTitle(),
                            schedule.getDescription()
                    )).collect(Collectors.toList());

            // Lấy danh sách hình ảnh của Tour
            List<TourImageDTO> images = tourImageRepository.findByTourId(tour.getId()).stream()
                    .map(image -> new TourImageDTO(
                            image.getId(),
                            image.getTour().getId(),
                            image.getImageUrl(),
                            image.isFeatured()
                    )).collect(Collectors.toList());

            return new TourDTO(
                    tour.getId(),
                    tour.getName(),
                    tour.getDescription(),
                    tour.getPrice(),
                    tour.getCheckInDate(),
                    tour.getDurationDays(),
                    tour.getDiscountPercent(),
                    tour.getCreatedAt(),
                    schedules, // 🟢 Gán lịch trình vào DTO
                    images     // 🟢 Gán hình ảnh vào DTO
            );
        }
        return null;
    }
}
