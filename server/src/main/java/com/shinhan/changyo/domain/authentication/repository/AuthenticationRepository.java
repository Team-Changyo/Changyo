package com.shinhan.changyo.domain.authentication.repository;

import com.shinhan.changyo.domain.authentication.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<Authentication, String> {
}