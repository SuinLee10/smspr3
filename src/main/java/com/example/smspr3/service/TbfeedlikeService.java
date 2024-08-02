package com.example.smspr3.service;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedlikeDto;

import java.util.List;

public interface TbfeedlikeService {

    boolean exist(TbfeedlikeDto.CreateReqDto param);
    TbfeedlikeDto.CreateResDto toggle(TbfeedlikeDto.CreateReqDto param);
    TbfeedlikeDto.CreateResDto create(TbfeedlikeDto.CreateReqDto param);
    TbfeedlikeDto.CreateResDto update(TbfeedlikeDto.UpdateReqDto param);
    TbfeedlikeDto.CreateResDto delete(TbfeedlikeDto.UpdateReqDto param);
    TbfeedlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedlikeDto.DetailResDto> list(TbfeedlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbfeedlikeDto.PagedListReqDto param);
    List<TbfeedlikeDto.DetailResDto> scrollList(TbfeedlikeDto.ScrollListReqDto param);
}
