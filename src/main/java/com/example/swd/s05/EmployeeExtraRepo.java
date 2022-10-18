package com.example.swd.s05;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.dao.Employee;

@Repository
public interface EmployeeExtraRepo extends CrudRepository<Employee, Integer> {
    Iterable<Employee> findByFirstName(String name);

    Iterable<Employee> findByFirstNameStartingWith(String prefix);

    Iterable<Employee> findByFirstNameStartingWithOrLastNameContainingIgnoreCase(String prefixFirst, String inLast);
}
