package com.gordon.springboot.service.impl;

import com.gordon.springboot.entity.GwMenu;
import com.gordon.springboot.mapper.GwMenuMapper;
import com.gordon.springboot.service.GwMenuService;
import com.gordon.springboot.utils.JsonUtils;
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
//        System.out.println("  menuList : " + JsonUtils.listToJson(menuList));


//        menuList = convertToTree(0,menuList);

        return menuList;
    }

    /**
     * 递归拼成树形结构
     * @param rootId
     * @param menuList
     * @return
     */
    private List<GwMenu> convertToTree(int rootId,List<GwMenu> menuList) {
        List<GwMenu> parentList = new ArrayList<>();
        for(int i = 0 ; i < menuList.size() ; i++){
            GwMenu gwMenu = menuList.get(i);
            Integer parentId = gwMenu.getParentId();
            if(parentId == rootId){ // 获取一级菜单
                parentList.add(gwMenu);
                convertChildren(gwMenu,menuList);
            }
        }

        return parentList;

    }

    // 获取当前节点下所有子节点
    private void convertChildren(GwMenu gwMenu,List<GwMenu> menuList) {
        Integer menuId = gwMenu.getMenuId();
        for(int i = 0 ; i < menuList.size() ; i++){
            GwMenu child = menuList.get(i);
            Integer parentId = child.getParentId();
            if(menuId.equals(parentId)){// 不能用 == 比较， == 比较的是地址 有时为true 有时为false
                List<GwMenu> childrenList = gwMenu.getChildrenList();
                childrenList.add(child);
                convertChildren(child,menuList);
            }
        }
    }


}
