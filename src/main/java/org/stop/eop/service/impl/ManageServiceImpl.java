package org.stop.eop.service.impl;

import org.springframework.stereotype.Service;
import org.stop.eop.entity.dto.LoginDTO;
import org.stop.eop.entity.dto.RegisterDTO;
import org.stop.eop.entity.po.Manager;
import org.stop.eop.mapper.ManageMapper;
import org.stop.eop.service.ManageService;

import java.util.Objects;

@Service
public class ManageServiceImpl implements ManageService {
    private final ManageMapper mapper;

    ManageServiceImpl(ManageMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public boolean login(LoginDTO loginDTO) {

        String phone = loginDTO.getPhone().trim();
        String pwd = loginDTO.getPwd().trim();

        Manager managerByPhone = mapper.getManagerByPhone(phone);
        if (Objects.nonNull(managerByPhone)) {
            //手机号与密码一致 (这种写的 真的不要太low了..)
            return phone.equals(managerByPhone.getPhone()) && pwd.equals(managerByPhone.getPwd());
        }
        //用户不存在
        return false;
    }

    @Override
    public boolean register(RegisterDTO registerDTO) {
        Manager managerByPhone = mapper.getManagerByPhone(registerDTO.getPhone());
        if (Objects.nonNull(managerByPhone)) {
            return false;
        }
        return mapper.addManagerInfo(registerDTO) > 0;
    }


}
