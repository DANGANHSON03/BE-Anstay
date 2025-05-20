package com.anstay.repository;

import com.anstay.dto.RoomDTO;
import com.anstay.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findByApartmentId(Long apartmentId);

    @Query("SELECT new com.anstay.dto.RoomDTO(" +
            "r.id, " +
            "r.apartmentId, " +
            "r.name, " +
            "r.description, " +
            "r.capacity, " +
            "r.price, " +
            "r.maxRooms, " +
            "r.maxAdults, " +
            "r.maxChildren, " +      // <- phải có dấu phẩy ở đây!
            "r.discount" +
            ") " +
            "FROM Room r WHERE r.apartmentId = :apartmentId")
    List<RoomDTO> findRoomDTOByApartmentId(@Param("apartmentId") Long apartmentId);


}
