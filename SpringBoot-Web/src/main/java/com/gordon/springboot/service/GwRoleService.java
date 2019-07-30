package com.gordon.springboot.service;

import java.util.List;

/**
 * Created by gordon on 2019/7/16.
 */
public interface GwRoleService {

    List<String> getRolesByUserId(Long userId);

}
