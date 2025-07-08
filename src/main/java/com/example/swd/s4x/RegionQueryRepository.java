package com.example.swd.s4x;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.swd.m2.entity.Region;

public interface RegionQueryRepository extends JpaRepository<Region, Integer> {
    @Query("SELECT r FROM Region r LEFT JOIN FETCH r.countries WHERE r.name = :name")
    Optional<Region> findByNameWithCountries(@Param("name") String name);

    Optional<Region> findByName(String name);
}
