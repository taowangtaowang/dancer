package com.wt.overflow.controller.sys;

import com.wt.overflow.service.SysRoleAuthorizeMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 权限相关
 * @author wangt
 *
 */
@Controller
@RequestMapping(value = "authorizeMenu")
public class SysRoleAuthorizeMenuController {


	@Autowired
	private SysRoleAuthorizeMenuService sysRoleAuthorizeMenuService;


	
	/**
	 * 查询角色权限    
	 * @param request
	 * @param response
	 * @param roleId 为“” 表示所有无权限 ，反之被选中
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 上午10:51:29
	 */
	@RequestMapping("queryAllData")
	@ResponseBody
	public List<Map<String, Object>> queryAllData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="roleId", defaultValue="") String roleId) throws Exception {
		return sysRoleAuthorizeMenuService.queryAllData(roleId);
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
	@RequestMapping("updateSysRoleAuthorizeMenu")
	@ResponseBody
	public Map<String, Object> updateSysRoleAuthorizeMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="keyValue", defaultValue="") String keyValue,
			@RequestParam(value="fOrganizeid", defaultValue="") String F_OrganizeId,
			@RequestParam(value="fFullname", defaultValue="") String F_FullName,
			@RequestParam(value="fEncode", defaultValue="") String F_EnCode,
			@RequestParam(value="fType", defaultValue="") String F_Type,
			@RequestParam(value="fSortcode", defaultValue="1") Integer F_SortCode,
			@RequestParam(value="fAllowddit", defaultValue="0") int F_AllowEdit,
			@RequestParam(value="fAllowDelete", defaultValue="0") int F_AllowDelete,
			@RequestParam(value="fEnabledmark", defaultValue="0") int F_EnabledMark,
			@RequestParam(value="fDescription", defaultValue="") String F_Description,
			@RequestParam(value="permissionIds", defaultValue="") String permissionIds) throws Exception {
		return sysRoleAuthorizeMenuService.updateSysRoleAuthorizeMenu(request,
				keyValue,F_OrganizeId,F_FullName,F_EnCode,F_Type,F_SortCode,
				F_AllowEdit,F_AllowDelete,F_EnabledMark,F_Description,permissionIds);
	}
}
