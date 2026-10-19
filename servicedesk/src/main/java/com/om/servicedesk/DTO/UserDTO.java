package com.om.servicedesk.DTO;

import com.om.servicedesk.Entity.SdUser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private String name;
    private String email;
    private String phNumber;
    public UserDTO(SdUser user){
        this.userName=user.getUserName();
        this.name=user.getShortName();
        this.email=user.getEmail();
        this.phNumber=user.getPhone();

    }
}
