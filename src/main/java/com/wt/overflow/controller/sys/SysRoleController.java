package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysRole;
import com.wt.overflow.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 角色相关
 * 
 * @author wangt
 *
 */
@Controller
@RequestMapping(value = "role")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 分页查询  
	 * 
	 * @param request
	 * @param response
	 * @param rows
	 *            每页显示数量
	 * @param page
	 *            当前页码
	 * @param sidx
	 *            排序字段
	 * @param sord
	 *            排序方式【asc，desc】
	 * @param keyword
	 *            关键字查询（筛选F_FullName或F_EnCode字段）
	 * @return
	 * @throws
	 * @name wangt
	 * @Date 2018年4月8日 下午3:46:50
	 */
	@RequestMapping("queryRoleByPage")
	@ResponseBody
	public Object queryRoleByPage(HttpServletRequest request, HttpServletResponse response, Integer rows,
			Integer page, String sidx, String sord, String keyword) {
		Map<String, Object> resmap = new HashMap<String, Object>();
		resmap = sysRoleService.queryRoleByPage(rows, page, sidx, sord, keyword);
		return resmap.get("data");
	}


	/**
	 * 删除角色机构id
	 * @param request
	 * @param response
	 *
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 下午3:57:18
	 */
	@RequestMapping("deleteRole")
	@ResponseBody
	public Map<String, Object> deleteRole(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="keyValue", defaultValue="") String keyValue) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysRoleService.deleteRole(request,keyValue);
		if(status>0){
			parameter.put("state", "success");
			parameter.put("message", "操作成功");
		}else{
			parameter.put("state", "error");
			parameter.put("message", "操作失败");
		}
		return parameter;
	}

	
	/**
	 * 查询机构  查询角色
	 * @param request
	 * @param response
	 *
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 下午3:57:18
	 */
	@RequestMapping("queryOneByRoleId")
	@ResponseBody
	public SysRole queryOneByRoleId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(value="keyValue", defaultValue="") String keyValue) throws Exception {
		return sysRoleService.queryOneByRoleId(keyValue);
	}
	
	/***************************************岗位相关******************************************/
	
	/**
	 * 分页查询岗位所有数据
	 * @param request
	 * @param response
	 * @param rows 
	 * @param page
	 * @param sidx
	 * @param sord 
	 * @param keyword
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 下午4:58:12
	 */
	@RequestMapping("queryAllDuty")
	@ResponseBody
	public List<Map<String, Object>> queryAllDuty(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="rows", defaultValue="") Integer rows,
			@RequestParam(value="page", defaultValue="") Integer page,
			@RequestParam(value="sidx", defaultValue="") String sidx,
			@RequestParam(value="sord", defaultValue="") String sord,
			@RequestParam(value="keyword", defaultValue="") String keyword) throws Exception {
		return sysRoleService.queryAllDuty(rows,page,sidx,sord,keyword);
	}
	
	
	
	/**
	 * 机构新增修改
	 * @param request
	 * @param response
	 * @param sysRole
	 * @param keyword
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 下午5:21:32
	 */
	@RequestMapping("updateDuty")
	@ResponseBody
	public Map<String, Object> updateDuty(HttpServletRequest request, HttpServletResponse response,
                                          SysRole sysRole, @RequestParam(value="keyValue", defaultValue="") String keyword) throws Exception {
		return sysRoleService.updateDuty(request,sysRole,keyword);
	}
}
