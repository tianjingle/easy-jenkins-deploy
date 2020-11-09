package com.scaffold.easy.jenkins.client.service.deploy;

import com.scaffold.easy.jenkins.client.api.request.DeploayTaskRequest;
import com.scaffold.easy.jenkins.client.api.response.ResponseResult;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 11:52
 * @Eamil: 2695062879@qq.com
 */
public interface DeployClientService {
    /**
     * 上传到服务端
     * @param request
     * @return
     */
    ResponseResult deploy(DeploayTaskRequest request);
}
