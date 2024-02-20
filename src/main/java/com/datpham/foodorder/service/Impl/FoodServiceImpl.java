package com.datpham.foodorder.service.Impl;

import com.datpham.foodorder.dto.FoodDTO;
import com.datpham.foodorder.entities.Food;
import com.datpham.foodorder.repository.FoodRepository;
import com.datpham.foodorder.service.CategoryService;
import com.datpham.foodorder.service.FileService;
import com.datpham.foodorder.service.FoodService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FileService fileService;
    private final CategoryService categoryService;
    private final FoodRepository foodRepository;

    @Override
    public boolean addFood(MultipartFile file, String title, String timeShip, double price, String category_name) {
        boolean isInsertSuccess = false;
        try {
            boolean isSuccess = fileService.saveFile(file, "food");
            if(isSuccess){
                Food food = new Food();
                food.setTitle(title);
                food.setTimeShip(timeShip);
                food.setPrice(price);
                food.setImage(file.getOriginalFilename());
                food.setCategory(categoryService.findByName(category_name));

                foodRepository.save(food);
                isInsertSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return isInsertSuccess;
    }

    @Override
    public boolean updateFood(int id, MultipartFile file, String title, String timeShip, double price, String category_name) {
        boolean isUpdateSuccess = false;
        try {
            boolean isSuccess = fileService.saveFile(file, "food");
            if(isSuccess){
                Food food = foodRepository.findFoodById(id);
                food.setTitle(title);
                food.setTimeShip(timeShip);
                food.setPrice(price);
                food.setImage(file.getOriginalFilename());
                food.setCategory(categoryService.findByName(category_name));

                foodRepository.save(food);
                isUpdateSuccess = true;
            }
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return isUpdateSuccess;
    }

    @Override
    public boolean deleteFood(int id) {
        boolean isDeleteSuccess = false;
        try {
            foodRepository.deleteById(id);
            isDeleteSuccess = true;
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return isDeleteSuccess;
    }

    @Override
    public List<FoodDTO> getHomePageFood() {;
        List<Food> foodList = foodRepository.findTop3ByOrderById();
        List<FoodDTO> foodDTOS = new ArrayList<>();
        for(Food food : foodList){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(food.getId());
            foodDTO.setCategory(food.getCategory());
            foodDTO.setTitle(food.getTitle());
            foodDTO.setPrice(food.getPrice());

            foodDTOS.add(foodDTO);
        }
        return foodDTOS;
    }

}
