package com.example.smspr3.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenFactory {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //token 생성
    public String generate(String tbuserId, long sec){
        long expiredMillSec = 0;
        Date nowDate = new Date();
        //현재 시각
        logger.info("now : " + nowDate.getTime());
        nowDate.setTime(nowDate.getTime() + sec * 1000);
        expiredMillSec = nowDate.getTime();
        //expired 시간
        logger.info("expiredMillSec : " + expiredMillSec);
        String token = "";
        try{
            String secretKey = "1234567890123456";
            token = AES256Cipher.AES_Encode(secretKey, tbuserId + "_" + expiredMillSec);
        } catch(Exception e){
            throw new RuntimeException("AES encrypt failed");
        }
        return token;
    }
    public String verify(String token){
        String decodeToken = "";
        try{
            String secretKey = "1234567890123456";
            decodeToken = AES256Cipher.AES_Decode(secretKey, token);
            String[] arrayToken = decodeToken.split("_");
            long expiredMillSec = Long.parseLong(arrayToken[1]);
            Date nowDate = new Date();
            long nowMillSec = nowDate.getTime();
            if(nowMillSec < expiredMillSec){
                return arrayToken[0];
            } else{
              throw new RuntimeException("expired");
            }
        }catch(Exception e){
            throw new RuntimeException("AES encrypt failed");
        }
    }
    public String refreshToken(String tbuserId){
        return generate(tbuserId, 60 * 60 * 24 * 7);
    }
    public String accessToken(String refreshToken){
        return generate(verify(refreshToken), 60 * 60 * 24 * 2);
    }
}
