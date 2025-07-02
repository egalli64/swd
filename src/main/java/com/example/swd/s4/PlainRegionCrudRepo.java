package com.example.swd.s4;

import org.springframework.data.repository.CrudRepository;

import com.example.swd.entity.Region;

public interface PlainRegionCrudRepo extends CrudRepository<Region, Integer> {
}
