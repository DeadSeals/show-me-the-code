package org.ds.msgboard.dto;

public class MsgResult {

    public static final String SUCC_MSG = "请求成功";
    public static final String FAIL_MSG = "请求失败";
    public static final String ADDMSG_FAIL_MSG = "留言失败，请稍候再试";
    public static final String PARAM_ERR_MSG = "参数有误，请检查";
    public static final String NET_ERR_MSG = "网络异常，请稍候再试";
    public static final String BLANK_DATA = "";

    public static final Integer SUCC_CODE = 1;
    public static final Integer FAIL_CODE = 0;

    private Integer status;

    private String msg;

    private Object data;

    public MsgResult() {
    }

    public MsgResult(Object data) {
        this.data = data;
        this.status = SUCC_CODE;
        this.msg = SUCC_MSG;
    }

    public MsgResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public MsgResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = BLANK_DATA;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MsgResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
