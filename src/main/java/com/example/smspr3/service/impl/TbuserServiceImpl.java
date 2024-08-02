package com.example.smspr3.service.impl;

import com.example.smspr3.domain.Tbemail;
import com.example.smspr3.domain.Tbuser;
import com.example.smspr3.dto.DefaultDto;
import com.example.smspr3.dto.TbemailDto;
import com.example.smspr3.dto.TbuserDto;
import com.example.smspr3.mapper.TbuserMapper;
import com.example.smspr3.repository.TbemailRepository;
import com.example.smspr3.repository.TbuserRepository;
import com.example.smspr3.service.TbuserService;
import com.example.smspr3.util.SendEmail;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbuserServiceImpl implements TbuserService {
    private final TbuserRepository tbuserRepository;
    private final TbuserMapper tbuserMapper;
    private final SendEmail sendEmail;
    private final TbemailRepository tbemailRepository;

    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
            , SendEmail sendEmail
            , TbuserMapper tbuserMapper
            , TbemailRepository tbemailRepository
    ){
        this.tbuserRepository = tbuserRepository;
        this.sendEmail = sendEmail;
        this.tbuserMapper = tbuserMapper;
        this.tbemailRepository = tbemailRepository;
    }

    @Override
    public TbuserDto.CreateResDto confirm(TbuserDto.ConfirmReqDto param){
        Tbemail tbemail = tbemailRepository.findByUsernameAndNumber(param.getUsername(), param.getNumber());
        if(tbemail == null){
            return TbuserDto.CreateResDto.builder().id("not matched").build();
        }else{
            tbemailRepository.delete(tbemail);
            return TbuserDto.CreateResDto.builder().id("ok").build();
        }
    }
    @Transactional
    @Override
    public TbuserDto.CreateResDto email(TbuserDto.UidReqDto param){
        Tbuser tbuser = tbuserRepository.findByUsername(param.getUsername());
        if(tbuser == null){
            String number = "";
            for(int i = 0; i < 6; i++){
                int random_0to9 = (int)(Math.random() * 10);
                number += random_0to9 + "";
            }
            try{
                Tbemail tbemail = tbemailRepository.findByUsername(param.getUsername());
                if(tbemail == null){
                    tbemailRepository.save(TbemailDto.CreateReqDto.builder().username(param.getUsername()).number(number).build().toEntity());
                } else {
                    tbemail.setNumber(number);
                    tbemailRepository.save(tbemail);
                }
                System.out.println("number : " + number);
                sendEmail.send(param.getUsername(),"이메일 인증입니다", "인증번호 : " + number);
            }catch(Exception e){

            }return TbuserDto.CreateResDto.builder().id("ok").build();
        }else{
            return TbuserDto.CreateResDto.builder().id("already").build();
        }
    }
    @Override
    public TbuserDto.CreateResDto id(TbuserDto.UidReqDto param){
        //금지된 단어를 사용하는 경우 가입 불가!
        String[] ids = {"admin", "user", "fxxx"};
        for(String id : ids){
            if((param.getUsername()).contains(id)){
                return TbuserDto.CreateResDto.builder().id("not").build();
            }
        }

        Tbuser tbuser = tbuserRepository.findByUsername(param.getUsername());
        if(tbuser == null){
            //이거는 중복 아니어서 가입 가능!
            return TbuserDto.CreateResDto.builder().id("ok").build();
        } else {
            //이거는 중복 가입 뷸가능!
            return TbuserDto.CreateResDto.builder().id("already").build();
        }
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
