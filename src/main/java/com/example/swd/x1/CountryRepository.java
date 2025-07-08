package com.example.swd.x1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.m2.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByRegionId(Integer id);

    Integer countByRegionId(Integer id);
}
