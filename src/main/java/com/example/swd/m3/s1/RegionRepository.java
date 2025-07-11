package com.example.swd.m3.s1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.m2.entity.Region;

/**
 * Each public query method in a JPA repository is implicitly
 * read-only @Transactional
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> findByName(String name);
}
