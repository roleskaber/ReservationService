package com.demo.demo.hotels.api;

import com.demo.demo.hotels.db.HotelEntity;
import com.demo.demo.hotels.db.HotelRoom;
import com.demo.demo.hotels.dto.HotelDto;

import java.util.List;

public interface IHotelService {
    HotelEntity addHotel(HotelDto hotelDto);
    List<HotelEntity> getHotelsByParams();
    HotelEntity getHotelById(Long id);
    void deleteHotel(Long id);
    List<HotelRoom> getRoomsByHotel(Long id);
}
