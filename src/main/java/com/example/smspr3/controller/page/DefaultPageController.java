package com.example.smspr3.controller.page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import com.example.smspr3.util.FileUpload;

import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

@RequestMapping("")
@Controller
public class DefaultPageController {
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/agree")
    public String agree(){
        return "agree";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadfile/{file_name:.+}", method = {RequestMethod.GET, RequestMethod.POST})
    public byte[] getImage(@PathVariable("file_name") String file_name, HttpServletRequest request) throws Exception{
        String root_path = FileUpload.path(request);
        byte[] return_byte = null;
        File file = new File(root_path + file_name);
        InputStream in = null;
        try{
            in = new FileInputStream(file);
            return_byte = IOUtils.toByteArray(in);
        }catch(FileNotFoundException e){

        }catch(IOException e){

        }finally{
            if(in != null){
                try{
                    in.close();
                }catch(Exception e){

                }
            }
        }
        return return_byte;
    }
    @ResponseBody
    @RequestMapping(value = "/download/{file_name:.+}", method = RequestMethod.GET)
    public void download(@PathVariable("file_name") String file_name, @RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String root_path = FileUpload.path(request);
        File file = new File(root_path + file_name);
        //response 에 설정
        String mimeType = URLConnection.guessContentTypeFromName(file_name);
        if(mimeType == null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(file.getName(), "utf-8") +"\"");

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
}
