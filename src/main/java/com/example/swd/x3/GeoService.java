package com.example.swd.x3;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.swd.m2.entity.Country;
import com.example.swd.m2.entity.Region;
import com.example.swd.x1.CountryRepository;
import com.example.swd.x1.RegionRepository;

@Service
public class GeoService {
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    public GeoService(RegionRepository regionRepository, CountryRepository countryRepository) {
        this.regionRepository = regionRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional
    public Region createRegionWithCountries(String regionName, List<Country> countries) {
        // Step 1: Create the region
        Region region = new Region(regionName);
        region = regionRepository.save(region);

        // Step 2: Create and associate countries
        for (Country country : countries) {
            country.setRegion(region);
            countryRepository.save(country);
        }

        // Step 3: Update region with countries
        region.setCountries(countries);
        regionRepository.save(region);

        return region;
    }

    /**
     * Being in a transaction, we guarantee no partial migrations
     */
    @Transactional
    public void moveCountriesToNewRegion(List<String> countryIds, String newRegionName) {
        // Step 1: Create the new region
        Region newRegion = new Region(newRegionName);
        newRegion = regionRepository.save(newRegion);

        // Step 2: Move each country to the new region
        for (String countryId : countryIds) {
            Country country = countryRepository.findById(countryId).orElseThrow( //
                    () -> new RuntimeException("Country not found: " + countryId));

            // Remove from old region
            Region oldRegion = country.getRegion();
            if (oldRegion != null) {
                oldRegion.getCountries().remove(country);
                regionRepository.save(oldRegion);
            }

            // Assign to new region
            country.setRegion(newRegion);
            countryRepository.save(country);
        }

        // Step 3: Validate the new region has countries
        if (countryRepository.countByRegionId(newRegion.getId()) == 0) {
            throw new RuntimeException("No countries were successfully moved");
        }
    }

    @Transactional
    public void exampleWithRollback(String regionName, Country country) {
        regionRepository.save(new Region(regionName));
        countryRepository.save(country);

        throw new RuntimeException("Rollback, something went wrong");
    }

    @Transactional(rollbackFor = Exception.class)
    public void exampleWithCheckedExceptionRollback(String regionName) throws Exception {
        regionRepository.save(new Region(regionName));
        throw new Exception("This will also trigger rollback");
    }

    /**
     * Multiple read operations in same transaction optimized for read-only access
     */
    @Transactional(readOnly = true)
    public String getRegionInfo(Integer regionId) {
        Region region = regionRepository.findById(regionId).orElseThrow(() -> new RuntimeException("Region not found"));
        Integer countryCount = countryRepository.countByRegionId(regionId);

        return String.format("Region %s contains %d countries", region.getName(), countryCount);
    }
}
