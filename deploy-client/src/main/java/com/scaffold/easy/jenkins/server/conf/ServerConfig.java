package com.scaffold.easy.jenkins.server.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tianjl
 * @Date: 2020/11/9 13:39
 * @Eamil: 2695062879@qq.com
 */
@Configuration
@ConfigurationProperties(prefix = "com.scaffold.easy.jenkins")
public class ServerConfig {

    private String folder;

    private String repostory;

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getRepostory() {
        return repostory;
    }

    public void setRepostory(String repostory) {
        this.repostory = repostory;
    }
}
