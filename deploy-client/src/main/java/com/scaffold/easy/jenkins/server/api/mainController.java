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
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * 文件包上传
     * @param packRequest
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    public ResponseResult upload(PackRequest packRequest) throws Exception {
        String replaceFilePath="";
        String sourceFilePath=serverConfig.getFolder()+"/"+FilenameUtils.getBaseName(packRequest.getPackName())+"/";
        String repostoryPath=serverConfig.getRepostory()+"/"+FilenameUtils.getBaseName(packRequest.getPackName())+"/";
        StringBuffer outPut=new StringBuffer("");
        File folder=new File(sourceFilePath);
        File repostory=new File(repostoryPath);

        if (!folder.exists()){
            //创建大本营
            folder.mkdirs();
        }
        if (!repostory.exists()){
            //创建历史大本营
            repostory.mkdirs();
        }
        //先缓存一下
        for(int i=0;i<packRequest.getFiles().length;i++){
            //文件拷贝到发布大本营
            File source=new File(sourceFilePath+packRequest.getFiles()[i].getOriginalFilename());
            FileUtils.copyToFile(packRequest.getFiles()[i].getInputStream(),source);
        }
        //查看是否要进行备份
        String commans=CMDExecuteUtil.parseCommand(sourceFilePath+packRequest.getCommandName());
        String[] myCommand=commans.split("@");
        for(int i=0;i<myCommand.length;i++){
            if (myCommand[i].startsWith("set TARGET_PATH=")){
                String slipPath []=myCommand[i].split("=");
                if (slipPath.length==2){
                    replaceFilePath=slipPath[1];
                    Date now = new Date(); // 创建一个Date对象，获取当前时间
                    // 指定格式化格式
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                    System.out.println(f.format(now));
                    if (packRequest.isSave()) {
                        FileUtils.copyFile(new File(replaceFilePath), new File(repostoryPath + FilenameUtils.getBaseName(packRequest.getPackName()) + f.format(now) + "." + FilenameUtils.getExtension(packRequest.getPackName())));
                    }
                }else{
                    throw new Exception("没有目标文佳");
                }
            }
        }
            //往目标文件夹发布
            System.out.println(replaceFilePath);
            FileUtils.copyFile(new File(sourceFilePath+packRequest.getPackName()), new File(replaceFilePath));
        if ("bat".equals(FilenameUtils.getExtension(packRequest.getCommandName()))){
            outPut.append(CMDExecuteUtil.executeCommand("start "+packRequest.getCommandName(),new File(sourceFilePath))).append("\n");
            Thread.sleep(200);
            outPut.append("----------------------------------new--------------------------------------------").append("\n");
            outPut.append(CMDExecuteUtil.executeCommand("jps",new File(sourceFilePath))).append("\n");
            outPut.append("----------------------------------end--------------------------------------------").append("\n");
            System.out.println(outPut);
        }
        return ResponseResult.success(outPut);
    }
}
