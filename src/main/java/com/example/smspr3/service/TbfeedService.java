package com.example.smspr3.service;

import com.example.smspr3.domain.Tbfeed;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedDto;

import java.util.List;

public interface TbfeedService {
    TbfeedDto.CreateResDto create(TbfeedDto.CreateReqDto param);
    TbfeedDto.CreateResDto update(TbfeedDto.UpdateReqDto param);
    TbfeedDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedDto.DetailResDto> list(TbfeedDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbfeedDto.PagedListReqDto param);
    List<TbfeedDto.DetailResDto> scrollList(TbfeedDto.ScrollListReqDto param);
}
