package com.om.servicedesk.Controller;

import com.om.servicedesk.DTO.LoginDTO;
import com.om.servicedesk.DTO.UserUpdateDto;
import com.om.servicedesk.Entity.SdUser;
import com.om.servicedesk.Service.SdUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class SdUserController {
    @Autowired
    private SdUserService userService;
    @GetMapping("/get-user/{userId}")
    public ResponseEntity<SdUser> getUser(@PathVariable Long userId){
        SdUser user=userService.getUser(userId);

            return new ResponseEntity<>(user, HttpStatus.OK);


    }
    @PostMapping("/register")
    public ResponseEntity<String> adduser(@RequestBody SdUser user){
        Boolean success=userService.SaveUser(user);
        if(success){
            return new ResponseEntity<>(user.getShortName()+" you are successfully registered",HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(user.getShortName()+" registration unsuccessful",HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO user){
        String token=userService.verify(user);
        if(token !=null) {
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Failed",HttpStatus.UNAUTHORIZED);

    }
    @PutMapping("/update/{userid}/user")
    public ResponseEntity<String>updateByUser(@RequestBody UserUpdateDto userDto,@PathVariable Long userid){
        if(userService.UpdatesByUser(userDto,userid)){
            return new ResponseEntity<>("your updates saved",HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("your updates did not succeed",HttpStatus.NOT_ACCEPTABLE);
    }
    @PutMapping("/update/admin/{userid}")
    public ResponseEntity<String>updateByAdmin(@RequestBody UserUpdateDto userDto,@PathVariable Long userid){
        if(userService.UpdatesByAdmin(userDto,userid)){
            return new ResponseEntity<>("your updates saved",HttpStatus.ACCEPTED);

        }
        return new ResponseEntity<>("your updates did not succeed",HttpStatus.NOT_ACCEPTABLE);
    }
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("User Deleted",HttpStatus.OK);
    }


}
