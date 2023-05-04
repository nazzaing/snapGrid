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


    /**
     * 모임 생성 DTO
     */
    @Getter
    @Setter
    public static class createRequest {
        @NotBlank(message = "이름은 필수 어쩌구")
        private String groupName;

        @NotNull
        private String introduction;

        @NotNull
        private String address;

        @NotNull
        private String category;

        private Long creatorId;

        private LocalDateTime createDate = LocalDateTime.now();
    }


    /**
     * 모임 수정 DTO
     */
    @Getter
    @Setter
    public static class modifyRequest {

        private Long groupId;

        private String groupName;

        private String introduction;

        private String address;

        private String category;

        private Long UpdaterId;

        private LocalDateTime updateDate = LocalDateTime.now();
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

        public Response() {
        }

        @Builder
        public Response(Long groupId, String groupName, String introduction, String address, String category, LocalDateTime createDate, LocalDateTime updateDate, Long creatorId) {
            this.groupId = groupId;
            this.groupName = groupName;
            this.introduction = introduction;
            this.address = address;
            this.category = category;
            this.createDate = createDate;
            this.updateDate = updateDate;
            this.creatorId = creatorId;
        }
    }



}
