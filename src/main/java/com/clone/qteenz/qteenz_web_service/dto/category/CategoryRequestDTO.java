package com.clone.qteenz.qteenz_web_service.dto.category;

import jakarta.validation.constraints.NotBlank;

public class CategoryRequestDTO {

    @NotBlank(message = "{category.name.notblank}")
    private String name;

    public CategoryRequestDTO(String name) {
        this.name = name;
    }

    public CategoryRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
