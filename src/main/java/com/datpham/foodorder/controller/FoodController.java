package com.datpham.foodorder.controller;

import com.datpham.foodorder.payload.ResponseData;
import com.datpham.foodorder.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/food")
@RequiredArgsConstructor
public class FoodController {


    private final FoodService foodService;

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addFood(@RequestParam MultipartFile file,
                                     @RequestParam String title,
                                     @RequestParam String timeShip,
                                     @RequestParam double price,
                                     @RequestParam String category_name){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = foodService.addFood(file, title,timeShip, price, category_name);
        if(isSuccess){
            responseData.setData("Add food successfully !");
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't add the food");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping("/update")
    public ResponseEntity<?> updateFood(@RequestParam int id,
                                        @RequestParam MultipartFile file,
                                        @RequestParam String title,
                                        @RequestParam String timeShip,
                                        @RequestParam double price,
                                        @RequestParam String category_name){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = foodService.updateFood(id, file, title,timeShip, price, category_name);
        if(isSuccess){
            responseData.setData("Update food successfully !");
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't Update the food");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping("/delete")
    public ResponseEntity<?> deleteFood(@RequestParam int id){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = foodService.deleteFood(id);
        if(isSuccess){
            responseData.setData("Delete food successfully !");
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't delete the food");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
