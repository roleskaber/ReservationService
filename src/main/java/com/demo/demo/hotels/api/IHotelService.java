package com.demo.demo.hotels.api;

import com.demo.demo.hotels.db.HotelEntity;
import com.demo.demo.hotels.db.HotelRoomEntity;
import com.demo.demo.hotels.dto.HotelDto;

import java.util.List;

public interface IHotelService {
    HotelEntity addHotel(HotelDto hotelDto);
    List<HotelEntity> getHotelsByParams();
    List<HotelEntity> doElasticSearch(String hint, int size, int page);
    HotelEntity getHotelById(Long id);
    void deleteHotel(Long id);
    List<HotelRoomEntity> getRoomsByHotel(Long id);
}
