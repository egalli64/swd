package com.example.swd.s04;

import org.springframework.data.repository.CrudRepository;

import com.example.swd.dao.Region;

public interface PlainRegionCrudRepo extends CrudRepository<Region, Integer> {
}
