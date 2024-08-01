package com.example.smspr3.mapper;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedfileDto;

import java.util.List;

public interface TbfeedfileMapper {
    TbfeedfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedfileDto.DetailResDto> list(TbfeedfileDto.ListReqDto param);
    List<TbfeedfileDto.DetailResDto> pagedList(TbfeedfileDto.PagedListReqDto param);
    List<TbfeedfileDto.DetailResDto> scrollList(TbfeedfileDto.ScrollListReqDto param);
    int pagedListCount(TbfeedfileDto.PagedListReqDto param);
}
