package com.demo.demo.hotels.db;

import com.demo.demo.hotels.domain.RoomType;
import jakarta.persistence.*;

@Entity
@Table(name="hotel_rooms")
public class HotelRoom {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="number")
    private Long number;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    public HotelRoom(RoomType type, Long number, Long id) {
        this.type = type;
        this.number = number;
        this.id = id;
    }

    public HotelRoom() {
    }
}
