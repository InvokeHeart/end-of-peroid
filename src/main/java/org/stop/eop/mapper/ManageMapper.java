package org.stop.eop.mapper;

import org.stop.eop.entity.dto.RegisterDTO;
import org.stop.eop.entity.dto.StudentDTO;
import org.stop.eop.entity.po.Manager;

public interface ManageMapper {
    //根据手机号查找管理员
    Manager getManagerByPhone(String phone);

    Integer addManagerInfo(RegisterDTO registerDTO);
}
