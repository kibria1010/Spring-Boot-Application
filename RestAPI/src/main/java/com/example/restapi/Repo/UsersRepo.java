package com.example.restapi.Repo;

import com.example.restapi.Models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {

}
