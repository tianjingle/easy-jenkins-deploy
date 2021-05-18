package com.scaffold.easy.jenkins.server;


/**
 * @author syr
 */
public class FileUploadVO {
    /**
     * 文件上传后存储在HFS上的文件名
     */
    private String fileCode;

    /**
     * 文件标题名称 不带后缀名
     */
    private String fileName;

    /**
     * 文件格式 .jpg
     */
    private String format;

    /**
     * 转换任务id
     */
    private Integer convertTaskId;

    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 上传成功与否
     */
    private Boolean success;
    /**
     * 提示信息
     */
    private String message;

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getConvertTaskId() {
        return convertTaskId;
    }

    public void setConvertTaskId(Integer convertTaskId) {
        this.convertTaskId = convertTaskId;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FileUploadVO(String fileCode, String fileName, String format, Integer convertTaskId, Long fileSize, Boolean success, String message) {
        this.fileCode = fileCode;
        this.fileName = fileName;
        this.format = format;
        this.convertTaskId = convertTaskId;
        this.fileSize = fileSize;
        this.success = success;
        this.message = message;
    }


}
