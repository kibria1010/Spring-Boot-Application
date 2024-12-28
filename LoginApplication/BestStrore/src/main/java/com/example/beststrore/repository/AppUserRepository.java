package com.example.beststrore.repository;

import com.example.beststrore.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    public AppUser findByEmail(String email);
}
