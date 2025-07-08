package com.example.swd.x2;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.swd.m2.entity.Country;
import com.example.swd.m2.entity.Region;

@RestController
@RequestMapping("/regions")
public class CustomRegionController {

    private final CustomRegionRepository repo;

    public CustomRegionController(CustomRegionRepository repo) {
        this.repo = repo;
    }

    /**
     * Test
     * 
     * <pre>
     * curl -X GET localhost:8080/regions/with-countries
     * </pre>
     */
    @GetMapping("/with-countries")
    public List<Region> getRegionsWithCountries() {
        return repo.findRegionsWithCountryCount();
    }

    /**
     * Test
     * 
     * <pre>
     * curl -X POST localhost:8080/regions/1/countries -H "Content-Type: application/json" -d "[{\"id\": \"AA\", \"name\": \"Aaa\"}, {\"id\": \"BB\", \"name\": \"Bbb\"}]"
     * </pre>
     */
    @PostMapping("/{regionId}/countries")
    public ResponseEntity<String> addCountriesToRegion( //
            @PathVariable Integer regionId, @RequestBody List<Country> countries) {
        try {
            repo.addCountriesToExistingRegion(regionId, countries);
            return ResponseEntity.ok("Countries successfully added to region " + regionId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add countries: " + e.getMessage());
        }
    }
}
