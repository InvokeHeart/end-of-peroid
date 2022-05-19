package org.stop.eop.entity.resp;

public enum ResultCode {
    SUCCESS(2000, "success"),
    FAIL(5000, "fail"),
    UnAUTH(3000, "请先登录"),

    LOGIN_SUCCESS(2222, "登录成功"),

    LOGIN_FAIL(3333, "登录失败");
    private Integer code;
    private String codeDesc;

    ResultCode(Integer code, String codeDesc) {
        this.code = code;
        this.codeDesc = codeDesc;
    }


    public Integer getCode() {
        return code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
