package com.snapgrid.dto.member;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @Setter
    public static class Request {
        private String name;
        private String password;
        private String nickname;
        private String email;
    }

    @Getter
    @Setter
    public static class Response {
        private String id;
        private String name;
        private String nickname;
        private String email;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
    }
}
