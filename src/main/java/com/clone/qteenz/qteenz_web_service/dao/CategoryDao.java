package com.clone.qteenz.qteenz_web_service.dao;

import com.clone.qteenz.qteenz_web_service.dto.category.CategoryRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.category.CategoryResponseDTO;
import com.clone.qteenz.qteenz_web_service.model.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;

    public CategoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static String INSERT_QUERY =
            """
                insert into CATEGORY (name) values ( ? );        
            """;

    private static String DELETE_QUERY =
            """
                DELETE FROM CATEGORY WHERE id = ?    
            """;

    private static String SELECT_BY_ID_QUERY =
            """
                SELECT * FROM CATEGORY WHERE id = ?    
            """;

    private static  String SELECT_ALL_QUERY =
            """
                SELECT * FROM CATEGORY    
            """;

    private static String UPDATE_BY_ID =
            """
                UPDATE CATEGORY 
                SET name = ?
                WHERE id = ?        
                    
            """;
    public CategoryResponseDTO insert(CategoryRequestDTO requestDTO){
//        jdbcTemplate.update(INSERT_QUERY, category.getName());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, requestDTO.getName());
            return  ps;
        }, keyHolder);

        return new CategoryResponseDTO(keyHolder.getKey().longValue(), requestDTO.getName());
    }

    public void deleteById(long id){
        jdbcTemplate.update(DELETE_QUERY, id);
    }

    public CategoryResponseDTO findById(long id){
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new BeanPropertyRowMapper<>(CategoryResponseDTO.class), id);
    }

    public List<CategoryResponseDTO> getAll(){
        return  jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(CategoryResponseDTO.class));
    }


    public CategoryResponseDTO updateById(long id, CategoryRequestDTO requestDTO){
        int data = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    UPDATE_BY_ID
            );
            ps.setString(1, requestDTO.getName());
            ps.setLong(2, id);
            return ps;
        });

        if (data == 0) {
            throw new RuntimeException("Category not found");
        }
        return new CategoryResponseDTO(id, requestDTO.getName());

    }



}
