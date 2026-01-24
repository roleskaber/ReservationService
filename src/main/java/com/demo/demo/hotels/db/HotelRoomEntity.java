package com.demo.demo.hotels.db;

import com.demo.demo.hotels.domain.RoomType;
import jakarta.persistence.*;

@Entity
@Table(name="hotel_rooms")
public class HotelRoomEntity {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name="number")
    private Long number;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    public HotelRoomEntity(RoomType type, Long number, Long id) {
        this.type = type;
        this.number = number;
        this.id = id;
    }

    public HotelRoomEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
