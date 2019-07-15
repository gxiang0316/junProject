package com.gordon.springboot.service;

import com.gordon.springboot.entity.GwPermission;
import java.util.List;

public interface GwPermissionService {

    int insert(GwPermission record);

    int insertSelective(GwPermission record);

    List<String> getPermissionByUserId(Long userId);

}
