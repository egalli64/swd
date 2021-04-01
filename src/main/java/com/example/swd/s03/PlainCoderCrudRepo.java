package com.example.swd.s03;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.swd.dao.Coder;

@Repository
public interface PlainCoderCrudRepo extends CrudRepository<Coder, Integer> {
}
