CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) DEFAULT 0.00
);

-- Junction table Menu * - * Category
CREATE TABLE menu_categories (
    menu_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (menu_id, category_id),
    CONSTRAINT fk_menu
        FOREIGN KEY (menu_id)
        REFERENCES menu(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_category
        FOREIGN KEY (category_id)
        REFERENCES category(id)
        ON DELETE CASCADE
);
