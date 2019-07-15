package com.gordon.springboot.service.impl;

import com.gordon.springboot.contants.ErrorContants;
import com.gordon.springboot.entity.GwPermission;
import com.gordon.springboot.exception.GwException;
import com.gordon.springboot.mapper.GwPermissionMapper;
import com.gordon.springboot.service.GwPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GwPermissionServiceImpl implements GwPermissionService {

    @Autowired
    private GwPermissionMapper gwPermissionMapper;

    @Override
    public int insert(GwPermission record) {
        return 0;
    }

    @Override
    public int insertSelective(GwPermission record) {
        return 0;
    }

    @Override
    public List<String> getPermissionByUserId(Long userId) {
        if(userId <= 0){
            throw new GwException(ErrorContants.ERROR_9007,new String[]{"userId"});
        }

        return gwPermissionMapper.getPermissionByUserId(userId);
    }


}
