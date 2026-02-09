package com.clone.qteenz.qteenz_web_service.controller;

import com.clone.qteenz.qteenz_web_service.dto.ApiResponse;
import com.clone.qteenz.qteenz_web_service.dto.menu.MenuRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.menu.MenuResponseDTO;
import com.clone.qteenz.qteenz_web_service.model.Menu;
import com.clone.qteenz.qteenz_web_service.service.MenuService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")

public class MenuController {
    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<MenuResponseDTO>> create (@RequestBody @Valid MenuRequestDTO requestDTO){
        MenuResponseDTO created = menuService.create(requestDTO);
        ApiResponse<MenuResponseDTO> response = new ApiResponse<>(
                "success",
                201,
                "Menu Created Successfuly",
                created
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<MenuResponseDTO>>> getAll(){
        List<MenuResponseDTO> menus = menuService.getAll();
        ApiResponse<List<MenuResponseDTO>> response = new ApiResponse<>(
                "success",
                200,
                "Menu retrieved suxxesfuly",
                menus
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> findById(@PathVariable long id){
        MenuResponseDTO menu = menuService.findById(id);
        ApiResponse<MenuResponseDTO> response = new ApiResponse<>(
                "success",
                200,
                "Menu with id: " + id + "retrieved succesfuly",
                menu
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> updateById(@PathVariable long id, @RequestBody @Valid MenuRequestDTO requestDTO){
        MenuResponseDTO menu = menuService.updateById(id, requestDTO);
        ApiResponse<MenuResponseDTO> response = new ApiResponse<>(
                "success",
                200,
                "Menu with id: " + id + "updated succesfuly",
                menu
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<MenuResponseDTO>> deleteById(long id){
        MenuResponseDTO menu = menuService.deleteById(id);
        ApiResponse<MenuResponseDTO> response= new ApiResponse<>(
                "success",
                200,
                "Menu with id: " + id + "deleted succesfuly",
                menu
        );

        return  ResponseEntity.ok(response);
    }
}
