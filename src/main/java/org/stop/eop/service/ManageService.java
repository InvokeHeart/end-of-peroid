package org.stop.eop.service;

import org.stop.eop.entity.dto.LoginDTO;
import org.stop.eop.entity.dto.RegisterDTO;

//manage表相关的一些methods
public interface ManageService {
    boolean login(LoginDTO loginDTO);

    boolean register(RegisterDTO registerDTO);
}
