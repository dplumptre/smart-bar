package com.example.smartBar.smartBar.services;

import com.example.smartBar.smartBar.domain.MenuItemEntity;
import com.example.smartBar.smartBar.dto.MenuItemDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MenuItemService {
    public MenuItemDto createMenuItem(MenuItemDto menuItemDto);

    public List<MenuItemDto> getMenuItems();

    public  MenuItemDto getSingleMenuItem(Long id);

    public MenuItemDto updateMenuItem(Long id, MenuItemDto menuItem);

    public String deleteMenuItem(Long id);
}
