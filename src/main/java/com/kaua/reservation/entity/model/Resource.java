package com.kaua.reservation.entity.model;


import com.kaua.reservation.entity.enums.ResourceStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="resources")
public class Resource {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name", nullable = false, length = 1000, unique = true)
    private String name;

    @Column(name="category", nullable = false, length = 1000)
    private String category;

    @Column(name="capacity", nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @Version
    private Long version;


}
