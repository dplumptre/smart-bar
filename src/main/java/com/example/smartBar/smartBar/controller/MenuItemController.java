package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.domain.MenuItemEntity;
import com.example.smartBar.smartBar.dto.MenuItemDto;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.impl.MenuItemServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {


    private final MenuItemServiceImp menuItemServiceImp;

    public MenuItemController(MenuItemServiceImp menuItemServiceImp) {
        this.menuItemServiceImp = menuItemServiceImp;
    }


    @PostMapping
    public ResponseEntity<ApiResponse<MenuItemDto>> createMenuItem(@RequestBody MenuItemDto menuItem){
            MenuItemDto menuItems = menuItemServiceImp.createMenuItem(menuItem);
            return new ResponseEntity<>(ApiResponse.create("menu created successfully", menuItems), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<MenuItemDto>>>getMenuItem(){
             List<MenuItemDto>  allMenuList = menuItemServiceImp.getMenuItems();
             return new ResponseEntity<>(ApiResponse.create("list of menus",allMenuList), HttpStatus.ACCEPTED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemDto>>getSingleMenuItem(@PathVariable("id") Long id){
        MenuItemDto singleMenuList = menuItemServiceImp.getSingleMenuItem(id);
        return new ResponseEntity<>(ApiResponse.create("one menu item",singleMenuList), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemDto>>updateMenuItem(@PathVariable("id") Long id,@RequestBody MenuItemDto menuItem){
        MenuItemDto updatedMenuItem = menuItemServiceImp.updateMenuItem(id, menuItem);
        return new ResponseEntity<>(ApiResponse.create("menu Item Updated Successfully",updatedMenuItem), HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMenuItem(@PathVariable("id") Long id){
        String response = menuItemServiceImp.deleteMenuItem(id);
        return new ResponseEntity<>(ApiResponse.create(response,""),HttpStatus.OK);
    }



}
