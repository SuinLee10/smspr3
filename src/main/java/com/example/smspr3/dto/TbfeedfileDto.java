package com.example.smspr3.dto;

import com.example.smspr3.domain.Tbfeed;
import com.example.smspr3.domain.Tbfeedfile;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class TbfeedfileDto {

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

        @Schema(description = "type", example = "")
        @NotNull
        @NotEmpty
        @Size(max=100)
        private String type;
        @Schema(description = "url", example="")
        @Size(max=400)
        private String url;

        public Tbfeedfile toEntity(){return Tbfeedfile.of(tbfeedId, type, url); }
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

        @Schema(description = "type", example = "")
        @Size(max=100)
        private String type;
        @Schema(description = "url", example="")
        @Size(max=400)
        private String url;
    }

    @Schema
    @Getter
    @Setter
    public static class DetailResDto extends DefaultDto.DetailResDto{
        @Schema(description = "tbfeedId", example = "")
        private String tbfeedId;

        @Schema(description = "type", example = "")
        private String type;
        @Schema(description = "url", example="")
        private String url;
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
        @Schema(description = "type", example = "")
        private String type;
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
        @Schema(description = "type", example = "")
        private String type;
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
        @Schema(description = "type", example = "")
        private String type;
    }



}
