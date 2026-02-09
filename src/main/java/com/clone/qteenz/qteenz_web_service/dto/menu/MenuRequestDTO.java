package com.clone.qteenz.qteenz_web_service.dto.menu;

import com.clone.qteenz.qteenz_web_service.model.Category;

import java.math.BigDecimal;
import java.util.List;

public class MenuRequestDTO {
    private String name;
    private String description;
    private BigDecimal price;

    public MenuRequestDTO(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public MenuRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
