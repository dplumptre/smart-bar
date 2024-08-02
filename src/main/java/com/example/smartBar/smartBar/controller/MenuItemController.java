package com.example.smartBar.smartBar.controller;


import com.example.smartBar.smartBar.domain.MenuItemEntity;
import com.example.smartBar.smartBar.dto.MenuItemDto;
import com.example.smartBar.smartBar.response.ApiResponse;
import com.example.smartBar.smartBar.services.impl.MenuItemServiceImp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(
        name="CRUD - MENU ITEM REST API",
        description = "CRUD for the menu Items"
)


@RestController
@RequestMapping("/api/menu-items")
public class MenuItemController {


    private final MenuItemServiceImp menuItemServiceImp;

    public MenuItemController(MenuItemServiceImp menuItemServiceImp) {
        this.menuItemServiceImp = menuItemServiceImp;
    }

    @Operation(
            summary = "Create - REST API",
            description = "Create a menu item and save into the db"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "HTTP STATUS 201 CREATED"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<MenuItemDto>> createMenuItem(@RequestBody MenuItemDto menuItem){
            MenuItemDto menuItems = menuItemServiceImp.createMenuItem(menuItem);
            return new ResponseEntity<>(ApiResponse.create("menu created successfully", menuItems), HttpStatus.CREATED);
    }

    @Operation(
            summary = "READ ALL - MENU ITEM List",
            description = "Get all menu items"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )

//    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<MenuItemDto>>>getMenuItem(){
             List<MenuItemDto>  allMenuList = menuItemServiceImp.getMenuItems();
             return new ResponseEntity<>(ApiResponse.create("list of menus",allMenuList), HttpStatus.ACCEPTED);

    }


    @Operation(
            summary = "READ - REST API",
            description = "Get single menu Item REST API"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )

//    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemDto>>getSingleMenuItem(@PathVariable("id") Long id){
        MenuItemDto singleMenuList = menuItemServiceImp.getSingleMenuItem(id);
        return new ResponseEntity<>(ApiResponse.create("one menu item",singleMenuList), HttpStatus.ACCEPTED);
    }


    @Operation(
            summary = "Update REST API",
            description = "Update a single menu item and  save into the db"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuItemDto>>updateMenuItem(@PathVariable("id") Long id,@RequestBody MenuItemDto menuItem){
        MenuItemDto updatedMenuItem = menuItemServiceImp.updateMenuItem(id, menuItem);
        return new ResponseEntity<>(ApiResponse.create("menu Item Updated Successfully",updatedMenuItem), HttpStatus.ACCEPTED);

    }

    @Operation(
            summary = "Delete REST API",
            description = "Delete a single menu item"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "200",
            description = "HTTP STATUS 200 OK"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteMenuItem(@PathVariable("id") Long id){
        String response = menuItemServiceImp.deleteMenuItem(id);
        return new ResponseEntity<>(ApiResponse.create(response,""),HttpStatus.OK);
    }



}
