package com.demo.demo.hotels.db;

import com.demo.demo.hotels.domain.HotelAmenities;
import com.demo.demo.hotels.dto.HotelDto;
import jakarta.persistence.*;

import java.util.List;

@Table(name="hotels")
@Entity
public class HotelEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="name")
    String name;
    @Column(name="address")
    String address;
    @Column(name="country")
    String country;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "hotel_id")
    List<HotelRoom> hotelRoom;
    @Column(name = "amenities")
    List<HotelAmenities> amenities;


    public HotelEntity() {
    }

    public HotelEntity(Long id,
                       String name,
                       String address,
                       String country,
                       List<HotelRoom> hotelRoom,
                       List<HotelAmenities> amenities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.country = country;
        this.hotelRoom = hotelRoom;
        this.amenities = amenities;
    }

        public HotelDto toDto() {
        return new HotelDto(
                id, name, address, country, hotelRoom, amenities
        );
    }
}
