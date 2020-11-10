package com.scaffold.easy.jenkins.server.api.request;


/**
 * @Author: tianjl
 * @Date: 2020/11/9 11:55
 * @Eamil: 2695062879@qq.com
 */

public class DeploayTaskRequest {


    /**
     * 目标端接受文件的地址
     */
    private String targetServer;

    /**
     * 是否对旧版本进行备份
     */
    private boolean saveOld;

    /**
     * 需要移动的文件
     */
    private String javafilePath;

    /**
     * 需要执行的指令地址
     */
    private String commandPath;


    public String getTargetServer() {
        return targetServer;
    }

    public void setTargetServer(String targetServer) {
        this.targetServer = targetServer;
    }

    public boolean isSaveOld() {
        return saveOld;
    }

    public void setSaveOld(boolean saveOld) {
        this.saveOld = saveOld;
    }

    public String getJavafilePath() {
        return javafilePath;
    }

    public void setJavafilePath(String javafilePath) {
        this.javafilePath = javafilePath;
    }

    public String getCommandPath() {
        return commandPath;
    }

    public void setCommandPath(String commandPath) {
        this.commandPath = commandPath;
    }
}
