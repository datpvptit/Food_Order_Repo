package com.datpham.foodorder.service;


import com.datpham.foodorder.dto.LoginDto;
import com.datpham.foodorder.dto.RegisterDto;
import com.datpham.foodorder.entities.Role;
import com.datpham.foodorder.entities.Users;
import org.springframework.http.ResponseEntity;


public interface UserService {
   //ResponseEntity<?> register (RegisterDto registerDto);
 //  ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

   String authenticate(LoginDto loginDto);
   ResponseEntity<?> register (RegisterDto registerDto);
   Role saveRole(Role role);

   Users saverUser (Users users) ;
   Users getByEmail(String email);
}
