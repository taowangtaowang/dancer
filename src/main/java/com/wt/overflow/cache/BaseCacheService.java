package com.wt.overflow.cache;

import com.wt.overflow.bean.SysRole;

public interface BaseCacheService {
    /**
     * 根据角色id查询角色相关信息
      * @param roleId
     * @return
     */
    SysRole getRoleByRoleId(String roleId);

}
