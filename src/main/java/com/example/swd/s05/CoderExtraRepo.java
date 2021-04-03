package com.example.swd.s05;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.dao.Coder;

@Repository
public interface CoderExtraRepo extends CrudRepository<Coder, Integer> {
    Iterable<Coder> findByFirstName(String name);

    Iterable<Coder> findByFirstNameStartingWith(String prefix);

    Iterable<Coder> findByFirstNameStartingWithOrLastNameContainingIgnoreCase(String prefixFirst, String inLast);
}
