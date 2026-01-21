package com.demo.demo.reservations.db;

public record ReservationFilter(
        Long roomId,
        Long userId,
        Integer pageSize,
        Integer pageNum
) {
}
