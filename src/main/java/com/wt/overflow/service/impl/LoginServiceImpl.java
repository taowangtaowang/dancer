package com.wt.overflow.service.impl;

import com.wt.overflow.bean.*;
import com.wt.overflow.dao.*;
import com.wt.overflow.service.LoginService;
import com.wt.overflow.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 登录初始化相关
 * @author wangt
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysItemsMapper sysItemsMapper;
	@Autowired
	private SysItemsDetailMapper sysItemsDetailMapper;
	@Autowired
	private SysOrganizeMapper sysOrganizeMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	
	@Autowired
	private SysModuleMapper sysModuleMapper;
	@Autowired
	private SysModuleButtonMapper sysModuleButtonMapper;
	@Override
	public List<SysUser> queryByLoginName(String loginname) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("loginname", loginname);
		return sysUserMapper.queryByLoginName(parameter);
	}

	
	/**
	 * 登录初始化数据
	 */
	@Override
	public Map<String, Object> queryInitData(SysUser sysUser) {
		// dataItems 系统选项信息
		Map<String, Object> res = new HashMap<String, Object>();
		List<SysItems> sysItemlist = sysItemsMapper.queryAllData();
		res.put("sysItem", sysItemlist);
		List<SysItemsdetail> sysItemsDetaillist = sysItemsDetailMapper.queryAllData();
		
		Map<String, Object> sysItemMaplist = new HashMap<String, Object>();
		//List<Map<Object,Object>> sysItemMaplist = new ArrayList<Map<Object,Object>>();
		for (SysItems sysItems : sysItemlist) {
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			for (SysItemsdetail sysItemsdetail : sysItemsDetaillist) {
				if(sysItems.getfId().equals(sysItemsdetail.getfItemid())){//77070117-3F1A-41BA-BF81-B8B85BF10D5E
					detailmap.put(sysItemsdetail.getfItemcode(),sysItemsdetail.getfItemname());
				}
			}
			
			Map<Object,Object> map = new  HashMap<Object,Object>();
			map.put(sysItems.getfEncode(), detailmap);
			//sysItemMaplist.add(map);
			sysItemMaplist.put(sysItems.getfEncode(), detailmap);
		}
		res.put("dataItems", sysItemMaplist);
		// organize 组织信息
		/*Map<String,Object> map = new HashMap<String,Object>();
		map.put("nodeid","0");*/
		List<SysOrganize> sysOrganizelist = sysOrganizeMapper.queryAllData(new HashMap<String,Object>());
		//List<Map<Object,Object>> sysOrganMaplist = new ArrayList<Map<Object,Object>>();
		Map<String, Object> sysOrganMaplist = new HashMap<String, Object>();
		for (SysOrganize sysOrganize : sysOrganizelist) {
			Map<Object,Object> sysOrganMap = new  HashMap<Object,Object>();
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			detailmap.put("encode", sysOrganize.getfEncode());
			detailmap.put("fullname", sysOrganize.getfFullname());
			sysOrganMap.put(sysOrganize.getfId(), detailmap);
			sysOrganMaplist.put(sysOrganize.getfId(), detailmap);
		}
		
		res.put("organize", sysOrganMaplist);
		// role 角色信息 如果是
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("F_Category", String.valueOf(1));
		List<SysRole> sysRolelist = sysRoleMapper.queryAllDataByCategory(parameter);// F_Category=1
		//List<Map<Object,Object>> sysRoleMaplist = new ArrayList<Map<Object,Object>>();
		Map<String, Object> sysRoleMaplist = new HashMap<String, Object>();
		for (SysRole sysRole : sysRolelist) {
			Map<Object,Object> sysRoleMap = new  HashMap<Object,Object>();
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			
			detailmap.put("encode", sysRole.getfEncode());
			detailmap.put("fullname", sysRole.getfFullname());
			sysRoleMap.put(sysRole.getfId(), detailmap);
			sysRoleMaplist.put(sysRole.getfId(), detailmap);
		}
		res.put("role", sysRoleMaplist);
		// duty 岗位信息
		Map<String, Object> sysDutyMaplist = new HashMap<String, Object>();
		parameter.put("F_Category", String.valueOf(2));
		List<SysRole> sysdutylist = sysRoleMapper.queryAllDataByCategory(parameter);// F_Category=2
		for (SysRole sysRole : sysdutylist) {
			Map<Object,Object> sysRoleMap = new  HashMap<Object,Object>();
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			
			detailmap.put("encode", sysRole.getfEncode());
			detailmap.put("fullname", sysRole.getfFullname());
			sysRoleMap.put(sysRole.getfId(), detailmap);
			sysDutyMaplist.put(sysRole.getfId(), detailmap);
		}
		res.put("duty", sysDutyMaplist);
		// user 登录用户信息
		res.put("user", sysUser);
		// authorizeMenu 拥有权限菜单
		boolean isAdmin = false;
		for (SysRole role : sysRolelist) {
			if (role.getfId().equals((sysUser.getfRoleid() == null ? "" : sysUser.getfRoleid()))) {
				isAdmin = true;
			}
		}
		String userRoleId = sysUser.getfRoleid() == null ? "" : sysUser.getfRoleid();
		String isAdminStr =( isAdmin?"0":"1");//1 代表admin用户
		parameter.put("isAdmin", isAdminStr);
		parameter.put("userRoleId", userRoleId);
		parameter.put("F_EnabledMark", 1);//该菜单是否有效
		//如果是admin 查询所有数据  如何是别的角色  查询  拥有的权限
		List<SysModule> authorizeMenulist  = new ArrayList<SysModule>();//拥有的所有权限
		List<Map<String, Object>> menulist= new ArrayList<Map<String, Object>>();//最后组装好的菜单res
		authorizeMenulist = sysModuleMapper.queryAllSysModuleByIsAdmin(parameter);//菜单权限

		for (SysModule sysModule : authorizeMenulist) {
			if(sysModule.getfParentid().equals("0")){//1级菜单
				Map<String, Object> levermap1 = new HashMap<String, Object>();
				List<Map<String, Object>> levermap2list= new ArrayList<Map<String, Object>>();
					levermap1 = ObjectUtil.convertBean(sysModule);//1级菜单map
					for (SysModule sysModule2 : authorizeMenulist) {//admin权限拥有的所有菜单 12 级
						if(sysModule2.getfParentid().equals(sysModule.getfId())){//子父关系
							levermap2list.add(ObjectUtil.convertBean(sysModule2));
						}
					}
				levermap1.put("ChildNodes",levermap2list);//二级菜单
				menulist.add(levermap1);
			}
		}
		res.put("authorizeMenu", menulist);
		// authorizeButton 拥有权限按钮
		
		List<SysModuleButton> authorizeButtonlist = sysModuleButtonMapper.queryAllAuthorizeButtonform(parameter);//按钮权限
		Map<String, Object> lowMenuMap = new HashMap<String, Object>();
		for (SysModule sysModule : authorizeMenulist) {//拥有的菜单权限
			if(!sysModule.getfParentid().equals("0")){//最低等级菜单
				List<Map<String, Object>> buttonlist= new ArrayList<Map<String, Object>>();
				for (SysModuleButton sysModuleButton : authorizeButtonlist) {
					if(sysModuleButton.getfModuleid()!=null&&sysModule.getfId()!=null){
						if(sysModuleButton.getfModuleid() .equals(sysModule.getfId())){
							buttonlist.add(ObjectUtil.convertBean(sysModuleButton));
						}
					}
				}
				lowMenuMap.put(sysModule.getfId(), buttonlist);
			}
		}
		
		res.put("authorizeButton", lowMenuMap);
		res.put("state", "success");
		res.put("message", "登录成功");
		return res;
	}

}
