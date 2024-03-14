package com.datpham.foodorder.controller;

import com.datpham.foodorder.payload.ResponseData;
import com.datpham.foodorder.payload.request.CategoryReponse;
import com.datpham.foodorder.service.CategoryService;
import com.datpham.foodorder.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryReponse categoryReponse){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryService.addCateGory(categoryReponse.getName());
        if(isSuccess){
            responseData.setData(categoryService.findByName(categoryReponse.getName()));
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't add the category");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryReponse categoryReponse){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryService.updateCategory(categoryReponse.getName(), categoryReponse.getNewName());
        if(isSuccess){
            responseData.setData(categoryService.findByName(categoryReponse.getName()));
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't update the category");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCategory(@RequestBody CategoryReponse categoryReponse){
        ResponseData responseData = new ResponseData();
        boolean isSuccess = categoryService.deleteCategory(categoryReponse.getName());
        if(isSuccess){
            responseData.setData("Delete category " + categoryReponse.getName() + "successfully !");
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't delete the category");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
