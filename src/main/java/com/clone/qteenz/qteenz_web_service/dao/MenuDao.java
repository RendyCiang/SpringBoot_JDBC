package com.clone.qteenz.qteenz_web_service.dao;

import com.clone.qteenz.qteenz_web_service.dto.category.CategoryRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.category.CategoryResponseDTO;
import com.clone.qteenz.qteenz_web_service.dto.menu.MenuRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.menu.MenuResponseDTO;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class MenuDao {
    private JdbcTemplate jdbcTemplate;

    public MenuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static String INSERT_QUERY =
            """
                INSERT INTO Menu (name, description, price) VALUES (?, ?, ?)        
            """;

    private static String DELETE_QUERY =
            """
                DELETE FROM Menu WHERE id = ?    
            """;

    private static String SELECT_BY_ID_QUERY =
            """
                SELECT * FROM Menu WHERE id = ?    
            """;

    private static  String SELECT_ALL_QUERY =
            """
                SELECT * FROM Menu    
            """;

    private static String UPDATE_BY_ID =
            """
                UPDATE CATEGORY 
                SET name = ?
                description = ?
                price = ?
                WHERE id = ?        
            """;
    private static String SELECT_DATA_JOINED =
            """
                SELECT m.id, m.name, m.description, m.price, c.id AS category_id, c.name AS category_name
                FROM Menu m
                LEFT JOIN menu_categories mc ON m.id = mc.menu_id
                LEFT JOIN categories c ON mc.category_id = c.id
                ORDER BY m.id    
            """;
    public MenuResponseDTO insert(MenuRequestDTO requestDTO){
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, requestDTO.getName());
            ps.setString(2, requestDTO.getDescription());
            ps.setBigDecimal(3, requestDTO.getPrice());
            return ps;
        }, keyHolder);

        return new MenuResponseDTO(keyHolder.getKey().longValue(), requestDTO.getName(),requestDTO.getDescription(), requestDTO.getPrice());
    }

    public List<MenuResponseDTO> getALl(){
        return jdbcTemplate.query(SELECT_ALL_QUERY, new BeanPropertyRowMapper<>(MenuResponseDTO.class));

    }


    public MenuResponseDTO findById(long id){
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new BeanPropertyRowMapper<>(MenuResponseDTO.class), id);
    }

    public MenuResponseDTO updateById(long id, MenuRequestDTO requestDTO) {
//        int data = jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(
//                    UPDATE_BY_ID
//            );
//            ps.setString(1, requestDTO.getName());
//            ps.setString(2, requestDTO.getDescription());
//            ps.setBigDecimal(3, requestDTO.getPrice());
//            ps.setLong(4, id);
//            return ps;
//        });
//
//        if (data == 0) {
//            throw new RuntimeException("Category not found");
//        }

        BeanPropertySqlParameterSource beanParams = new BeanPropertySqlParameterSource(requestDTO);

        MapSqlParameterSource params = new MapSqlParameterSource();

        // copy semua field dari DTO ke Map
        for (String paramName : beanParams.getParameterNames()) {
            params.addValue(paramName, beanParams.getValue(paramName));
        }

        // tambah id dari path
        params.addValue("id", id);

        int rows = jdbcTemplate.update(UPDATE_BY_ID, params);
        if (rows == 0) {
            throw new RuntimeException("Menu not found");
        }

        MenuResponseDTO response = new MenuResponseDTO();
        BeanUtils.copyProperties(requestDTO, response);
        response.setId(id);

//        return findById(id);
        return response;
    }



    public MenuResponseDTO deleteById(long id) {
        int rows = jdbcTemplate.update(DELETE_QUERY, id);

        if (rows == 0) {
            throw new RuntimeException("Menu not found");
        }

        MenuResponseDTO response = new MenuResponseDTO();
        response.setId(id);
        return response;
    }









}
