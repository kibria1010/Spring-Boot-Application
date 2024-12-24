package com.example.mvc.repository;

import com.example.mvc.model.Programmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammerRepo extends JpaRepository<Programmer, Integer> {
}
