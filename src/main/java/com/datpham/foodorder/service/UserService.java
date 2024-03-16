package com.datpham.foodorder.service;


import com.datpham.foodorder.dto.LoginDTO;
import com.datpham.foodorder.dto.RegisterDTO;
import com.datpham.foodorder.entities.Role;
import com.datpham.foodorder.entities.Users;
import com.datpham.foodorder.payload.Reponse.UserReponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface UserService {
   //ResponseEntity<?> register (RegisterDto registerDto);
 //  ResponseEntity<BearerToken> authenticate(LoginDto loginDto);

   String authenticate(LoginDTO loginDto);
   ResponseEntity<?> register (RegisterDTO registerDto);
   Role saveRole(Role role);
   Boolean deleteAccount(Integer id);
   Boolean confirmAccount(Integer id);
   Users saverUser (Users users) ;
   Users getByEmail(String email);
   List<UserReponse> getAllUser();
}
