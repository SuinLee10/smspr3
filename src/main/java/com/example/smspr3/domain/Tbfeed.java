package com.example.smspr3.domain;

import com.example.smspr3.dto.TbfeedDto;
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
public class Tbfeed extends AuditingFields {
    @Setter @Column(nullable = false) private String tbuserId;
    @Setter @Column(nullable = false, length=400) private String title;
    @Setter @Column(nullable = true, length=10000) @Lob private String content;

    protected Tbfeed(){}
    private Tbfeed(String tbuserId, String title, String content){
        this.tbuserId = tbuserId;
        this.title = title;
        this.content = content;
    }
    public static Tbfeed of(String tbuserId, String title, String content){
        return new Tbfeed(tbuserId, title, content);
    }

    public TbfeedDto.CreateResDto toCreateResDto(){return TbfeedDto.CreateResDto.builder().id(this.getId()).build();}

}
