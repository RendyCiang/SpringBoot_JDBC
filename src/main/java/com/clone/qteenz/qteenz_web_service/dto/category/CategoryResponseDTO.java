package com.clone.qteenz.qteenz_web_service.dto.category;

public class CategoryResponseDTO {
    private Long id;
    private String name;

    public CategoryResponseDTO() {
    }

    public CategoryResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
