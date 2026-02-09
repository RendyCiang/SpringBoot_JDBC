package com.clone.qteenz.qteenz_web_service.repository.Category;

import com.clone.qteenz.qteenz_web_service.model.Category;
import com.clone.qteenz.qteenz_web_service.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
