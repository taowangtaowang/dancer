package com.wt.overflow.service.impl;

import com.wt.overflow.bean.Account;
import com.wt.overflow.bean.Menu;
import com.wt.overflow.dao.MenuMapper;
import com.wt.overflow.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询当前登录用户拥有的菜单权限
     * @param account
     * @return
     */
    @Override
    public List<Map<String, Object>> selectShowMenus(Account account) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        //获取所有菜单
        Example menuExample = new Example(Menu.class);
        //Example.Criteria menuCriteria = menuExample.createCriteria();
        List<Menu> menuList = menuMapper.selectByExample(menuExample);
        List<Menu> rootMenus = getRootMenus(menuList);

        for (Menu menu : rootMenus) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", menu.getId());
            map.put("text", menu.getName());
            map.put("order", menu.getOrderby());
            //菜单额外属性
            Map<String, Object> attrMap = new HashMap<String, Object>();
            attrMap.put("url", menu.getUrl());
            map.put("attributes", attrMap);
            map.put("url", menu.getUrl());
            map.put("icon", menu.getIcon());
            List<Map<String, Object>> childrenMenus = getChildrenMenus(menuList, menu.getId());
            map.put("children", childrenMenus);
            maps.add(map);
        }
        return maps;
    }

    /**
     * 获取根菜单
     *
     * @param menus
     * @return
     */
    private List<Menu> getRootMenus(List<Menu> menus) {
        List<Menu> rootMenus = new ArrayList<Menu>();
        for (Menu menu : menus) {
            if (menu.getParentId() == null) {
                rootMenus.add(menu);
                continue;
            }
            Menu parentMenu = get(menus, menu.getParentId());
            if (parentMenu == null) {
                rootMenus.add(menu);
            }
        }
        return rootMenus;
    }

    /**
     * 获取菜单
     * @param menus
     * @param id
     * @return
     */
    public Menu get(List<Menu> menus, String id) {
        for(Menu menu:menus){
            if(menu.getId().equals(id)){
                return menu;
            }
        }
        return null;
    }

    /**
     * 获取子级菜单
     *
     * @param menus
     * @param parentId
     * @return
     */
    private List<Map<String, Object>> getChildrenMenus(List<Menu> menus, String parentId) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Menu menu : menus) {
            if ((parentId == null && menu.getParentId() == null) || (parentId != null && menu.getParentId() != null && menu.getParentId().equals(parentId))) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", menu.getId());
                map.put("text", menu.getName());
                //菜单额外属性
                Map<String, Object> attrMap = new HashMap<String, Object>();
                attrMap.put("url", menu.getUrl());
                map.put("attributes", attrMap);
                map.put("url", menu.getUrl());
                map.put("order", menu.getOrderby());
                map.put("icon", menu.getIcon());
                List<Map<String, Object>> childrenMenus = getChildrenMenus(menus, menu.getId());
                map.put("children", childrenMenus);
                mapList.add(map);
            }
        }
        return mapList;
    }
}
