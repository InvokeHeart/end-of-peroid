package org.stop.eop.entity.po;

import lombok.Data;

@Data
public class Manager {
    //主键
    private Integer seq;

    //管理员名称
    private String name;
    //管理员联系方式
    private String phone;
    //登录密码
    private String pwd;
    //管理的楼栋号
    private String build;
}
