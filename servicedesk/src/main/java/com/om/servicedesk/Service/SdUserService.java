package com.om.servicedesk.Service;

import com.om.servicedesk.DTO.LoginDTO;
import com.om.servicedesk.DTO.UserUpdateDto;
import com.om.servicedesk.Entity.SdUser;
import com.om.servicedesk.Exceptions.DuplicateResourceException;
import com.om.servicedesk.Exceptions.ResourceNotFoundException;
import com.om.servicedesk.Repos.SdUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SdUserService {
    @Autowired
    private SdUserRepo sdUserRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private  JWTService jwtService;
    public boolean SaveUser(SdUser user){

        if(sdUserRepo.findByPhone(user.getPhone()).isPresent()){
            throw new DuplicateResourceException(user.getPhone()+" is already registered");
        }
        if(sdUserRepo.findByEmail(user.getEmail()).isPresent()){
            throw new DuplicateResourceException(user.getEmail()+" is already registered");
        }
        if(sdUserRepo.findByUserName(user.getUserName()).isPresent()){
            throw new DuplicateResourceException(user.getUserName()+" is already registered");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        sdUserRepo.save(user);
        return true;
    }
    public SdUser getUser(Long Id){
        return sdUserRepo.findById(Id).orElseThrow(()->
                new ResourceNotFoundException("No userId found with UserId "+ Id));

    }
    public String verify(LoginDTO loginDTO){
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(),loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
           return  jwtService.generateToken(loginDTO.getUserName());

        }
        return null;

    }
    public boolean UpdatesByUser(UserUpdateDto userUpdateDto,Long userId){
        SdUser user =sdUserRepo.findById(userId).orElse(null);
        if(user!=null){
            if(userUpdateDto.getShortName()!=null) {
                user.setShortName(userUpdateDto.getShortName());
            }
            if(userUpdateDto.getPhone()!=null){
                if(sdUserRepo.findByPhone(userUpdateDto.getPhone()).isPresent()){
                    throw new DuplicateResourceException(userUpdateDto.getPhone()+" is already registered");
                }
                user.setPhone(userUpdateDto.getPhone());
            }
            if(userUpdateDto.getEmail()!=null){
                if(sdUserRepo.findByEmail(userUpdateDto.getEmail()).isPresent()){
                    throw new DuplicateResourceException(userUpdateDto.getEmail()+" is already registered");
                }
                user.setEmail(userUpdateDto.getEmail());
            }
            sdUserRepo.save(user);
            return true;
        }
        return false;
    }
    public boolean UpdatesByAdmin(UserUpdateDto userUpdateDto,Long userId){
        SdUser user =sdUserRepo.findById(userId).orElse(null);
        if(user!=null){
            if(userUpdateDto.getShortName()!=null) {
                user.setShortName(userUpdateDto.getShortName());
            }
            if(userUpdateDto.getUserName()!=null) {
                user.setUserName(userUpdateDto.getUserName());
            }
            if(userUpdateDto.getPhone()!=null){
                user.setPhone(userUpdateDto.getPhone());
            }
            if(userUpdateDto.getEmail()!=null){
                user.setEmail(userUpdateDto.getEmail());
            }
            if(userUpdateDto.getRole()!=null){
                user.setUserRole(userUpdateDto.getRole());
            }
            sdUserRepo.save(user);
            return true;
        }
        return false;


    }
    public boolean deleteUser(Long userId){
        if(!sdUserRepo.findById(userId).isPresent()){
            throw new ResourceNotFoundException("User Id doesn't exists..");
        }
        sdUserRepo.deleteById(userId);

        return true;
    }

}
