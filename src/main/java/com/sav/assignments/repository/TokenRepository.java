package com.sav.assignments.repository;

import com.sav.assignments.entity.AppUser;
import com.sav.assignments.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(AppUser user);
}
