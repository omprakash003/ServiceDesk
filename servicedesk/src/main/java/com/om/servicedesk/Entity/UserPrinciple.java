package com.om.servicedesk.Entity;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrinciple implements UserDetails {
    private SdUser user;
    public UserPrinciple(SdUser user){
        this.user=user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority("ROLE_" + user.getUserRole())
        );

    }

    @Override
    public  String getPassword() {
       return  user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }
}
