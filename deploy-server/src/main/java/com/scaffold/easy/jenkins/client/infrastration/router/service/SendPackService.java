package com.scaffold.easy.jenkins.client.infrastration.router.service;

import ch.qos.logback.core.util.FileUtil;
import com.scaffold.easy.jenkins.client.api.response.ResponseResult;
import com.scaffold.easy.jenkins.client.infrastration.router.ro.SendPackRo;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 12:27
 * @Eamil: 2695062879@qq.com
 */
@Service
public class SendPackService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 发送发布包到服务器
     * @param ro
     * @return
     */
    public ResponseResult upload2Serer(SendPackRo ro){

        //设置请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        FileSystemResource fileSystemResource =new FileSystemResource(ro.getPackName());
        FileSystemResource fileSystemResource1 =new FileSystemResource(ro.getCommandName());
        form.add("files", fileSystemResource);
        form.add("files", fileSystemResource1);
        form.add("packName", FilenameUtils.getName(ro.getPackName()));
        form.add("commandName",FilenameUtils.getName(ro.getCommandName()));
        //用HttpEntity封装整个请求报文
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
        String s = restTemplate.postForObject(ro.getServerUrl(), entity, String.class);
        System.out.println(s);
        return new ResponseResult();
    }
}
