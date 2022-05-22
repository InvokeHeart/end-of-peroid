package org.stop.eop.entity.po;

import lombok.Data;

@Data
public class BuildCount {
    //楼栋
    private Integer build;
    //已经入住的人数
    private Integer count;
}
