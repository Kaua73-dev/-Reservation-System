package com.kaua.reservation.entity.repository;


import com.kaua.reservation.dto.response.ResourceResponse;
import com.kaua.reservation.entity.model.Resource;
import com.kaua.reservation.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {


    Optional<Resource> findByNameAndUser(String name, User user);

    List<ResourceResponse> findByName(String name);

    void deleteByName(String name);
}

