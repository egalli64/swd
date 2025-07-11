package com.example.swd.m3.s2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.m2.entity.Country;

/**
 * Each public query method in a JPA repository is implicitly
 * read-only @Transactional
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findByRegionId(Integer id);

    Integer countByRegionId(Integer id);
}
