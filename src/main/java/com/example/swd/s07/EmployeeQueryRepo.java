package com.example.swd.s07;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.dao.Employee;

@Repository
public interface EmployeeQueryRepo extends CrudRepository<Employee, Integer> {
    @Query("select e from Employee e where e.salary between ?1 and ?2 order by salary desc, hired desc")
    Iterable<Employee> findBySalaryRange(double low, double high);

    @Query("select e from Employee e where e.salary between :low and :high order by salary desc, hired desc")
    Iterable<Employee> findBySalaryRangeNames(double low, double high);

    @Query("select e from Employee e where e.firstName like ?1%")
    Iterable<Employee> findByFirstName(String prefix);

    @Query("select e from Employee e where e.firstName like %?1%")
    Iterable<Employee> findByFirstNameIn(String infix);

    @Query(value = "select * from employee where salary between ?1 and ?2 order by salary desc, hired desc", nativeQuery = true)
    Iterable<Employee> findBySalaryRangeNative(double low, double high);
}
