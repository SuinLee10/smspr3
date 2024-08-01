package com.example.smspr3.service.impl;

import com.example.smspr3.domain.Tbuser;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbuserDto;
import com.example.smspr3.mapper.TbuserMapper;
import com.example.smspr3.repository.TbuserRepository;
import com.example.smspr3.service.TbuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbuserServiceImpl implements TbuserService {
    private final TbuserRepository tbuserRepository;
    private final TbuserMapper tbuserMapper;
    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
            ,TbuserMapper tbuserMapper
    ){
        this.tbuserRepository = tbuserRepository;
        this.tbuserMapper = tbuserMapper;
    }

    @Override
    public TbuserDto.CreateResDto login(TbuserDto.LoginReqDto param) {
        Tbuser tbuser = tbuserRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if(tbuser == null){return TbuserDto.CreateResDto.builder().id("not matched").build();}
        return TbuserDto.CreateResDto.builder().id(tbuser.getId()).build();
    }

    @Override
    public TbuserDto.CreateResDto signup(TbuserDto.SignupReqDto param) {
        TbuserDto.CreateReqDto newParam = TbuserDto.CreateReqDto.builder().username(param.getUsername()).password(param.getPassword()).build();
        //Tbuser tbuser = Tbuser.of("123", "456",null, null, null, null, null, null);
        return tbuserRepository.save(newParam.toEntity()).toCreateResDto();
    }

    @Override
    public TbuserDto.CreateResDto create(TbuserDto.CreateReqDto param){
        return tbuserRepository.save(param.toEntity()).toCreateResDto();
    }
    @Override
    public TbuserDto.CreateResDto update(TbuserDto.UpdateReqDto param) {
        Tbuser tbuser = tbuserRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("no data"));
        if (param.getName() != null) {
            tbuser.setName(param.getName());
        }
        if (param.getNick() != null) {
            tbuser.setNick(param.getNick());
        }
        if (param.getPhone() != null) {
            tbuser.setPhone(param.getPhone());
        }
        if (param.getGender() != null) {
            tbuser.setGender(param.getGender());
        }
        if (param.getContent() != null) {
            tbuser.setContent(param.getContent());
        }
        if (param.getImg() != null) {
            tbuser.setImg(param.getImg());
        }
        tbuserRepository.save(tbuser);
        return tbuser.toCreateResDto();
    }

    @Override
    public TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbuserDto.DetailResDto selectResDto = tbuserMapper.detail(param);
        if(selectResDto == null){throw new RuntimeException("no data");}
        return selectResDto;
    }
    @Override
    public List<TbuserDto.DetailResDto> list(TbuserDto.ListReqDto param){
        return detailList(tbuserMapper.list(param));
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(TbuserDto.PagedListReqDto param){
        int[] returnSize = param.init(tbuserMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbuserMapper.pagedList(param)));
    }
    @Override
    public List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListReqDto param){
    param.init();
    return detailList(tbuserMapper.scrollList(param));
    }

    public List<TbuserDto.DetailResDto> detailList(List<TbuserDto.DetailResDto>list){
        List<TbuserDto.DetailResDto> newList = new ArrayList<>();
        for(TbuserDto.DetailResDto each: list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
