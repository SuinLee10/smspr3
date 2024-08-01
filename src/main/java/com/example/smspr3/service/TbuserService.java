package com.example.smspr3.service;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbuserDto;

import java.util.List;

public interface TbuserService {
    TbuserDto.CreateResDto login(TbuserDto.LoginReqDto param);
    TbuserDto.CreateResDto signup(TbuserDto.SignupReqDto param);
    TbuserDto.CreateResDto create(TbuserDto.CreateReqDto param);
    TbuserDto.CreateResDto update(TbuserDto.UpdateReqDto param);
    TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbuserDto.DetailResDto> list(TbuserDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbuserDto.PagedListReqDto param);
    List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListReqDto param);
}
