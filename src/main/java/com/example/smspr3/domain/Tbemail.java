package com.example.smspr3.domain;

import com.example.smspr3.dto.TbemailDto;
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
public class Tbemail extends AuditingFields{
    @Setter @Column(nullable = false, unique = true) private String username;
    @Setter @Column(nullable = false) private String number;
    @Setter @Column(nullable = false) private String due;
    protected Tbemail(){}
    private Tbemail(String username, String number, String due){
        this.username = username;
        this.number = number;
        this.due = due;
    }
    public static Tbemail of(String username, String number, String due){
        return new Tbemail(username, number, due);
    }

    public TbemailDto.CreateResDto toCreateResDto(){
        return TbemailDto.CreateResDto.builder().id(this.getId()).build();
    }

}
