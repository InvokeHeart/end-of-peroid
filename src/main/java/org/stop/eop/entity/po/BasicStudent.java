package org.stop.eop.entity.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicStudent {
    // 学号
    private String studentId;
    // 学生姓名
    private String studentName;
}
