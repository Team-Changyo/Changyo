package com.shinhan.changyo.domain.account.repository;

import com.shinhan.changyo.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 계좌 레포지토리
 * 
 * @author 최영환
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
}
