package org.stop.eop.entity.dto;

import lombok.Data;
import org.stop.eop.anno.Phone;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    @Phone
    @NotEmpty
    private String phone;
    @NotEmpty
    private String pwd;
}
