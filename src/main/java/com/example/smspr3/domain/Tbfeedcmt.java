package com.example.smspr3.domain;

import com.example.smspr3.dto.TbfeedcmtDto;
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
public class Tbfeedcmt extends AuditingFields {
    @Setter @Column(nullable = false) private String tbfeedId;
    @Setter @Column(nullable = false) private String tbuserId;
    @Setter @Column(nullable = false, length=400) private String content;

    protected Tbfeedcmt(){}
    private Tbfeedcmt(String tbfeedId, String tbuserId, String content){
        this.tbfeedId = tbfeedId;
        this.tbuserId = tbuserId;
        this.content = content;
    }
    public static Tbfeedcmt of(String tbfeedId, String tbuserId, String content){
        return new Tbfeedcmt(tbfeedId,tbuserId,content);
    }

    public TbfeedcmtDto.CreateResDto toCreateResDto(){return TbfeedcmtDto.CreateResDto.builder().id(this.getId()).build();}

}
