package com.example.smspr3.service;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedcmtDto;

import java.util.List;

public interface TbfeedcmtService {
    TbfeedcmtDto.CreateResDto create(TbfeedcmtDto.CreateReqDto param);
    TbfeedcmtDto.CreateResDto update(TbfeedcmtDto.UpdateReqDto param);
    TbfeedcmtDto.CreateResDto delete(TbfeedcmtDto.UpdateReqDto param);
    TbfeedcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedcmtDto.DetailResDto> list(TbfeedcmtDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbfeedcmtDto.PagedListReqDto param);
    List<TbfeedcmtDto.DetailResDto> scrollList(TbfeedcmtDto.ScrollListReqDto param);
}
