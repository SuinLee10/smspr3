package com.example.smspr3.mapper;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedlikeDto;

import java.util.List;

public interface TbfeedlikeMapper {
    TbfeedlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedlikeDto.DetailResDto> list(TbfeedlikeDto.ListReqDto param);
    List<TbfeedlikeDto.DetailResDto> scrollList(TbfeedlikeDto.ScrollListReqDto param);
    List<TbfeedlikeDto.DetailResDto> pagedList(TbfeedlikeDto.PagedListReqDto param);
    int pagedListCount(TbfeedlikeDto.PagedListReqDto param);
}
