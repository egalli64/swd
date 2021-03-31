package com.example.swd.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoderRepo extends CrudRepository<Coder, Integer> {
}
