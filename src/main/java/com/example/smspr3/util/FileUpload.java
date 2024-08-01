package com.example.smspr3.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Component
public class FileUpload {
    private final KeysProperties keysProperties;
    public FileUpload(
            KeysProperties keysProperties
    ){
        this.keysProperties = keysProperties;
    }
    public String s3(MultipartFile mf) throws IOException {
        String returnValue = "";
        try{
            String filename = setFileName(mf);
            AWSCredentials credentials = new BasicAWSCredentials(keysProperties.getAccess_key(), keysProperties.getSecret_key());
            AmazonS3 s3 = AmazonS3ClientBuilder
                    .standard()
                    .withCredentials(new AWSStaticCredentialsProvider(credentials))
                    .withRegion(Regions.AP_NORTHEAST_2)
                    .build();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(mf.getSize());
            metadata.setContentType(mf.getContentType());

            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(
                            keysProperties.getBucket_name() + "/" + keysProperties.getProject_folder_name()
                            ,filename
                            ,mf.getInputStream()
                            ,metadata
                    );
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            s3.putObject(putObjectRequest);
            returnValue = keysProperties.getRead_s3_url() + keysProperties.getProject_folder_name() + "/" + filename;
        }catch (Exception e){
            System.out.println("======================Exception : " + e);
        }
        return returnValue;
    }
    public static String local(
            MultipartFile mf, HttpServletRequest request
    ) throws IOException {
        String returnValue = "";
        try{
            String root_path = path(request);
            setDir(root_path);
            String filename = setFileName(mf);

            FileCopyUtils.copy(mf.getBytes(), new File(root_path + filename));
            returnValue = "/uploadfile/" + filename;
        }catch (Exception e) {
            System.out.println("======================Exception : " + e);
        }
        return returnValue;
}
    public static String rootPath(HttpServletRequest request){
        String root_path = request.getSession().getServletContext().getRealPath("/");
        root_path = root_path.replace("\\", "/");
        return root_path;
    }
    public static String path(HttpServletRequest request) throws IOException {
        String root_path = rootPath(request);
        root_path = root_path.replace("wtpwebapps", "uploadfiles");
        root_path = root_path.replace("webapps", "uploadfiles");

        if(root_path.indexOf("C:/") == 0){
            root_path = "C:/workspace4/";
        }else if(root_path.indexOf("Users/") == 0){
            root_path = "Users/workspace4/";
        }
        String attach_path = "uploadfiles/smspr3/";
        return root_path + attach_path;
    }
    public static String setFileName(MultipartFile mf){
        String result = "";
        if(mf == null || "".equals(mf.getOriginalFilename() + "")){
        }else{
            Date date = new Date();
            String temp_date = date.getTime() + "";
            String filename = mf.getOriginalFilename();
            String extension = FilenameUtils.getExtension(filename);
            if(extension == null || "".equals(extension)){
                extension = "nnn";
            }
            filename = filename.replaceAll(" ", "");
            result = temp_date + "_" + filename;
        }
        return result;
    }
    public static void setDir(String root_path){
        File newfile = new File(root_path);
        if(!newfile.exists()){
            newfile.mkdirs();
        }
    }
}