package com.example.smspr3.domain;

import com.example.smspr3.dto.TbfeedcmtDto;
import com.example.smspr3.dto.TbfeedlikeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(indexes = {
        @Index(columnList = "deleted")
        ,@Index(columnList = "process")
        ,@Index(columnList = "createdAt")
        ,@Index(columnList = "modifiedAt")
    }
    ,uniqueConstraints = {
        @UniqueConstraint(
                name="UQ_tbfeedlike_tbfeed_id_tbuser_id"
                ,columnNames = {"tbfeedId", "tbuserId"}
        )
})
@Entity
public class Tbfeedlike extends AuditingFields {
    @Setter @Column(nullable = false) private String tbfeedId;
    @Setter @Column(nullable = false) private String tbuserId;
    protected Tbfeedlike(){}
    private Tbfeedlike(String tbfeedId, String tbuserId){
        this.tbfeedId = tbfeedId;
        this.tbuserId = tbuserId;
    }
    public static Tbfeedlike of(String tbfeedId, String tbuserId){
        return new Tbfeedlike(tbfeedId,tbuserId);
    }

    public TbfeedlikeDto.CreateResDto toCreateResDto(){return TbfeedlikeDto.CreateResDto.builder().id(this.getId()).build();}

}
