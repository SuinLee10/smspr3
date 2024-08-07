package com.example.smspr3.domain;

import com.example.smspr3.dto.TbrefreshtokenDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
})
@Entity
public class Tbrefreshtoken extends AuditingFields{
    @Setter @Column(nullable = false, unique = true) private String tbuserId;
    @Setter @Column(nullable = false) private String token;
    protected Tbrefreshtoken(){}
    private Tbrefreshtoken(String tbuserId, String token){
        this.tbuserId = tbuserId;
        this.token = token;
    }
    public static Tbrefreshtoken of(String tbuserId, String token){
        return new Tbrefreshtoken(tbuserId, token);
    }

    public TbrefreshtokenDto.CreateResDto toCreateResDto(){

        return TbrefreshtokenDto.CreateResDto.builder().id(this.getId()).build();
    }

}
