package com.example.swd.m3.s4;

import org.springframework.stereotype.Service;

import com.example.swd.m2.entity.Country;
import com.example.swd.m2.entity.Region;

@Service
public class RegionService {
    private final RegionQueryRepository repo;

    public RegionService(RegionQueryRepository repo) {
        this.repo = repo;
    }

    public Region getRegionWithCountries(String name) {
        return repo.findByNameWithCountries(name)
                .orElseThrow(() -> new RuntimeException("Region not found: " + name));
    }

    public Region createRegionWithCountries(Region region) {
        if (region.getCountries() != null) {
            for (Country country : region.getCountries()) {
                country.setRegion(region);
            }
        }

        return repo.save(region);
    }
}
