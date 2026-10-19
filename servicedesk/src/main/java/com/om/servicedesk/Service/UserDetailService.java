package com.om.servicedesk.Service;

import com.om.servicedesk.Entity.SdUser;
import com.om.servicedesk.Entity.UserPrinciple;
import com.om.servicedesk.Exceptions.ResourceNotFoundException;
import com.om.servicedesk.Repos.SdUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private SdUserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SdUser user= userRepo.findByUserName(username).
                orElseThrow(()->new UsernameNotFoundException("User Not found with username"+ username));
        return new UserPrinciple(user);
    }
}
