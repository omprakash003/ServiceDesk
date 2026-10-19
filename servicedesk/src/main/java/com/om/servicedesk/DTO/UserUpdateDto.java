package com.om.servicedesk.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;


@Getter
@Setter
@NoArgsConstructor

public class UserUpdateDto {
    private String userName;
    private String ShortName;
    private String email;
    private String  phone;
    private String Role;

}
