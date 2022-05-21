package org.stop.eop.entity.dto;

import lombok.Data;
import org.stop.eop.anno.Phone;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterDTO {

    @NotEmpty
    private String name;
    @Phone
    @NotEmpty
    private String phone;
    @NotEmpty
    private String pwd;
}
