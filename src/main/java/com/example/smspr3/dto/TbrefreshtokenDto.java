package com.example.smspr3.dto;

import com.example.smspr3.domain.Tbrefreshtoken;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


public class TbrefreshtokenDto {
    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    //우리가 필요한 정보 하나씩만 받기 위해 DTO를 만듬
    public static class CreateReqDto{
        @Schema(description = "tbuserId", example = "")
        @NotNull
        @NotEmpty
        @Size(max=400)
        private String tbuserId;
        @Schema(description = "token", example = "")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String token;

        public Tbrefreshtoken toEntity(){
            return Tbrefreshtoken.of(tbuserId, token);
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
