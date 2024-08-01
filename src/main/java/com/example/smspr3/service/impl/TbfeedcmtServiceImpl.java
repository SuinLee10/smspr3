package com.example.smspr3.service.impl;

import com.example.smspr3.domain.Tbfeed;
import com.example.smspr3.domain.Tbfeedcmt;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedcmtDto;
import com.example.smspr3.mapper.TbfeedcmtMapper;
import com.example.smspr3.repository.TbfeedcmtRepository;
import com.example.smspr3.service.TbfeedcmtService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbfeedcmtServiceImpl implements TbfeedcmtService {
    private final TbfeedcmtRepository tbfeedcmtRepository;
    private final TbfeedcmtMapper tbfeedcmtMapper;
    public TbfeedcmtServiceImpl(
            TbfeedcmtRepository tbfeedcmtRepository
            ,TbfeedcmtMapper tbfeedcmtMapper
    ){
        this.tbfeedcmtRepository = tbfeedcmtRepository;
        this.tbfeedcmtMapper = tbfeedcmtMapper;
    }

    @Override
    public TbfeedcmtDto.CreateResDto create(TbfeedcmtDto.CreateReqDto param){
        return tbfeedcmtRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public TbfeedcmtDto.CreateResDto update(TbfeedcmtDto.UpdateReqDto param){
        Tbfeedcmt tbfeedcmt = tbfeedcmtRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted()!=null) {
            tbfeedcmt.setDeleted(param.getDeleted());
        }
        if(param.getProcess()!=null) {
            tbfeedcmt.setProcess(param.getProcess());
        }

        tbfeedcmtRepository.save(tbfeedcmt);
        return tbfeedcmt.toCreateResDto();
    }
    @Override
    public TbfeedcmtDto.CreateResDto delete(TbfeedcmtDto.UpdateReqDto param){
        Tbfeedcmt tbfeedcmt = tbfeedcmtRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbfeedcmtRepository.delete(tbfeedcmt);
        return TbfeedcmtDto.CreateResDto.builder().id("success").build();
    }
    @Override
    public TbfeedcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbfeedcmtDto.DetailResDto selectResDto = tbfeedcmtMapper.detail(param);
        if(selectResDto == null){throw new RuntimeException("no data");}
        return selectResDto;
    }
    @Override
    public List<TbfeedcmtDto.DetailResDto> list(TbfeedcmtDto.ListReqDto param){
        return detailList(tbfeedcmtMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(TbfeedcmtDto.PagedListReqDto param){
        int[] returnSize = param.init(tbfeedcmtMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbfeedcmtMapper.pagedList(param)));
    }
    @Override
    public List<TbfeedcmtDto.DetailResDto> scrollList(TbfeedcmtDto.ScrollListReqDto param){
    param.init();
    return detailList(tbfeedcmtMapper.scrollList(param));
    }

    public List<TbfeedcmtDto.DetailResDto> detailList(List<TbfeedcmtDto.DetailResDto>list){
        List<TbfeedcmtDto.DetailResDto> newList = new ArrayList<>();
        for(TbfeedcmtDto.DetailResDto each: list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
