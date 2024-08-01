package com.example.smspr3.controller;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedfileDto;
import com.example.smspr3.service.TbfeedfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1-1. 피드 첨부파일 API 안내", description = "피드 첨부파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbfeedfile")
@RestController
public class TbfeedfileRestController {
    private TbfeedfileService tbfeedfileService;

    public TbfeedfileRestController(TbfeedfileService tbfeedfileService) {
        this.tbfeedfileService = tbfeedfileService;
    }

    @Operation(summary = "피드 첨부파일 생성",
            description = "피드 첨부파일 생성 컨트롤러 <br />"
                    + "@param TbfeedfileDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbfeedfileDto.CreateResDto> create(@Valid @RequestBody TbfeedfileDto.CreateReqDto param) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfeedfileService.create(param));
    }

    @Operation(summary = "피드 첨부파일 수정",
            description = "피드 첨부파일 수정 컨트롤러 <br />"
                    + "@param TbfeedfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbfeedfileDto.CreateResDto> update(@Valid @RequestBody TbfeedfileDto.UpdateReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedfileService.update(param));
    }
    @Operation(summary = "피드 첨부파일 삭제",
            description = "피드 첨부파일 삭제 컨트롤러 <br />"
                    + "@param TbfeedfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbfeedfileDto.CreateResDto> delete(@Valid @RequestBody TbfeedfileDto.UpdateReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedfileService.delete(param));
    }
    @Operation(summary = "피드 첨부파일 상세 조회",
            description = "피드 첨부파일 상세 조회 컨트롤러 <br />"
                    + "@param TbfeedfileDto.DetailReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbfeedfileDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedfileService.detail(param));
    }
    @Operation(summary = "피드 첨부파일 목록 전체 조회",
            description = "피드 첨부파일 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbfeedfileDto.ListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbfeedfileDto.DetailResDto>> list(@Valid TbfeedfileDto.ListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedfileService.list(param));
    }
    @Operation(summary = "피드 첨부파일 목록 페이지 조회",
            description = "피드 첨부파일 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbfeedfileDto.PagedListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<DefaultDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbfeedfileDto.PagedListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedfileService.pagedList(param));
    }
    @Operation(summary = "피드 첨부파일 목록 스크롤 조회",
            description = "피드 첨부파일 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbfeedfileDto.MoreListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbfeedfileDto.DetailResDto>>mlist(@Valid TbfeedfileDto.ScrollListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedfileService.scrollList(param));
    }
}