package com.example.swd.s06;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.dao.Coder;

@Repository
public interface CoderQueryRepo extends CrudRepository<Coder, Integer> {
    @Query("select c from Coder c where c.salary between ?1 and ?2 order by salary desc, hireDate desc")
    Iterable<Coder> findBySalaryRange(double low, double high);

    @Query("select c from Coder c where c.salary between :low and :high order by salary desc, hireDate desc")
    Iterable<Coder> findBySalaryRangeNames(double low, double high);

    @Query("select c from Coder c where c.firstName like ?1%")
    Iterable<Coder> findByFirstName(String prefix);

    @Query("select c from Coder c where c.firstName like %?1%")
    Iterable<Coder> findByFirstNameIn(String infix);

    @Query(value = "select * from coders where salary between ?1 and ?2 order by salary desc, hire_date desc", nativeQuery = true)
    Iterable<Coder> findBySalaryRangeNative(double low, double high);
}
