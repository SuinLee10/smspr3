package com.example.smspr3.controller;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedlikeDto;
import com.example.smspr3.service.TbfeedlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1-1. 피드 좋아요 API 안내", description = "피드 좋아요 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbfeedlike")
@RestController
public class TbfeedlikeRestController {
    private TbfeedlikeService tbfeedlikeService;

    public TbfeedlikeRestController(TbfeedlikeService tbfeedlikeService) {
        this.tbfeedlikeService = tbfeedlikeService;
    }


    @Operation(summary = "피드 좋아요 토글",
            description = "피드 좋아요 토글 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbfeedlikeDto.CreateResDto> toggle(@Valid @RequestBody TbfeedlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfeedlikeService.toggle(param));
    }

    @Operation(summary = "피드 좋아요 생성",
            description = "피드 좋아요 생성 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbfeedlikeDto.CreateResDto> create(@Valid @RequestBody TbfeedlikeDto.CreateReqDto param) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfeedlikeService.create(param));
    }

    @Operation(summary = "피드 좋아요 수정",
            description = "피드 좋아요 수정 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbfeedlikeDto.CreateResDto> update(@Valid @RequestBody TbfeedlikeDto.UpdateReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedlikeService.update(param));
    }
    @Operation(summary = "피드 좋아요 삭제",
            description = "피드 좋아요 삭제 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbfeedlikeDto.CreateResDto> delete(@Valid @RequestBody TbfeedlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedlikeService.delete(param));
    }
    @Operation(summary = "피드 좋아요 상세 조회",
            description = "피드 좋아요 상세 조회 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.DetailReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbfeedlikeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedlikeService.detail(param));
    }
    @Operation(summary = "피드 좋아요 목록 전체 조회",
            description = "피드 좋아요 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.ListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbfeedlikeDto.DetailResDto>> list(@Valid TbfeedlikeDto.ListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedlikeService.list(param));
    }
    @Operation(summary = "피드 좋아요 목록 페이지 조회",
            description = "피드 좋아요 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<DefaultDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbfeedlikeDto.PagedListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedlikeService.pagedList(param));
    }
    @Operation(summary = "피드 좋아요 목록 스크롤 조회",
            description = "피드 좋아요 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbfeedlikeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbfeedlikeDto.DetailResDto>>mlist(@Valid TbfeedlikeDto.ScrollListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedlikeService.scrollList(param));
    }
}