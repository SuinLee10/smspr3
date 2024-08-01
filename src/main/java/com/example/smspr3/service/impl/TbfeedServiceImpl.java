package com.example.smspr3.service.impl;

import com.example.smspr3.domain.Tbfeed;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedDto;
import com.example.smspr3.mapper.TbfeedMapper;
import com.example.smspr3.repository.TbfeedRepository;
import com.example.smspr3.service.TbfeedService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbfeedServiceImpl implements TbfeedService {
    private final TbfeedRepository tbfeedRepository;
    private final TbfeedMapper tbfeedMapper;
    public TbfeedServiceImpl(
            TbfeedRepository tbfeedRepository
            ,TbfeedMapper tbfeedMapper
    ){
        this.tbfeedRepository = tbfeedRepository;
        this.tbfeedMapper = tbfeedMapper;
    }

    @Override
    public TbfeedDto.CreateResDto create(TbfeedDto.CreateReqDto param){
        TbfeedDto.CreateResDto createResDto = tbfeedRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }
    @Override
    public TbfeedDto.CreateResDto update(TbfeedDto.UpdateReqDto param){
        Tbfeed tbfeed = tbfeedRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted()!=null) {
            tbfeed.setDeleted(param.getDeleted());
        }
        if(param.getProcess()!=null) {
            tbfeed.setProcess(param.getProcess());
        }
        if(param.getTbuserId()!=null) {
            tbfeed.setTbuserId(param.getTbuserId());
        }
        if(param.getTitle()!=null){
            tbfeed.setTitle(param.getTitle());
        }
        if(param.getContent()!=null){
            tbfeed.setContent(param.getContent());
        }
        tbfeedRepository.save(tbfeed);
        return tbfeed.toCreateResDto();
    }
    @Override
    public TbfeedDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbfeedDto.DetailResDto selectResDto = tbfeedMapper.detail(param);
        if(selectResDto == null){throw new RuntimeException("no data");}
        return selectResDto;
    }
    @Override
    public List<TbfeedDto.DetailResDto> list(TbfeedDto.ListReqDto param){
        return detailList(tbfeedMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(TbfeedDto.PagedListReqDto param){
        int[] returnSize = param.init(tbfeedMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbfeedMapper.pagedList(param)));
    }
    @Override
    public List<TbfeedDto.DetailResDto> scrollList(TbfeedDto.ScrollListReqDto param){
    param.init();
    return detailList(tbfeedMapper.scrollList(param));
    }

    public List<TbfeedDto.DetailResDto> detailList(List<TbfeedDto.DetailResDto>list){
        List<TbfeedDto.DetailResDto> newList = new ArrayList<>();
        for(TbfeedDto.DetailResDto each: list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
