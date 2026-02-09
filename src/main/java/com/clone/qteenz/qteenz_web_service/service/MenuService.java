package com.clone.qteenz.qteenz_web_service.service;

import com.clone.qteenz.qteenz_web_service.dao.MenuDao;
import com.clone.qteenz.qteenz_web_service.dto.menu.MenuRequestDTO;
import com.clone.qteenz.qteenz_web_service.dto.menu.MenuResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public MenuResponseDTO create(MenuRequestDTO requestDTO){
        return menuDao.insert(requestDTO);
    }

    public List<MenuResponseDTO> getAll(){
        return menuDao.getALl();
    }

    public MenuResponseDTO findById(long id){
        return menuDao.findById(id);
    }

    public MenuResponseDTO updateById(long id, MenuRequestDTO requestDTO){
        return menuDao.updateById(id, requestDTO);
    }

    public MenuResponseDTO deleteById(long id) {
        return menuDao.deleteById(id);
    }


}
