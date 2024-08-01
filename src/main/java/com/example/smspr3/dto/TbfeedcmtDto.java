package com.example.smspr3.dto;

import com.example.smspr3.domain.Tbfeedcmt;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbfeedcmtDto {

    @Builder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateReqDto{
        @Schema(description = "tbfeedId", example = "")
        @NotNull
        @NotEmpty
        private String tbfeedId;

        @Schema(description = "tbuserId", example = "")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example="")
        @Size(max=400)
        private String content;

        public Tbfeedcmt toEntity(){return Tbfeedcmt.of(tbfeedId, tbuserId, content); }
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

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        @Schema(description = "tbfeedId", example = "")
        private String tbfeedId;

        @Schema(description = "tbuserId", example = "")
        @Size(max=100)
        private String tbuserId;
        @Schema(description = "content", example="")
        @Size(max=400)
        private String content;
    }

    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbfeedId", example = "")
        private String tbfeedId;

        @Schema(description = "tbuserId", example = "")
        private String tbuserId;
        @Schema(description = "content", example="")
        private String content;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        @Schema(description = "tbfeedId", example = "")
        private String tbfeedId;
        @Schema(description = "tbuserId", example = "")
        private String tbuserId;
    }

    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        @Schema(description = "tbfeedId", example = "")
        private String tbfeedId;
        @Schema(description = "tbuserId", example = "")
        private String tbuserId;
    }
    @SuperBuilder
    @Schema
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        @Schema(description = "tbfeedId", example = "")
        private String tbfeedId;
        @Schema(description = "tbuserId", example = "")
        private String tbuserId;
    }



}
