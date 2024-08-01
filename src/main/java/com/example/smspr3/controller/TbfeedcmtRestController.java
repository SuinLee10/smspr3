package com.example.smspr3.controller;

import com.example.smspr3.domain.Tbfeedcmt;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedcmtDto;
import com.example.smspr3.service.TbfeedcmtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1-1. 피드 댓글 API 안내", description = "피드 댓글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbfeedcmt")
@RestController
public class TbfeedcmtRestController {
    private TbfeedcmtService tbfeedcmtService;

    public TbfeedcmtRestController(TbfeedcmtService tbfeedcmtService) {
        this.tbfeedcmtService = tbfeedcmtService;
    }

    @Operation(summary = "피드 댓글 생성",
            description = "피드 댓글 생성 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedcmtDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbfeedcmtDto.CreateResDto> create(@Valid @RequestBody TbfeedcmtDto.CreateReqDto param) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfeedcmtService.create(param));
    }

    @Operation(summary = "피드 댓글 수정",
            description = "피드 댓글 수정 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedcmtDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbfeedcmtDto.CreateResDto> update(@Valid @RequestBody TbfeedcmtDto.UpdateReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedcmtService.update(param));
    }
    @Operation(summary = "피드 댓글 삭제",
            description = "피드 댓글 삭제 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedcmtDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbfeedcmtDto.CreateResDto> delete(@Valid @RequestBody TbfeedcmtDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedcmtService.delete(param));
    }
    @Operation(summary = "피드 댓글 상세 조회",
            description = "피드 댓글 상세 조회 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.DetailReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedcmtDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbfeedcmtDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedcmtService.detail(param));
    }
    @Operation(summary = "피드 댓글 목록 전체 조회",
            description = "피드 댓글 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.ListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedcmtDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbfeedcmtDto.DetailResDto>> list(@Valid TbfeedcmtDto.ListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedcmtService.list(param));
    }
    @Operation(summary = "피드 댓글 목록 페이지 조회",
            description = "피드 댓글 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.PagedListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<DefaultDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbfeedcmtDto.PagedListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedcmtService.pagedList(param));
    }
    @Operation(summary = "피드 댓글 목록 스크롤 조회",
            description = "피드 댓글 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbfeedcmtDto.MoreListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedcmtDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbfeedcmtDto.DetailResDto>>mlist(@Valid TbfeedcmtDto.ScrollListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedcmtService.scrollList(param));
    }
}