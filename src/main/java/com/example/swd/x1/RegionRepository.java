package com.example.swd.x1;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.entity.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> findByName(String name);
}
