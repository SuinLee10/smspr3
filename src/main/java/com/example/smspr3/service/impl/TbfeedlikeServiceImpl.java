package com.example.smspr3.service.impl;

import com.example.smspr3.domain.Tbfeedlike;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbfeedlikeDto;
import com.example.smspr3.mapper.TbfeedlikeMapper;
import com.example.smspr3.repository.TbfeedlikeRepository;
import com.example.smspr3.service.TbfeedlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbfeedlikeServiceImpl implements TbfeedlikeService {
    private final TbfeedlikeRepository tbfeedlikeRepository;
    private final TbfeedlikeMapper tbfeedlikeMapper;
    public TbfeedlikeServiceImpl(
            TbfeedlikeRepository tbfeedlikeRepository
            ,TbfeedlikeMapper tbfeedlikeMapper
    ){
        this.tbfeedlikeRepository = tbfeedlikeRepository;
        this.tbfeedlikeMapper = tbfeedlikeMapper;
    }


    @Override
    public boolean exist(TbfeedlikeDto.CreateReqDto param){
        Tbfeedlike tbfeedlike = tbfeedlikeRepository.findByTbfeedIdAndTbuserId(param.getTbfeedId(),param.getTbuserId());
        return tbfeedlike != null;
    }
    @Override
    public TbfeedlikeDto.CreateResDto toggle(TbfeedlikeDto.CreateReqDto param){
        Tbfeedlike tbfeedlike = tbfeedlikeRepository.findByTbfeedIdAndTbuserId(param.getTbfeedId(),param.getTbuserId());
        if(tbfeedlike == null){
            return tbfeedlikeRepository.save(param.toEntity()).toCreateResDto();
        } else {
            return delete(TbfeedlikeDto.UpdateReqDto.builder().id(tbfeedlike.getId()).build());
        }
    }

    @Override
    public TbfeedlikeDto.CreateResDto create(TbfeedlikeDto.CreateReqDto param){
        return tbfeedlikeRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public TbfeedlikeDto.CreateResDto update(TbfeedlikeDto.UpdateReqDto param){
        Tbfeedlike tbfeedlike = tbfeedlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted()!=null) {
            tbfeedlike.setDeleted(param.getDeleted());
        }
        if(param.getProcess()!=null) {
            tbfeedlike.setProcess(param.getProcess());
        }

        tbfeedlikeRepository.save(tbfeedlike);
        return tbfeedlike.toCreateResDto();
    }
    @Override
    public TbfeedlikeDto.CreateResDto delete(TbfeedlikeDto.UpdateReqDto param){
        Tbfeedlike tbfeedlike = tbfeedlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbfeedlikeRepository.delete(tbfeedlike);
        return TbfeedlikeDto.CreateResDto.builder().id("success").build();
    }
    @Override
    public TbfeedlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbfeedlikeDto.DetailResDto selectResDto = tbfeedlikeMapper.detail(param);
        if(selectResDto == null){throw new RuntimeException("no data");}
        return selectResDto;
    }
    @Override
    public List<TbfeedlikeDto.DetailResDto> list(TbfeedlikeDto.ListReqDto param){
        return detailList(tbfeedlikeMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(TbfeedlikeDto.PagedListReqDto param){
        int[] returnSize = param.init(tbfeedlikeMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbfeedlikeMapper.pagedList(param)));
    }
    @Override
    public List<TbfeedlikeDto.DetailResDto> scrollList(TbfeedlikeDto.ScrollListReqDto param){
    param.init();
    return detailList(tbfeedlikeMapper.scrollList(param));
    }

    public List<TbfeedlikeDto.DetailResDto> detailList(List<TbfeedlikeDto.DetailResDto>list){
        List<TbfeedlikeDto.DetailResDto> newList = new ArrayList<>();
        for(TbfeedlikeDto.DetailResDto each: list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
