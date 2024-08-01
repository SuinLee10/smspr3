package com.example.smspr3.mapper;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedDto;

import java.util.List;

public interface TbfeedMapper {
    TbfeedDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedDto.DetailResDto> list(TbfeedDto.ListReqDto param);
    List<TbfeedDto.DetailResDto> scrollList(TbfeedDto.ScrollListReqDto param);
    List<TbfeedDto.DetailResDto> pagedList(TbfeedDto.PagedListReqDto param);
    int pagedListCount(TbfeedDto.PagedListReqDto param);
}
