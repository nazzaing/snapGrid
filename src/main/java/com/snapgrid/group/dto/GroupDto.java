package com.snapgrid.group.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class GroupDto {

    @Getter
    @Setter
    public static class Request {
        @NotBlank(message = "이름은 필수 어쩌구")
        private String groupName;

        @NotNull
        private String introduction;

        @NotNull
        private String address;

        @NotNull
        private String category;

        private LocalDateTime createDate;

        private LocalDateTime updateDate;

        private Long creatorId;
    }

    @Getter
    @Setter
    public static class Response {
        private Long groupId;

        private String groupName;

        private String introduction;

        private String address;

        private String category;

        private LocalDateTime createDate;

        private LocalDateTime updateDate;

        private Long creatorId;


    }



}
