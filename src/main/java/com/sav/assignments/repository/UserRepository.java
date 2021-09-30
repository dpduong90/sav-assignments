package com.sav.assignments.repository;

import com.sav.assignments.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findOneByUsername(String username);
    Optional<AppUser> findOneByEmail(String username);
}