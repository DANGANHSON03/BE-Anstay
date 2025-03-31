package com.anstay.service;

import com.anstay.dto.ApartmentDTO;
import com.anstay.dto.ApartmentImageDTO;
import com.anstay.dto.ApartmentOwnerDTO;
import com.anstay.entity.Apartment;
import com.anstay.entity.ApartmentImage;
import com.anstay.entity.ApartmentOwner;
import com.anstay.enums.Area;
import com.anstay.repository.ApartmentOwnerRepository;
import com.anstay.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private ApartmentOwnerRepository apartmentOwnerRepository;

    // Lấy danh sách tất cả căn hộ
    public List<ApartmentDTO> getAllApartments() {
        return apartmentRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Lấy căn hộ theo ID
    public ApartmentDTO getApartmentById(Integer id) {
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        return apartment.map(this::convertToDTO).orElse(null);
    }

    // Thêm mới căn hộ
    public ApartmentDTO createApartment(ApartmentDTO dto) {
        ApartmentOwner owner = apartmentOwnerRepository.findById(dto.getOwnerId()).orElse(null);
        if (owner == null) return null; // Tránh lỗi nếu chủ sở hữu không tồn tại

        Apartment apartment = new Apartment();
        apartment.setName(dto.getName());
        apartment.setLocation(dto.getLocation());
        apartment.setOwner(owner);
        apartment.setPricePerDay(dto.getPricePerDay());
        apartment.setPricePerMonth(dto.getPricePerMonth());
        apartment.setDiscountPercent(dto.getDiscountPercent());
        apartment.setDescription(dto.getDescription());
        apartment.setMaxAdults(dto.getMaxAdults());
        apartment.setMaxChildren(dto.getMaxChildren());
        apartment.setNumRooms(dto.getNumRooms());

        Apartment savedApartment = apartmentRepository.save(apartment);
        return convertToDTO(savedApartment);
    }

    // Cập nhật căn hộ
    public ApartmentDTO updateApartment(Integer id, ApartmentDTO dto) {
        Optional<Apartment> optionalApartment = apartmentRepository.findById(id);
        if (optionalApartment.isPresent()) {
            Apartment apartment = optionalApartment.get();
            ApartmentOwner owner = apartmentOwnerRepository.findById(dto.getOwnerId()).orElse(null);
            if (owner == null) return null;

            apartment.setName(dto.getName());
            apartment.setLocation(dto.getLocation());
            apartment.setOwner(owner);
            apartment.setPricePerDay(dto.getPricePerDay());
            apartment.setPricePerMonth(dto.getPricePerMonth());
            apartment.setDiscountPercent(dto.getDiscountPercent());
            apartment.setDescription(dto.getDescription());
            apartment.setMaxAdults(dto.getMaxAdults());
            apartment.setMaxChildren(dto.getMaxChildren());
            apartment.setNumRooms(dto.getNumRooms());
            apartment.setStatus(dto.getStatus());
            Apartment updatedApartment = apartmentRepository.save(apartment);
            return convertToDTO(updatedApartment);
        }
        return null;
    }

    // Xóa căn hộ
    public boolean deleteApartment(Integer id) {
        if (apartmentRepository.existsById(id)) {
            apartmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Convert Entity to DTO
    private ApartmentDTO convertToDTO(Apartment apartment) {
        // Chuyển đổi danh sách chủ sở hữu (nếu có nhiều chủ sở hữu, hãy sửa lại kiểu dữ liệu)
        List<ApartmentOwnerDTO> ownerDTOs = List.of(
                new ApartmentOwnerDTO(
                        apartment.getOwner().getId(),
                        apartment.getOwner().getName(),
                        apartment.getOwner().getPhone(),
                        apartment.getOwner().getEmail(),
                        apartment.getOwner().getAddress()
                )
        );

        // Chuyển đổi danh sách ảnh (fix lỗi lấy danh sách ảnh thay vì chỉ một ảnh)
        List<ApartmentImageDTO> imageDTOS = apartment.getImages().stream()
                .map(image -> new ApartmentImageDTO(
                        image.getId(),
                        apartment.getId(),
                        image.getImageUrl(),
                        image.isFeatured()
                ))
                .toList(); // Chuyển về danh sách List

        return new ApartmentDTO(
                apartment.getId(),
                apartment.getName(),
                apartment.getLocation(),
                apartment.getOwner().getId(),
                apartment.getPricePerDay(),
                apartment.getPricePerMonth(),
                apartment.getDiscountPercent(),
                apartment.getDescription(),
                apartment.getMaxAdults(),
                apartment.getMaxChildren(),
                apartment.getNumRooms(),
                apartment.getStatus(),
                ownerDTOs,
                imageDTOS,
                apartment.getArea()// Thêm danh sách ảnh vào DTO
        );
    }

    public List<ApartmentDTO> getApartmentsByArea(Area area) {
        List<Apartment> apartments = apartmentRepository.findByArea(area);
        return apartments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}
