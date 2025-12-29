package com.demo.demo;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReservationService {

    private final Map<Long, Reservation> reservationMap;
    private AtomicLong idCounter = new AtomicLong(0L);

    public ReservationService() {
        this.reservationMap = new HashMap<>();
    }

    public Reservation getReservationById(Long id) {
        if (!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("");
        }
        return reservationMap.get(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationMap.values().stream().toList();
    }

    public Reservation createReservation(
            Reservation reservation
    ) {
        if (reservation.id() != null) {
            throw new IllegalArgumentException("Id should be empty");
        }
        if (reservation.status() != null) {
            throw new IllegalArgumentException("Status should be empty");
        }
        var newReservation = new Reservation(
                idCounter.incrementAndGet(),
                reservation.userId(),
                reservation.roomId(),
                reservation.startDate(),
                reservation.endDate(),
                ReservationStatus.PENDING
        );
        reservationMap.put(newReservation.id(), newReservation);
        return newReservation;
    }


    public Reservation updateReservation(
            Reservation reservation
    ) {
        if (!reservationMap.containsKey(reservation.id())) {
            throw new NoSuchElementException("");
        }
        var oldReservation = reservationMap.get(reservation.id());
        if (oldReservation.status() != ReservationStatus.PENDING) {
            throw new IllegalStateException("");
        }
        reservationMap.put(reservation.id(), reservation);
        return reservation;
    }

    public void deleteReservation(
            Long id
    ) {
        if (!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("");
        }
        reservationMap.remove(id);
    }


}
