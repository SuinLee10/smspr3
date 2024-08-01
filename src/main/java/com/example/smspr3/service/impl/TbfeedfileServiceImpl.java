package com.example.smspr3.service.impl;

import com.example.smspr3.domain.Tbfeedfile;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedfileDto;
import com.example.smspr3.mapper.TbfeedfileMapper;
import com.example.smspr3.repository.TbfeedfileRepository;
import com.example.smspr3.service.TbfeedfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbfeedfileServiceImpl implements TbfeedfileService {
    private final TbfeedfileRepository tbfeedfileRepository;
    private final TbfeedfileMapper tbfeedfileMapper;
    public TbfeedfileServiceImpl(
            TbfeedfileRepository tbfeedfileRepository
            ,TbfeedfileMapper tbfeedfileMapper
    ){
        this.tbfeedfileRepository = tbfeedfileRepository;
        this.tbfeedfileMapper = tbfeedfileMapper;
    }
    @Override
    public TbfeedfileDto.CreateResDto create(TbfeedfileDto.CreateReqDto param){
        return tbfeedfileRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public TbfeedfileDto.CreateResDto update(TbfeedfileDto.UpdateReqDto param) {
        Tbfeedfile tbfeedfile = tbfeedfileRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if (param.getDeleted() != null) {
            tbfeedfile.setDeleted(param.getDeleted());
        }
        if (param.getProcess() != null) {
            tbfeedfile.setProcess(param.getProcess());
        }
        tbfeedfileRepository.save(tbfeedfile);
        return tbfeedfile.toCreateResDto();
    }
    @Override
    public TbfeedfileDto.CreateResDto delete(TbfeedfileDto.UpdateReqDto param) {
    Tbfeedfile tbfeedfile = tbfeedfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
    tbfeedfileRepository.delete(tbfeedfile);
    return TbfeedfileDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbfeedfileDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbfeedfileDto.DetailResDto selectResDto = tbfeedfileMapper.detail(param);
        if(selectResDto == null){throw new RuntimeException("no data");}
        return selectResDto;
    }
    @Override
    public List<TbfeedfileDto.DetailResDto> list(TbfeedfileDto.ListReqDto param){
        return detailList(tbfeedfileMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(TbfeedfileDto.PagedListReqDto param){
        int[] returnSize = param.init(tbfeedfileMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbfeedfileMapper.pagedList(param)));
    }
    @Override
    public List<TbfeedfileDto.DetailResDto> scrollList(TbfeedfileDto.ScrollListReqDto param){
    param.init();
    return detailList(tbfeedfileMapper.scrollList(param));
    }

    public List<TbfeedfileDto.DetailResDto> detailList(List<TbfeedfileDto.DetailResDto>list){
        List<TbfeedfileDto.DetailResDto> newList = new ArrayList<>();
        for(TbfeedfileDto.DetailResDto each: list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
