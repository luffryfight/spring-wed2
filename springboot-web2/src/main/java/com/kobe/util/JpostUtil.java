package com.kobe.util;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * to do :
 *
 * @author 86137
 * @date 2021/3/8 19:27
 */
public class JpostUtil {
    /**
     *
     *  发送文件MultipartFile类型的参数请求第三方接口
     * @param url  请求url
     * @param file 参数
     * @return 字符流
     */
    public static String JPost(String url, MultipartFile file, String token) throws IOException {
        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(convert(file)));
        HttpHeaders headers = new HttpHeaders();
        headers.add("accept", "*/*");
        headers.add("connection", "Keep-Alive");
        headers.add("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        headers.add("Accept-Charset", "utf-8");
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("token", token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        String body = response.getBody();
        return body;
    }

    /**
     * 接收处理传过来的文件
     * @param file MultipartFile 类型的文件
     * @return
     */
    public static File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
        try {
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return convFile;
    }
}
