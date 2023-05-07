package com.snapgrid.group.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class groupAndMemberDto {
    @Getter
    @Setter
    @AllArgsConstructor
    public static class createRequest {
        private Long groupId;
        private Long memberId;
        private LocalDateTime joinDate;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class deleteRequest {
        private Long groupId;
        private Long memberId;
    }

}
