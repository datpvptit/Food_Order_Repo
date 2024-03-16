package com.datpham.foodorder.controller;

import com.datpham.foodorder.dto.OrderDTO;
import com.datpham.foodorder.payload.ResponseData;
import com.datpham.foodorder.repository.UserRepository;
import com.datpham.foodorder.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("/get-all")
    public ResponseEntity<?> getList(){
        ResponseData responseData = new ResponseData();
        responseData.setData(userService.getAllUser());
        if(responseData.getData() != null){
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't get all user");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirm(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(userService.confirmAccount(id));
        if(responseData.isSuccess()){
            responseData.setData("Successfuly confirm user acount");
        }else {
            responseData.setData("Error confirm user acount");
            responseData.setStatus(400);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        responseData.setSuccess(userService.deleteAccount(id));
        if(responseData.isSuccess()){
            responseData.setData("Successfuly delete user acount");
        }else {
            responseData.setData("Error delete user acount");
            responseData.setStatus(400);
        }

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
