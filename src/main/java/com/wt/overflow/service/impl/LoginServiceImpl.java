package com.wt.overflow.service.impl;

import com.wt.overflow.bean.*;
import com.wt.overflow.dao.*;
import com.wt.overflow.service.LoginService;
import com.wt.overflow.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 登录初始化相关
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysOrganizeMapper sysOrganizeMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysModuleMapper sysModuleMapper;
	@Autowired
	private SysModuleButtonMapper sysModuleButtonMapper;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public List<SysUser> queryByLoginName(String loginname) {
		logger.info("日志测试service包");
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("account",loginname);
		/*Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("loginname", loginname);
		return sysUserMapper.queryByLoginName(parameter);*/
		return sysUserMapper.selectByExample(example);
	}

	
	/**
	 * 登录初始化数据
	 */
	@Override
	public Map<String, Object> queryInitData(SysUser sysUser) {
		// dataItems 系统选项信息
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("sysItem", null);
		res.put("dataItems", null);

		Example sysOrganizeExample = new Example(SysUser.class);
		Example.Criteria sysOrganizeCriteria = sysOrganizeExample.createCriteria();
		List<SysOrganize> sysOrganizelist = sysOrganizeMapper.selectByExample(sysOrganizeExample);
		res.put("organize", sysOrganizelist);

		// role 角色信息
		Example sysRoleExample = new Example(SysRole.class);
		Example.Criteria sysRoleCriteria = sysRoleExample.createCriteria();
		//sysRoleCriteria.andEqualTo("category",1);
		sysRoleCriteria.andNotEqualTo("enabledmark",1);

		List<SysRole> sysRolelist = sysRoleMapper.selectByExample(sysRoleExample);
		Map<String, Object> sysRoleMaplist = new HashMap<String, Object>();
		for (SysRole sysRole : sysRolelist) {
			Map<Object,Object> sysRoleMap = new  HashMap<Object,Object>();
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			detailmap.put("encode", sysRole.getEncode());
			detailmap.put("fullname", sysRole.getFullname());
			sysRoleMap.put(sysRole.getId(), detailmap);
			sysRoleMaplist.put(sysRole.getId(), detailmap);
		}
		res.put("role", sysRoleMaplist);
		// duty 岗位信息
		Example sysRoleExample2 = new Example(SysUser.class);
		Example.Criteria sysRoleCriteria2 = sysRoleExample2.createCriteria();
		sysRoleCriteria2.andEqualTo("category",2);
		List<SysRole> sysdutylist = sysRoleMapper.selectByExample(sysRoleExample2);
		Map<String, Object> sysDutyMaplist = new HashMap<String, Object>();
		for (SysRole sysRole : sysdutylist) {
			Map<Object,Object> sysRoleMap = new  HashMap<Object,Object>();
			Map<Object,Object> detailmap = new  HashMap<Object,Object>();
			
			detailmap.put("encode", sysRole.getEncode());
			detailmap.put("fullname", sysRole.getFullname());
			sysRoleMap.put(sysRole.getId(), detailmap);
			sysDutyMaplist.put(sysRole.getId(), detailmap);
		}
		res.put("duty", sysDutyMaplist);

		// user 登录用户信息
		res.put("user", sysUser);
		// authorizeMenu 拥有权限菜单
		boolean isAdmin = false;
		for (SysRole role : sysRolelist) {
			if (role.getId().equals((sysUser.getRoleid() == null ? "" : sysUser.getRoleid()))) {
				isAdmin = true;
			}
		}
		String userRoleId = sysUser.getRoleid() == null ? "" : sysUser.getRoleid();
		String isAdminStr =( isAdmin?"0":"1");//1 代表admin用户
		HashMap<String,Object> parameter = new HashMap<String,Object>();
		parameter.put("isAdmin", isAdminStr);
		parameter.put("userRoleId", userRoleId);
		parameter.put("enabledMark", 1);//该菜单是否有效
		//如果是admin 查询所有数据  如何是别的角色  查询  拥有的权限
		List<SysModule> authorizeMenulist  = new ArrayList<SysModule>();//拥有的所有权限
		List<Map<String, Object>> menulist= new ArrayList<Map<String, Object>>();//最后组装好的菜单res
		authorizeMenulist = sysModuleMapper.queryAllSysModuleByIsAdmin(parameter);//菜单权限

		for (SysModule sysModule : authorizeMenulist) {
			if(sysModule.getParentid().equals("0")){//1级菜单
				Map<String, Object> levermap1 = new HashMap<String, Object>();
				List<Map<String, Object>> levermap2list= new ArrayList<Map<String, Object>>();
					levermap1 = ObjectUtil.convertBean(sysModule);//1级菜单map
					for (SysModule sysModule2 : authorizeMenulist) {//admin权限拥有的所有菜单 12 级
						if(sysModule2.getParentid().equals(sysModule.getId())){//子父关系
							levermap2list.add(ObjectUtil.convertBean(sysModule2));
						}
					}
				levermap1.put("ChildNodes",levermap2list);//二级菜单
				menulist.add(levermap1);
			}
		}
		res.put("authorizeMenu", menulist);
		// authorizeButton 拥有权限按钮
		
		List<SysModulebutton> authorizeButtonlist = sysModuleButtonMapper.queryAllAuthorizeButtonform(parameter);//按钮权限
		Map<String, Object> lowMenuMap = new HashMap<String, Object>();
		for (SysModule sysModule : authorizeMenulist) {//拥有的菜单权限
			if(!sysModule.getParentid().equals("0")){//最低等级菜单
				List<Map<String, Object>> buttonlist= new ArrayList<Map<String, Object>>();
				for (SysModulebutton sysModuleButton : authorizeButtonlist) {
					if(sysModuleButton.getModuleid()!=null&&sysModule.getId()!=null){
						if(sysModuleButton.getModuleid() .equals(sysModule.getId())){
							buttonlist.add(ObjectUtil.convertBean(sysModuleButton));
						}
					}
				}
				lowMenuMap.put(sysModule.getId(), buttonlist);
			}
		}
		
		res.put("authorizeButton", lowMenuMap);
		res.put("state", "success");
		res.put("message", "登录成功");
		return res;
	}

}
