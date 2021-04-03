package com.example.swd.s05;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.dao.Coder;

@Repository
public interface CoderQueryRepo extends CrudRepository<Coder, Integer> {
    @Query("select c from Coder c where c.salary between ?1 and ?2 order by salary desc, hireDate desc")
    Iterable<Coder> findBySalaryRange(double low, double high);
}
