package com.scaffold.easy.jenkins.server.api;

import com.scaffold.easy.jenkins.server.api.request.DeploayTaskRequest;
import com.scaffold.easy.jenkins.server.api.response.ResponseResult;
import com.scaffold.easy.jenkins.server.service.deploy.DeployClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 11:51
 * @Eamil: 2695062879@qq.com
 */
@RestController
public class DeployController {


    /**
     * 业务处理
     */
    @Autowired
    private DeployClientService deployService;


    @GetMapping(value = "/task/deploy")
    public ResponseResult executeTask(@ModelAttribute DeploayTaskRequest request){

        return deployService.deploy(request);
    }
}
