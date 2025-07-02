package com.example.swd.x1;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.swd.entity.Region;

@RestController
public class RegionController {
    private final RegionRepository regionRepository;

    public RegionController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    /**
     * Test:
     * 
     * <pre>
     * curl -X GET http://localhost:8080/regions
     * </pre>
     * 
     * @return a JSON with all regions with associated countries
     */
    @GetMapping("/regions")
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    /**
     * Test:
     * 
     * <pre>
     * curl -X POST http://localhost:8080/regions -H "Content-Type: application/json" -d "{\"name\": \"Down Below\"}"
     * </pre>
     * 
     * @param region the JSON representing the region to be persisted, like: {"name":"Down Below"} 
     * @return a JSON like: {"id":5,"name":"Down Below","countries":null}
     */
    @PostMapping("/regions")
    public Region createRegion(@RequestBody Region region) {
        return regionRepository.save(region);
    }
}
