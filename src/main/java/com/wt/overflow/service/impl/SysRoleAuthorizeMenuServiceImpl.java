package com.wt.overflow.service.impl;

import com.wt.overflow.bean.*;
import com.wt.overflow.dao.SysModuleButtonMapper;
import com.wt.overflow.dao.SysModuleMapper;
import com.wt.overflow.dao.SysRoleMapper;
import com.wt.overflow.dao.SysRoleauthorizeMapper;
import com.wt.overflow.service.SysRoleAuthorizeMenuService;
import com.wt.overflow.util.UUIDUtil;
import com.wt.overflow.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


@Service
public class SysRoleAuthorizeMenuServiceImpl implements SysRoleAuthorizeMenuService {

	@Autowired
	private SysRoleauthorizeMapper sysRoleauthorizeMapper;
	@Autowired
	private SysModuleMapper sysModuleMapper;
	@Autowired
	private SysModuleButtonMapper sysModuleButtonMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;

	/**
	 * 查询所有的角色权限
	 * 
	 * @return
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 * @name wangt
	 * @Date 2018年4月9日 上午9:30:03
	 */
	
	public List<Map<String, Object>> queryAllData(String roleId) throws IllegalAccessException, InstantiationException, InvocationTargetException, IntrospectionException {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Page page = new Page();
		page.setSortField("F_SortCode");
		page.setOrder("DESC");
		page.setShowCount(99999999);
		parameter.put("page", page);
		List<SysModule> sysModulelist = sysModuleMapper.queryAllData(parameter);//菜单
		List<SysModuleButton> sysModuleButtonlist = sysModuleButtonMapper.queryAllData(parameter);//按钮
		List<Map<String, Object>> reslist = new ArrayList<Map<String, Object>>();
		
		//转化数格式
		for (SysModule sysModule : sysModulelist) {
			
			if(sysModule.getfParentid()!=null && sysModule.getfParentid().equals("0")){//第一级菜单目录
				List<Map<String, Object>> menuslist = new ArrayList<Map<String, Object>>();
				Map<String, Object> map1 = new HashMap<String, Object>();
				
				map1.put("fEnabledMark", sysModule.getfEnabledmark());//前端用于显示是否有效
				map1.put("id", sysModule.getfId());
				map1.put("text", sysModule.getfFullname());
				map1.put("value", sysModule.getfEncode());
				map1.put("img", sysModule.getfIcon());
				map1.put("showcheck", true);
				map1.put("isexpand",true);
				map1.put("complete", true);
				
				map1.put("checkstate", 0);// 0 或者1 确认是否拥有当前菜单的按钮权限
				if(!roleId.equals("")){
					parameter.clear();
					parameter.put("roleId", roleId);parameter.put("FId", sysModule.getfId());
					List<Map<String, Object>> authlist = sysRoleauthorizeMapper.queryMenuByRoleId(parameter);
					if(!authlist.isEmpty())
						map1.put("checkstate", 1);// 0 或者1 确认是否拥有当前菜单的按钮权限
				}
				map1.put("parentnodes", "0");
				map1.put("hasChildren",true);
				
				for (SysModule sysModule2 : sysModulelist) {
					if(sysModule.getfId().equals(sysModule2.getfParentid())){//子级菜单
						List<Map<String, Object>> buttonslist = new ArrayList<Map<String, Object>>();//第三级别按钮
						
						Map<String, Object> map3 = new HashMap<String, Object>();
						map3.put("fEnabledMark", sysModule2.getfEnabledmark());//前端用于显示是否有效
						map3.put("id", sysModule2.getfId());
						map3.put("text", sysModule2.getfFullname());
						map3.put("value", sysModule2.getfEncode());
						map3.put("img", sysModule2.getfIcon());
						map3.put("showcheck", true);
						map3.put("isexpand",true);
						map3.put("complete", true);
						
						map3.put("checkstate", 0);// 0 或者1 确认是否拥有当前菜单的按钮权限
						if(!roleId.equals("")){
							parameter.clear();
							parameter.put("roleId", roleId);parameter.put("FId", sysModule2.getfId());
							List<Map<String, Object>> authlist = sysRoleauthorizeMapper.queryMenuByRoleId(parameter);
							if(!authlist.isEmpty())
								map3.put("checkstate", 1);// 0 或者1 确认是否拥有当前菜单的按钮权限
						}
						map3.put("parentnodes", sysModule2.getfParentid());
						map3.put("hasChildren",true);
						
						for (SysModuleButton sysModuleButton : sysModuleButtonlist) {//按钮权限
							if(sysModule2.getfId() .equals(sysModuleButton.getfModuleid())){//当前按钮数据当前菜单

								Map<String, Object> map5 = new HashMap<String, Object>();
								map5.put("id", sysModuleButton.getfId());
								map5.put("fEnabledMark", sysModuleButton.getfEnabledmark());//前端用于显示是否有效
								map5.put("text", sysModuleButton.getfFullname());
								map5.put("value", sysModuleButton.getfEncode());
								map5.put("showcheck", true);
								map5.put("isexpand",true);
								map5.put("complete", true);
								
								if(sysModuleButton.getfParentid()==null||sysModuleButton.getfParentid().equals("0")){
									map5.put("parentnodes", sysModuleButton.getfModuleid());
								}else{
									map5.put("parentnodes", sysModuleButton.getfParentid());
								}
								//hasChildren 查询是否拥有子级  ChildNodes 显现子级按钮 这里不做子级按钮操作
								map5.put("hasChildren",false);
								for (SysModuleButton sysModuleButton2 : sysModuleButtonlist) {//查询是否存在子级
									if(sysModuleButton2.getfParentid().equals(sysModuleButton.getfId())){
										map5.put("hasChildren",true);//存在子级
									}
								}
								map5.put("checkstate",0);
								if(!roleId.equals("")){
									parameter.clear();
									parameter.put("roleId", roleId);parameter.put("FId", sysModuleButton.getfId());
									List<Map<String, Object>> authlist = sysRoleauthorizeMapper.queryMenuByRoleId(parameter);
									if(!authlist.isEmpty())
										map5.put("checkstate", 1);// 0 或者1 确认是否拥有当前菜单的按钮权限
								}
								
								map5.put("hasChildren",false);//这里不做子级按钮操作
								map5.put("ChildNodes", new ArrayList<Map<String, Object>>());//这里不做子级按钮操作
								buttonslist.add(map5);
							}
						}
						map3.put("ChildNodes", buttonslist);
						menuslist.add(map3);//第二级别的菜单
					}
					
				}
				map1.put("ChildNodes", menuslist);//第一级别菜单
				reslist.add(map1);
			}
		}
		return reslist;
	}

	
	/**
	 * 新增 修改角色权限相关   
	 * @param request
	 * @param response
	 * @param keyValue 角色表中主键ID，为空时新增数据，不为空时修改数据
	 * @param F_OrganizeId	归属组织
	 * @param F_FullName	角色名称
	 * @param F_EnCode	角色编号
	 * @param F_Type	角色类型
	 * @param F_SortCode	显示顺序
	 * @param F_AllowEdit	允许编辑true,false
	 * @param F_AllowDelete	允许删除true,false
	 * @param F_EnabledMark	是否有效true,false
	 * @param F_Description	描述信息
	 * @param permissionIds	拥有权限IDS 用，链接  包括按钮菜单，后续需要自行区分类别
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 下午2:53:24
	 */
	/**
	 * @param keyValue
	 * @param f_OrganizeId
	 * @param f_FullName
	 * @param f_EnCode
	 * @param f_Type
	 * @param f_SortCode
	 * @param f_AllowEdit
	 * @param f_AllowDelete
	 * @param f_EnabledMark
	 * @param f_Description
	 * @param permissionIds
	 * @return
	 * @name wangt	
	 * @Date 2018年4月9日 下午3:13:56
	 */
	
	public Map<String, Object> updateSysRoleAuthorizeMenu(HttpServletRequest request,String keyValue, String f_OrganizeId, String f_FullName,
			String f_EnCode, String f_Type, Integer f_SortCode, Boolean f_AllowEdit, Boolean f_AllowDelete,
			Boolean f_EnabledMark, String f_Description, String permissionIds) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		if(keyValue.equals("")){//新增数据
			Map<String, Object> parmap = new HashMap<String, Object>();
			SysRole sysRole = new SysRole();
			sysRole.setfId(UUIDUtil.getUUID());
			sysRole.setfOrganizeid(f_OrganizeId);
			sysRole.setfFullname(f_FullName);
			sysRole.setfCategory(1);
			sysRole.setfEncode(f_EnCode);
			sysRole.setfType(f_Type);
			sysRole.setfSortcode(f_SortCode);
			sysRole.setfAllowedit(f_AllowEdit);
			sysRole.setfAllowdelete(f_AllowDelete);
			sysRole.setfEnabledmark(f_EnabledMark);
			sysRole.setfDescription(f_Description);
			sysRole.setfCreatortime(new Date());
			sysRole.setfCreatoruserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			parmap.put("sysRole", sysRole);
			sysRoleMapper.addRole(parmap);//新增角色 	返回主键
			
			if(permissionIds!=null && !permissionIds.equals("")){
				String[] permissionIdlist = permissionIds.split(",");
				parmap.clear();
				Page page = new Page();
				page.setSortField("F_SortCode");
				page.setOrder("DESC");
				page.setShowCount(99999999);
				parmap.put("page", page);
				List<SysModule> sysModulelist = sysModuleMapper.queryAllData(parmap);//菜单
				List<String> menuIdlist = new ArrayList<String>(); 
				for (SysModule sysModule : sysModulelist) {//所有菜单
					menuIdlist.add(sysModule.getfId());
				}
				
				for (String permissionId : permissionIdlist) {//权限
					SysRoleauthorize sysRoleauthorize = new SysRoleauthorize();
					if(menuIdlist.contains(permissionId)){//属于菜单
						sysRoleauthorize.setfItemtype(1);//项目类型：1-模块2-按钮3-列表
					}else{
						sysRoleauthorize.setfItemtype(2);//项目类型：1-模块2-按钮3-列表
					}
					sysRoleauthorize.setfId(UUIDUtil.getUUID());
					sysRoleauthorize.setfItemid(permissionId);
					sysRoleauthorize.setfObjecttype(1);
					sysRoleauthorize.setfObjectid(sysRole.getfId());
					sysRoleauthorize.setfCreatoruserid(request.getSession().getAttribute("loginUser").toString());
					sysRoleauthorize.setfCreatortime(new Date());
					parmap.clear();
					parmap.put("sysRoleauthorize", sysRoleauthorize);
					sysRoleauthorizeMapper.addSysRoleauthorize(sysRoleauthorize);
				}
			}
		}else{//修改
			Map<String, Object> parmap = new HashMap<String, Object>();
			SysRole sysRole = new SysRole();
			sysRole.setfId(keyValue);
			sysRole.setfOrganizeid(f_OrganizeId);
			sysRole.setfFullname(f_FullName);
			sysRole.setfEncode(f_EnCode);
			sysRole.setfType(f_Type);
			sysRole.setfSortcode(f_SortCode);
			sysRole.setfAllowedit(f_AllowEdit);
			sysRole.setfAllowdelete(f_AllowDelete);
			sysRole.setfEnabledmark(f_EnabledMark);
			sysRole.setfDescription(f_Description);
			sysRole.setfLastmodifytime(new Date());
			sysRole.setfLastmodifyuserid(((SysUser)request.getSession().getAttribute("loginUser")).getfId());
			parmap.put("sysRole", sysRole);
			sysRoleMapper.updateRoleByRoleId(parmap);//修改角色 	返回主键
			sysRoleauthorizeMapper.deleteAllByRoleId(sysRole.getfId());//根据角色id 删除所有
			if(permissionIds!=null && !permissionIds.equals("")){
				String[] permissionIdlist = permissionIds.split(",");
				parmap.clear();
				Page page = new Page();
				page.setSortField("F_SortCode");
				page.setOrder("DESC");
				page.setShowCount(99999999);
				parmap.put("page", page);
				List<SysModule> sysModulelist = sysModuleMapper.queryAllData(parmap);//菜单
				List<String> menuIdlist = new ArrayList<String>(); 
				for (SysModule sysModule : sysModulelist) {//所有菜单
					menuIdlist.add(sysModule.getfId());
				}
				
				for (String permissionId : permissionIdlist) {//权限
					SysRoleauthorize sysRoleauthorize = new SysRoleauthorize();
					if(menuIdlist.contains(permissionId)){//属于菜单
						sysRoleauthorize.setfItemtype(1);//项目类型：1-模块2-按钮3-列表
					}else{
						sysRoleauthorize.setfItemtype(2);//项目类型：1-模块2-按钮3-列表
					}
					sysRoleauthorize.setfId(UUIDUtil.getUUID());
					sysRoleauthorize.setfItemid(permissionId);
					sysRoleauthorize.setfObjecttype(1);
					sysRoleauthorize.setfObjectid(sysRole.getfId());
					sysRoleauthorize.setfCreatoruserid(request.getSession().getAttribute("loginUser").toString());
					sysRoleauthorize.setfCreatortime(new Date());
					parmap.clear();
					parmap.put("sysRoleauthorize", sysRoleauthorize);
					int i= sysRoleauthorizeMapper.addSysRoleauthorize(sysRoleauthorize);
					System.out.println("添加权限："+i);
				}
			}
		}
		resmap.put("state", "success");
		resmap.put("message", "操作成功");
		resmap.put("data", "");
		return resmap;
	}

}
