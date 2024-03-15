package com.datpham.foodorder.controller;


import com.datpham.foodorder.dto.OrderDTO;
import com.datpham.foodorder.payload.ResponseData;
import com.datpham.foodorder.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDTO orderDTO){
        ResponseData responseData = orderService.addOrder(orderDTO);
        if(responseData != null){
            responseData.setSuccess(true);
        }else{
            responseData.setData("Can't add the order");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllOrder(){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderService.getAll());
        if(responseData.getData() != null){
            responseData.setSuccess(true);
        }else{
            responseData.setDesc("Can't get the order list");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('SUPERADMIN')")
    @GetMapping("/get-detail/{id}")
    public ResponseEntity<?> getDetail(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderService.getDetail(id));
        if(responseData.getData() != null){
            responseData.setSuccess(true);
        }else{
            responseData.setDesc("Can't get the order list");
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
