package com.demo.demo.reservations.api;

import com.demo.demo.reservations.db.ReservationFilter;
import com.demo.demo.reservations.domain.Reservation;
import com.demo.demo.reservations.db.ReservationEntity;
import com.demo.demo.reservations.db.ReservationRepository;
import com.demo.demo.reservations.domain.ReservationStatus;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {
    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation getReservationById(Long id) {
        ReservationEntity find = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity with id {} not found"));
        return find.toDomain();
    }

    public List<Reservation> getAllReservations(ReservationFilter filter) {
        int pageSize = filter.pageSize() != null ?
                filter.pageSize() : 10;
        int pageNum = filter.pageNum() != null ?
                filter.pageNum() : 0;
        var pageable = Pageable.ofSize(pageSize).withPage(pageNum);
        List<ReservationEntity> query = repository.findAllByParams(
                filter.roomId(),
                filter.userId(),
                pageable
        );
        return query
                .stream()
                .map(ReservationEntity::toDomain)
                .toList();
    }

    public Reservation createReservation(Reservation reservation) {
        if (reservation.status() != null) {
            throw new IllegalArgumentException("Status should be empty");
        }
        checkReservationDatesValid(reservation);
        ReservationEntity entity = reservation.toEntity();
        entity.setStatus(ReservationStatus.PENDING);
        var saved = repository.save(entity);
        return saved.toDomain();
    }


    public Reservation updateReservation(Long id, Reservation reservation) {
        checkReservationExists(id);
        checkReservationDatesValid(reservation);
        var oldReservation = this.getReservationById(id);
        if (oldReservation.status() != ReservationStatus.PENDING) {
            throw new IllegalStateException("");
        }
        ReservationEntity updatedReservation = reservation.toEntity();
        updatedReservation.setId(id);
        updatedReservation.setStatus(ReservationStatus.PENDING);
        return repository.save(updatedReservation).toDomain();
    }

    public void deleteReservation(Long id) {
        checkReservationExists(id);
        repository.deleteById(id);
    }

    @Transactional
    public Reservation approveReservationById(Long id) {
        repository.setStatus(id, ReservationStatus.APPROVED);
        return getReservationById(id);
    }

    @Transactional
    public Reservation cancelReservationById(Long id) {
        repository.setStatus(id, ReservationStatus.CANCELLED);
        return getReservationById(id);
    }

    private void checkReservationExists(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("");
        }
    }

    private void checkReservationDatesValid(Reservation r) {
        if (!r.endDate().isAfter(r.startDate())) {
            throw new IllegalArgumentException("End Date has to be valid");
        }
    }
}
