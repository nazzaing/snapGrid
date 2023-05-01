package com.snapgrid.member.dto;

import com.snapgrid.member.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @Setter
    public static class Request {
        private String userId;
        private String password;
        private String userName;
        private String email;
        private String phone;
        private Address address;
    }

    @Getter
    @Setter
    public static class Response {
        private Long id;
        private String userId;
        private String userName;
        private String email;
        private String phone;
        private Address address;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
    }
}
