package com.example.swd.m2.s5;

import org.springframework.data.repository.CrudRepository;

import com.example.swd.m2.entity.Employee;

public interface EmployeeExtraRepository extends CrudRepository<Employee, Integer> {
    Iterable<Employee> findByFirstName(String name);

    Iterable<Employee> findByFirstNameStartingWith(String prefix);

    Iterable<Employee> findByFirstNameStartingWithOrLastNameContainingIgnoreCase(String prefixFirst, String inLast);
}
