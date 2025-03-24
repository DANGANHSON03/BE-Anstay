package com.anstay.dto;

public class TourScheduleDTO {
    private Integer id;
    private Integer tourId;
    private Integer dayNumber;
    private String title;
    private String description;

    public TourScheduleDTO(Integer id, Integer tourId, Integer dayNumber, String title, String description) {
        this.id = id;
        this.tourId = tourId;
        this.dayNumber = dayNumber;
        this.title = title;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
