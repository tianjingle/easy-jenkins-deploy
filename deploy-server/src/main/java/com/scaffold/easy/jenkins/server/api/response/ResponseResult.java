package com.scaffold.easy.jenkins.server.api.response;



/**
 * @author tianjl
 */
public class ResponseResult {

    /**
     * 返回数据条数
     */
    private Integer count;
    /**
     * 匹配的总条数
     */
    private Integer total;

    /**
     * 响应业务状态
     */
    private Boolean success;
    /**
     * 响应消息
     */
    private String message;
    /**
     * 响应中的数据
     */
    private Object content;


    public ResponseResult() {
    }

    public ResponseResult(Boolean success, String msg, Object content, Integer count, Integer total) {
        this.success = success;
        this.message = msg;
        this.count = count;
        this.total = total;
        this.content = content;
    }
    public static ResponseResult success(Object content, Integer count, Integer total,String message) {
        return new ResponseResult(true, message, content, count, total);
    }

    public static ResponseResult success(Object content, Integer count, Integer total) {
        return new ResponseResult(true, null, content, count, total);
    }

    public static ResponseResult success(Object content) {
        return new ResponseResult(true, null, content, 1, 1);
    }

    public static ResponseResult success(Object content, String msg) {
        return new ResponseResult(true, msg, content, 1, 1);
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult(false, msg, null, 0, 0);
    }
    public static ResponseResult error(String msg,Object content) {
        return new ResponseResult(false, msg, content, 0, 0);
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
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

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "count=" + count +
                ", total=" + total +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", content=" + content +
                '}';
    }
}
