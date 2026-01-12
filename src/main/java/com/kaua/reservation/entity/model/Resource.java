package com.kaua.reservation.entity.model;


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

    private String name;

    private String category;

    private int capacity;


    @Enumerated(EnumType.STRING)
    private ResourceStatus status;

    @Version
    private Long version;


}
