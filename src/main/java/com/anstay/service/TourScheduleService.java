package com.anstay.service;

import com.anstay.dto.TourScheduleDTO;
import com.anstay.entity.Tour;
import com.anstay.entity.TourSchedule;
import com.anstay.repository.TourRepository;
import com.anstay.repository.TourScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TourScheduleService {

    @Autowired
    private TourScheduleRepository tourScheduleRepository;

    @Autowired
    private TourRepository tourRepository;

    // 🟢 Lấy danh sách lịch trình theo tour_id
    public List<TourScheduleDTO> getSchedulesByTourId(Integer tourId) {
        List<TourSchedule> schedules = tourScheduleRepository.findByTourId(tourId);
        return schedules.stream().map(schedule -> new TourScheduleDTO(
                schedule.getId(),
                schedule.getTour().getId(),
                schedule.getDayNumber(),
                schedule.getTitle(),
                schedule.getDescription()
        )).collect(Collectors.toList());
    }

    // 🟢 Thêm lịch trình mới
    public TourScheduleDTO addTourSchedule(TourScheduleDTO dto) {
        Optional<Tour> optionalTour = tourRepository.findById(dto.getTourId());
        if (optionalTour.isPresent()) {
            TourSchedule schedule = new TourSchedule();
            schedule.setTour(optionalTour.get());
            schedule.setDayNumber(dto.getDayNumber());
            schedule.setTitle(dto.getTitle());
            schedule.setDescription(dto.getDescription());

            TourSchedule savedSchedule = tourScheduleRepository.save(schedule);
            return new TourScheduleDTO(
                    savedSchedule.getId(),
                    savedSchedule.getTour().getId(),
                    savedSchedule.getDayNumber(),
                    savedSchedule.getTitle(),
                    savedSchedule.getDescription()
            );
        }
        return null;
    }

    // 🟢 Xóa lịch trình theo ID
    public boolean deleteTourSchedule(Integer id) {
        if (tourScheduleRepository.existsById(id)) {
            tourScheduleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
