package com.gordon.springboot.service.impl;

import com.gordon.springboot.mapper.GwRoleMapper;
import com.gordon.springboot.service.GwRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gordon on 2019/7/16.
 */
@Service
public class GwRoleServiceImpl implements GwRoleService {

    @Autowired
    private GwRoleMapper gwRoleMapper;

    @Override
    public List<String> getRolesByUserId(Long userId) {
        return gwRoleMapper.getRolesByUserId(userId);
    }
}
