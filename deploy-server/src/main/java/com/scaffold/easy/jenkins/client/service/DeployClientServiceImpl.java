package com.scaffold.easy.jenkins.client.service;

import com.scaffold.easy.jenkins.client.api.request.DeploayTaskRequest;
import com.scaffold.easy.jenkins.client.api.response.ResponseResult;
import com.scaffold.easy.jenkins.client.infrastration.router.ro.SendPackRo;
import com.scaffold.easy.jenkins.client.infrastration.router.service.SendPackService;
import com.scaffold.easy.jenkins.client.service.deploy.DeployClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 11:52
 * @Eamil: 2695062879@qq.com
 */
@Service
public class DeployClientServiceImpl implements DeployClientService {

    @Autowired
    private SendPackService sendPackService;

    /**
     * 上传到服务端
     * @param request
     * @return
     */
    @Override
    public ResponseResult deploy(DeploayTaskRequest request) {

        SendPackRo ro=new SendPackRo();
        ro.setPackName("D:\\github\\cosin\\contact-center\\appserver\\target\\appserver.war");
        ro.setCommandName("D:\\github\\cosin\\contact-center\\appserver\\execute.bat");
        ro.setServerUrl("http://127.0.0.1:8082/Jdeploy/upload");
        ro.setSave(true);
        return sendPackService.upload2Serer(ro);
    }


}
