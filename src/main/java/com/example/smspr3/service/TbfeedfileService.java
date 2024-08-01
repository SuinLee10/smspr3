package com.example.smspr3.service;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedfileDto;

import java.util.List;

public interface TbfeedfileService {
    TbfeedfileDto.CreateResDto create(TbfeedfileDto.CreateReqDto param);
    TbfeedfileDto.CreateResDto update(TbfeedfileDto.UpdateReqDto param);
    TbfeedfileDto.CreateResDto delete(TbfeedfileDto.UpdateReqDto param);
    TbfeedfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedfileDto.DetailResDto> list(TbfeedfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbfeedfileDto.PagedListReqDto param);
    List<TbfeedfileDto.DetailResDto> scrollList(TbfeedfileDto.ScrollListReqDto param);
}
