package com.scaffold.easy.jenkins.server;

import java.lang.annotation.Documented;
import java.util.Date;

/**
 * @Author: tianjl
 * @Date: 2021/1/13 9:53
 * @Eamil: 2695062879@qq.com
 */

public class AddFiles {


    /**
     * 文档名称
     */
    private String title;

    /**
     * 文档后缀，类型
     */
    private String format;



    /**
     * 标签
     */
    private String labels;

    /**
     * 分类id
     */
    private String categoryId;


    /**
     * 简介
     */
    private String introduction;

    /**
     * 售价
     */
    private Integer price;

    /**
     * 售价单位
     */
    private String unit;

    /**
     * 收益类型
     */
    private Integer earnType;

    /**
     * 状态（审核中、驳回、下架、通过、草稿）
     */
    private Integer status;



    /**
     * 文档标识
     */
    private String fileCode;



    /**
     * 转换任务id
     */
    private Integer convertTaskId;



    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 目标转化类型
     */
    private String targetFormat = ".pdf";



    /**
     * 来源，0网路，1原创，2其他
     */
    private Integer origin=0;


    /**
     * 来源说明
     */
    private String originMessage;


    private boolean showTagInput;

    private boolean showCustomPrice;

    private int uid;


    private int percentage;

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public boolean isShowTagInput() {
        return showTagInput;
    }

    public void setShowTagInput(boolean showTagInput) {
        this.showTagInput = showTagInput;
    }

    public boolean isShowCustomPrice() {
        return showCustomPrice;
    }

    public void setShowCustomPrice(boolean showCustomPrice) {
        this.showCustomPrice = showCustomPrice;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getEarnType() {
        return earnType;
    }

    public void setEarnType(Integer earnType) {
        this.earnType = earnType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
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

    public String getTargetFormat() {
        return targetFormat;
    }

    public void setTargetFormat(String targetFormat) {
        this.targetFormat = targetFormat;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public String getOriginMessage() {
        return originMessage;
    }

    public void setOriginMessage(String originMessage) {
        this.originMessage = originMessage;
    }
}
