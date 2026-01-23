package com.demo.demo.hotels.dto;

import com.demo.demo.hotels.db.HotelEntity;
import com.demo.demo.hotels.domain.HotelAmenities;
import com.demo.demo.hotels.db.HotelRoom;

import java.util.List;

public record HotelDto(
        Long id,
        String name,
        String address,
        String country,
        List<HotelRoom> rooms,
        List<HotelAmenities> amenities
) {
    public HotelEntity toEntity() {
        return new HotelEntity(
                null, name, address, country, rooms, amenities
        );
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", hotelRoom=" + rooms +
                ", amenities=" + amenities +
                '}';

    }
}
