package com.demo.demo.hotels.api;

import com.demo.demo.hotels.db.HotelEntity;
import com.demo.demo.hotels.db.HotelRepository;
import com.demo.demo.hotels.db.HotelRoom;
import com.demo.demo.hotels.dto.HotelDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService implements IHotelService {
    private final Logger logger = LoggerFactory.getLogger(HotelService.class);
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    @Transactional
    public HotelEntity addHotel(HotelDto hotelDto) {
        logger.info("lil: {}", hotelDto);
        return hotelRepository.save(hotelDto.toEntity());
    }

    @Override
    public List<HotelEntity> getHotelsByParams() {
        return List.of();
    }

    @Override
    @Transactional
    public HotelEntity getHotelById(Long id) {
        return hotelRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id {} not found"));
    }

    @Override
    @Transactional
    public void deleteHotel(Long id) {
        this.hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<HotelRoom> getRoomsByHotel(Long id) {
        return this.getHotelById(id).toDto().rooms();
    }
}
