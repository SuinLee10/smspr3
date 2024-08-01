package com.example.smspr3.mapper;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbuserDto;

import java.util.List;

public interface TbuserMapper {
    TbuserDto.DetailResDto login(TbuserDto.LoginReqDto param);

    TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbuserDto.DetailResDto> list(TbuserDto.ListReqDto param);
    List<TbuserDto.DetailResDto> pagedList(TbuserDto.PagedListReqDto param);
    List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListReqDto param);
    int pagedListCount(TbuserDto.PagedListReqDto param);
}
