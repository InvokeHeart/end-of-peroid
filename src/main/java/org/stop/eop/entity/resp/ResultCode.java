package org.stop.eop.entity.resp;

public enum ResultCode {
    SUCCESS(2000, "success"),
    FAIL(5000, "fail");
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
