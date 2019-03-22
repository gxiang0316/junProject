package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwMenu;
import com.gordon.springboot.mapper.GwMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 2019/2/28.
 */
@Service
public class GwMenuServiceImpl implements GwMenuService {

    @Autowired
    private GwMenuMapper menuMapper;

    @Override
    public int insertSelective(GwMenu record) {
        return 0;
    }

    @Override
    public List<GwMenu> getUserRoleMenuList(String username) {
        List<GwMenu> menuList = menuMapper.getUserRoleMenuList(username);
        if(menuList == null || menuList.size() == 0){
            return new ArrayList<>();
        }

        List<GwMenu> gwMenuList = new ArrayList<>();
        for(int i = 0 ; i < menuList.size() ; i++){
            GwMenu gwMenu = menuList.get(i);
            List<GwMenu> childrenList = gwMenu.getChildrenList();
            for(int j = 0 ; j < menuList.size() ; j++){
                GwMenu gwMenu1 = menuList.get(j);
                if(gwMenu.getMenuId().equals(gwMenu1.getParentId())){
                    childrenList.add(gwMenu1);
                }
            }
            if(childrenList.size() > 0) {
                gwMenuList.add(gwMenu);
            }
        }

        return gwMenuList;
    }
}
