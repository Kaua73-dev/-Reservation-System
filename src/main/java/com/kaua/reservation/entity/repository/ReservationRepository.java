package com.kaua.reservation.entity.repository;


import com.kaua.reservation.dto.response.ReservationResponse;
import com.kaua.reservation.entity.model.Reservation;
import com.kaua.reservation.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {


    List<Reservation> findByUser(User user);

}
