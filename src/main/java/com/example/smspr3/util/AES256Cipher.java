package com.example.smspr3.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

//암호화 복호화 동일한 키를 사용하는 대칭 키 알고리즘
//Advanced Encryption Standard
public class AES256Cipher {
    public static String temp_key = "12345678901234567890123456789012";
    //암호화
    public static String AES_Encode(String secretKey, String str)
            throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] keyData = secretKey.getBytes();
        String IV = secretKey.substring(0,16);
        //바이트 배열로부텉 SecretKey 생성
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        //암호화 과정에서 사용되는 암호화 인스턴스 얻음
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));
        //암호화 기능을 수행 doFinal 메서드를 통해 데이터를 암,복호화
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        //Base64 인코딩
        String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }
    //복호화 암호화된 문자열 입력 => Base64 디코딩 및 AES256 알고리즘으로 복호화 => 원래 문자열을 반환
    public static String AES_Decode(String secretKey, String str)throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
        byte[] keyData = secretKey.getBytes();

        String IV = secretKey.substring(0, 16);

        SecretKey secureKey = new SecretKeySpec(keyData, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));

        byte[] byteStr = Base64.decodeBase64(str.getBytes());
        return new String(c.doFinal(byteStr), "UTF-8");
    }

}
