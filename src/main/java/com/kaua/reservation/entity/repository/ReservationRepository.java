package com.kaua.reservation.entity.repository;



import com.kaua.reservation.entity.model.Reservation;
import com.kaua.reservation.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUser(User user);

    Optional<Reservation> findByIdAndUser(Integer reservationId, User user);


}
