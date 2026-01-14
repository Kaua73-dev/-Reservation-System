package com.kaua.reservation.entity.model;


import com.kaua.reservation.entity.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="reservations")
public class Reservation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDateTime created_at;

    private LocalDateTime expires_at;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Version
    private Long version;


    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="resource_id", nullable = false)
    private Resource resource;

}
