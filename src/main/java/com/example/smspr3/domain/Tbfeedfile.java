package com.example.smspr3.domain;

import com.example.smspr3.dto.TbfeedDto;
import com.example.smspr3.dto.TbfeedfileDto;
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
public class Tbfeedfile extends AuditingFields {
    @Setter @Column(nullable = false) private String tbfeedId;
    @Setter @Column(nullable = false, length=400) private String type;
    @Setter @Column(nullable = true, length=10000) @Lob private String url;

    protected Tbfeedfile(){}
    private Tbfeedfile(String tbfeedId, String type, String url){
        this.tbfeedId = tbfeedId;
        this.type = type;
        this.url = url;
    }
    public static Tbfeedfile of(String tbfeedId, String type, String url){
        return new Tbfeedfile(tbfeedId, type, url);
    }

    public TbfeedfileDto.CreateResDto toCreateResDto(){return TbfeedfileDto.CreateResDto.builder().id(this.getId()).build();}

}
