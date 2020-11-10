package com.scaffold.easy.jenkins.server.api;

import com.scaffold.easy.jenkins.server.api.request.PackRequest;
import com.scaffold.easy.jenkins.server.api.response.ResponseResult;
import com.scaffold.easy.jenkins.server.conf.ServerConfig;
import com.scaffold.easy.jenkins.server.utils.CMDExecuteUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 11:46
 * @Eamil: 2695062879@qq.com
 */
@RestController
public class mainController {

    /**
     * server配置
     */
    @Autowired
    private ServerConfig serverConfig;

    @PostMapping(value = "/upload")
    public ResponseResult upload(PackRequest packRequest) throws IOException {
        StringBuffer outPut=new StringBuffer("");
        File folder=new File(serverConfig.getFolder());
        if (!folder.exists()){
            folder.mkdir();
        }
        for(int i=0;i<packRequest.getFiles().length;i++){
            FileUtils.copyToFile(packRequest.getFiles()[i].getInputStream(),new File(serverConfig.getFolder()+"/"+packRequest.getFiles()[i].getOriginalFilename()));
        }
        if ("bat".equals(FilenameUtils.getExtension(packRequest.getCommandName()))){
            outPut.append(CMDExecuteUtil.executeCommand("start "+packRequest.getPackName(),new File(serverConfig.getFolder()+"/"))).append("\n");
            outPut.append(CMDExecuteUtil.executeCommand("jps",new File(serverConfig.getFolder()+"/"))).append("\n");
            System.out.println(outPut);
        }
        return ResponseResult.success(outPut);
    }
}
