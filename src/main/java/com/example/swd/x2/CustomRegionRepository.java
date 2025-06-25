package com.example.swd.x2;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.swd.dao.Country;
import com.example.swd.dao.Region;

import jakarta.persistence.EntityManager;

@Repository
@Transactional
public class CustomRegionRepository {
    /** Spring takes care of using the entity manager in a thread safe way */
    private final EntityManager entityManager;

    public CustomRegionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Non-class standard transaction, specific annotation.
     */
    @Transactional(readOnly = true)
    public List<Region> findRegionsWithCountryCount() {
        return entityManager.createQuery("SELECT r FROM Region r LEFT JOIN FETCH r.countries", Region.class)
                .getResultList();
    }

    /**
     * Being a public method, is write @Transactional as the class specifies
     * <p>
     * Single transaction, ACID behavior. In case of failure, full roll back
     */
    public void addCountriesToExistingRegion(Integer regionId, List<Country> countries) {
        // Find the region within the transaction
        Region region = entityManager.find(Region.class, regionId);
        if (region == null) {
            throw new RuntimeException("Region not found with id: " + regionId);
        }

        for (Country country : countries) {
            country.setRegion(region);
            entityManager.persist(country);
        }
    }
}
