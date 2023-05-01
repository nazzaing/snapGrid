package com.snapgrid.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false, length = 100, unique = true)
    private String userId;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "phone", nullable = true, unique = true)
    private String phone;

    @Embedded
    private Address address;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Builder
    public Member(String userId, String password, String userName, String email, String phone, Address address) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }
}
