package com.example.smspr3.dto;

import com.example.smspr3.domain.Tbemail;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbemailDto {
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    //우리가 필요한 정보 하나씩만 받기 위해 DTO를 만듬
    public static class CreateReqDto{
        @Schema(description = "username", example = "")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String username;
        @Schema(description = "number", example = "")
        @NotNull
        @NotEmpty
        private String number;

        public Tbemail toEntity(){
            return Tbemail.of(username, number);
        }
    }

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResDto{
        private String id;
    }


}
