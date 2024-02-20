package com.datpham.foodorder.payload;
public class ResponseData {
    private int status=200;
    private boolean isSuccess = false;
    private String desc;
    private Object data;
    public int getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }


}
