package com.shinhan.changyo.domain.member;

import com.shinhan.changyo.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;

/**
 * 회원 entity
 * @author 최영환
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends TimeBaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String encryptedPwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(unique = true, nullable = false, length = 13)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean active;

    @ElementCollection(fetch = EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    private Member(String loginId, String encryptedPwd, String name, String phoneNumber, Boolean active, List<String> roles) {
        this.loginId = loginId;
        this.encryptedPwd = encryptedPwd;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.roles = roles;
    }

    // == Business Logics ==//
    public void deActive() {
        this.active = false;
    }

    //== security info ==//
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return encryptedPwd;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
