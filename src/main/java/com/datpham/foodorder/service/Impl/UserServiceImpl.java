package com.datpham.foodorder.service.Impl;

import com.datpham.foodorder.dto.BearerToken;
import com.datpham.foodorder.dto.LoginDTO;
import com.datpham.foodorder.dto.RegisterDTO;
import com.datpham.foodorder.entities.Role;
import com.datpham.foodorder.entities.RoleName;
import com.datpham.foodorder.entities.Users;
import com.datpham.foodorder.payload.Reponse.UserReponse;
import com.datpham.foodorder.service.UserService;
import com.datpham.foodorder.repository.RoleRepository;
import com.datpham.foodorder.repository.UserRepository;
import com.datpham.foodorder.security.JwtUtilities;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager ;
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder ;
    private final JwtUtilities jwtUtilities ;


    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public Boolean deleteAccount(Integer id) {
        try {
            userRepository.deleteById(id);
            return true;
        }catch (RuntimeException e){
            throw new RuntimeException("Error delete accout");
        }
    }

    @Override
    @Transactional
    public Boolean confirmAccount(Integer id) {
        try {
            Users users = userRepository.findById(id).orElse(null);
            users.setIscheck(true);
            userRepository.save(users);
            return true;
        }catch (RuntimeException e){
            throw new RuntimeException("Error confirm accout");
        }
    }


    @Override
    @Transactional
    public Users saverUser(Users users) {
        return userRepository.save(users);
    }

    @Override
    public Users getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserReponse> getAllUser() {
        List<Users> usersList = userRepository.findAll();
        if (usersList.isEmpty()){
            return null;
        }
        List<UserReponse> userReponseList = new ArrayList<>();
        for(Users users : usersList){
            UserReponse userReponse = new UserReponse();
            userReponse.setId(users.getId());
            userReponse.setEmail(users.getEmail());
            userReponse.setFullName(users.getFulname());
            userReponse.setStatus(users.isIscheck());
            List<Role> roles = users.getRoles();
            String roleNamesString = roles.stream()
                    .map(Role::getRoleName) // Chuyển đổi từ Role sang roleName
                    .collect(Collectors.joining("/"));
            if(roleNamesString.contains("SUPERADMIN")) continue;
            userReponse.setRole(roleNamesString);
            userReponseList.add(userReponse);
        }
        return userReponseList;
    }

    @Override
    public ResponseEntity<?> register(RegisterDTO registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail()))
        { return  new ResponseEntity<>("email is already taken !", HttpStatus.SEE_OTHER); }
        else
        { Users users = new Users();
            users.setEmail(registerDto.getEmail());
            users.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            Role role = roleRepository.findByRoleName(RoleName.USER);
            users.setRoles(Collections.singletonList(role));
            userRepository.save(users);
            String token = jwtUtilities.generateToken(registerDto.getEmail(),Collections.singletonList(role.getRoleName()));
            return new ResponseEntity<>(new BearerToken(token , "Bearer "),HttpStatus.OK);

        }
        }

    @Override
    public String authenticate(LoginDTO loginDto) {
      Authentication authentication= authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Users users = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<String> rolesNames = new ArrayList<>();
        users.getRoles().forEach(r-> rolesNames.add(r.getRoleName()));
        String token = jwtUtilities.generateToken(users.getUsername(),rolesNames);
        return token;
    }

}

