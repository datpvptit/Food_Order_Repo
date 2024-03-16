package com.datpham.foodorder.payload.Reponse;

import lombok.Data;

@Data
public class UserReponse {
    private Integer id;
    private String email;
    private String role;
    private String fullName;
    private boolean status;
}
