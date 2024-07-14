package com.example.smartBar.smartBar.services.impl;

import com.example.smartBar.smartBar.domain.MenuItemEntity;
import com.example.smartBar.smartBar.dto.MenuItemDto;
import com.example.smartBar.smartBar.exception.ResourceNotFoundException;
import com.example.smartBar.smartBar.repository.MenuItemRepository;
import com.example.smartBar.smartBar.services.MenuItemService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuItemServiceImp implements MenuItemService {


    private MenuItemRepository menuItemRepository;
    private ModelMapper modelMapper;

    @Override
    public  MenuItemDto createMenuItem(MenuItemDto menuItemDto) {
         MenuItemEntity menuItemEntity = modelMapper.map(menuItemDto,MenuItemEntity.class);
         return modelMapper.map(menuItemRepository.save(menuItemEntity), MenuItemDto.class);
    }

    @Override
    public List<MenuItemDto> getMenuItems() {
        return menuItemRepository.findAll().stream().map( menuItem ->  modelMapper.map(menuItem,MenuItemDto.class)).collect(Collectors.toList());
    }

    @Override
    public MenuItemDto getSingleMenuItem(Long id) {
            MenuItemEntity menuItem =  menuItemRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("menuItem not found!"));
         return modelMapper.map(menuItem,MenuItemDto.class);
    }

    @Override
    public MenuItemDto updateMenuItem(Long id, MenuItemDto menuItem) {
        MenuItemEntity menuUpdateItem =   menuItemRepository.findById(id).orElseThrow( ()-> new ResourceNotFoundException("menuItem not found!"));
        menuUpdateItem.setName(menuItem.getName());
        menuUpdateItem.setPrice(menuItem.getPrice());
        menuUpdateItem.setImage(menuItem.getImage());
        return modelMapper.map(menuItemRepository.save(menuUpdateItem),MenuItemDto.class);
    }

    @Override
    public String deleteMenuItem(Long id) {
         MenuItemEntity menuItem = menuItemRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("MenuItem not found!"));
         menuItemRepository.delete(menuItem);
         return "Deleted Successfully";
    }




}

