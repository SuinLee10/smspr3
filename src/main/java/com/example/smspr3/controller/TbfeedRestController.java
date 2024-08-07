package com.example.smspr3.controller;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedDto;
import com.example.smspr3.service.TbfeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "1-1. 피드 API 안내", description = "피드 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbfeed")
@RestController
public class TbfeedRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TbfeedService tbfeedService;

    public TbfeedRestController(TbfeedService tbfeedService) {
        this.tbfeedService = tbfeedService;
    }

    @Operation(summary = "피드 생성",
            description = "피드 생성 컨트롤러 <br />"
                    + "@param TbfeedDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbfeedDto.CreateResDto> create(@Valid @RequestBody TbfeedDto.CreateReqDto param) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbfeedService.create(param));
    }

    @Operation(summary = "피드 수정",
            description = "피드 수정 컨트롤러 <br />"
                    + "@param TbfeedDto.UpdateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbfeedDto.CreateResDto> update(@Valid @RequestBody TbfeedDto.UpdateReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedService.update(param));
    }
    @Operation(summary = "피드 상세 조회",
            description = "피드 상세 조회 컨트롤러 <br />"
                    + "@param TbfeedDto.DetailReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbfeedDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedService.detail(param));
    }
    @Operation(summary = "피드 목록 전체 조회",
            description = "피드 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbfeedDto.ListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbfeedDto.DetailResDto>> list(@Valid TbfeedDto.ListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedService.list(param));
    }
    @Operation(summary = "피드 목록 페이지 조회",
            description = "피드 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbfeedDto.PagedListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<DefaultDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbfeedDto.PagedListReqDto param) {
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedService.pagedList(param));
    }
    @Operation(summary = "피드 목록 스크롤 조회",
            description = "피드 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbfeedDto.MoreListReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbfeedDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbfeedDto.DetailResDto>>mlist(@Valid TbfeedDto.ScrollListReqDto param, HttpServletRequest request, HttpServletResponse response) {
        String reqTbuserId = request.getAttribute("reqTbuserId") + "";
        logger.info("c-1: reqTbuserId [{}]", reqTbuserId);
        return ResponseEntity.status(HttpStatus.OK).body(tbfeedService.scrollList(param));
    }
}