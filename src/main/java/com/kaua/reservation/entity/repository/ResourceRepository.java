package com.kaua.reservation.entity.repository;


import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {


    Optional<Resource> findByUserAndName(User user, String name);



}

