package com.scaffold.easy.jenkins.server.api.request;


import org.springframework.web.multipart.MultipartFile;


/**
 * @Author: tianjl
 * @Date: 2020/11/9 12:24
 * @Eamil: 2695062879@qq.com
 */
public class PackRequest {

    private MultipartFile[] files;
    /**
     * 发布包的名称
     */
    private String packName;

    /**
     * 执行的命令文件
     */
    private String commandName;

    /**
     * 是否保存老版本
     */
    private boolean save;

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public boolean isSave() {
        return save;
    }

    public void setSave(boolean save) {
        this.save = save;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "PackRequest{" +
                "list=" + files +
                ", packName='" + packName + '\'' +
                ", commandName='" + commandName + '\'' +
                ", save=" + save +
                '}';
    }
}
