package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwPermission;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GwPermissionService {

    int insert(GwPermission record);

    int insertSelective(GwPermission record);

    List<String> getPermissionByUserId(Long userId);

}
