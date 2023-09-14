package com.shinhan.changyo.domain.authentication;

import com.shinhan.changyo.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authentication extends TimeBaseEntity {

    @Id
    private String accountNumber;

    private String authenticationNumber;

    @Builder
    private Authentication(String accountNumber, String authenticationNumber) {
        this.accountNumber = accountNumber;
        this.authenticationNumber = authenticationNumber;
    }
}