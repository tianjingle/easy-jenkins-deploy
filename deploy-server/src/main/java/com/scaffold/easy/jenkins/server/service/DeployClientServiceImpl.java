package com.scaffold.easy.jenkins.server.service;

import com.scaffold.easy.jenkins.server.api.request.DeploayTaskRequest;
import com.scaffold.easy.jenkins.server.api.response.ResponseResult;
import com.scaffold.easy.jenkins.server.infrastration.router.ro.SendPackRo;
import com.scaffold.easy.jenkins.server.infrastration.router.service.SendPackService;
import com.scaffold.easy.jenkins.server.service.deploy.DeployClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;

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
        ro.setPackName(request.getJavafilePath().replaceAll("_", Matcher.quoteReplacement("\\")));
        ro.setCommandName(request.getCommandPath().replaceAll("_",Matcher.quoteReplacement("\\")));
        ro.setServerUrl(request.getTargetServer());
        ro.setSave(request.isSaveOld());
        return sendPackService.upload2Serer(ro);
    }
}
