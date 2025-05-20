package com.anstay.repository;

import com.anstay.entity.Apartment;
import com.anstay.enums.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {
    List<Apartment> findByArea(Area area);
    List<Apartment> findByNameContainingIgnoreCase(String name);

    // Bổ sung hàm dưới đây
    Apartment findByName(String name);
    // Hoặc nếu muốn ignore case, lấy kết quả đầu tiên (tốt cho trường hợp name không unique)
    Apartment findFirstByNameIgnoreCase(String name);
}
