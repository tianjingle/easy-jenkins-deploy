package com.scaffold.easy.jenkins.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scaffold.easy.jenkins.server.api.response.ResponseResult;
import com.scaffold.easy.jenkins.server.infrastration.router.ro.SendPackRo;
import com.scaffold.easy.jenkins.server.infrastration.router.service.SendPackService;
import com.sun.glass.ui.Application;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class LabApplicationTests {


    @Autowired
    private SendPackService sendPackService;


    @Test
    public void executeTask() {
        SendPackRo ro = new SendPackRo();
        ro.setCommandName("D:\\github\\cosin\\contact-center\\appserver\\target\\appserver.war");
        ro.setCommandName("D:\\github\\cosin\\contact-center\\appserver\\execute.sh");
        ro.setServerUrl("http://127.0.0.1:8082/Jdeploy/upload");
        ro.setSave(true);
        sendPackService.upload2Serer(ro);
    }

    public static void main(String[] args) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String basePath = "E:\\13real\\att1";
        String basePathCopy = "E:\\13real\\att1";
        String token="LID=WEEvREcwSlJHSldTTEYzVnBFbFF3dE43bVdkdzcwM29vUFNZN1Azc2N4ST0=$9A4hF_YAuvQ5obgVAqNKPCYcEjKensW4ggI8Fm4gTkoUKaID8j8gFw!!";
        String uploadURL = "https://wenku.cnki.net/wenku/file/upload?"+token;
        String addFile="https://wenku.cnki.net/wenku/document/addBatch?"+token;
        File dir = new File(basePath);

        List<File> allFiles = (List<File>) FileUtils.listFiles(dir, null, false);
        for (int i = 0; i < allFiles.size(); i++) {
            System.out.println(allFiles.size());
            if (true){
                break;
            }
            System.out.println(allFiles.get(i).getAbsoluteFile());
            System.out.println(allFiles.get(i).length());
            if (allFiles.get(i).length()/1024/1024>80){
                continue;
            }
            if (allFiles.get(i).getName().equalsIgnoreCase("海南省发展和改革委员会  海南省人力资源和社会保障厅关于印发《海南省“十三五”促进就业规划》的通知.doc")){
                continue;
            }
            //设置请求头
            HttpHeaders headers = new HttpHeaders();

            MediaType type = MediaType.parseMediaType("multipart/form-data");
            headers.setContentType(type);
            MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
            FileSystemResource fileSystemResource = new FileSystemResource(allFiles.get(i).getAbsoluteFile());
            form.add("file", fileSystemResource);
            //用HttpEntity封装整个请求报文
            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(form, headers);
            String result = restTemplate.postForObject(uploadURL, entity, String.class);
            ResponseResult responseResult=JSON.parseObject(result,ResponseResult.class);
            System.out.println(responseResult.toString());
            if (responseResult.getSuccess()){
                FileUploadVO vo= JSON.parseObject(responseResult.getContent().toString(),FileUploadVO.class);
                List<AddFiles> list=new ArrayList<>();
                AddFiles files=new AddFiles();
                files.setCategoryId("e15c0e96977aeb85d9c114620718fbd2");
                files.setOrigin(0);
                files.setEarnType(1);
                files.setPrice(0);
                files.setUnit("积分");
                files.setShowTagInput(false);
                files.setShowCustomPrice(false);
                files.setUid((int) System.currentTimeMillis());
                files.setTitle(vo.getFileName());
                files.setFormat(vo.getFormat());
                files.setStatus(1);
                files.setPercentage(100);
                files.setFileCode(vo.getFileCode());
                files.setConvertTaskId(vo.getConvertTaskId());
                files.setFileSize(vo.getFileSize());
                files.setLabels("");
                list.add(files);
                //构造请求头
                HttpHeaders header = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("ignore-identity", "true");
                System.out.println(JSON.toJSON(list));
                //请求体
                HttpEntity<?> requestEntity = new HttpEntity<>(JSON.toJSON(list), header);
                //发送请求
                String responseData = restTemplate.postForEntity(addFile, requestEntity, String.class).getBody();
                ResponseResult test=JSON.parseObject(responseData,ResponseResult.class);
                if (test.getSuccess()){
                    FileUtils.copyFile(new File(allFiles.get(i).getAbsolutePath()),new File(basePathCopy+"\\"+allFiles.get(i).getName()));
                    FileUtils.deleteQuietly(new File(allFiles.get(i).getAbsolutePath()));
                }
            }
        }
    }
}
