package com.example.smspr3.mapper;

import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedcmtDto;

import java.util.List;

public interface TbfeedcmtMapper {
    TbfeedcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbfeedcmtDto.DetailResDto> list(TbfeedcmtDto.ListReqDto param);
    List<TbfeedcmtDto.DetailResDto> scrollList(TbfeedcmtDto.ScrollListReqDto param);
    List<TbfeedcmtDto.DetailResDto> pagedList(TbfeedcmtDto.PagedListReqDto param);
    int pagedListCount(TbfeedcmtDto.PagedListReqDto param);
}
