package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysOrganize;
import com.wt.overflow.service.SysOrganizeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

@Controller
@RequestMapping(value = "sysOrganize")
public class SysOrganizeController {

	@Resource(name="sysOrganizeService")
	private SysOrganizeService sysOrganizeService;
	
	
	/**
	 * 新建用户需要选择单位的ztree结构
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月17日 下午7:22:24
	 */
	@RequestMapping("queryAllDataZtree")
	@ResponseBody
	public Map<String, Object> queryAllDataZtree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", sysOrganizeService.queryAllDataZtree());
		return map;
	}
	
	
	
	@RequestMapping("queryAllData")
	@ResponseBody
	public Map<String, Object> queryAllData(HttpServletRequest request, HttpServletResponse response,
			 Integer rows,Integer page, String sidx, String sord, String keyword,String nodeid,String n_level) throws Exception {
		return sysOrganizeService.queryAllData(rows, page, sidx, sord, keyword,nodeid,n_level);
	}
	
	@RequestMapping("queryChildsDataByFId")
	@ResponseBody
	public List<SysOrganize> queryChildsDataByFId(HttpServletRequest request, HttpServletResponse response, String keyword) throws Exception {
		return sysOrganizeService.queryChildsDataByFId(keyword);
	}
	
	/**    
	* @Description: 新增修改机构  
	* @author wumin
	* @date 2018-04-08 16:10 
	* @param request
	* @param organize
	* @return
	*/
	@RequestMapping(value = "/updateOrganize")
	@ResponseBody
	public Object updateOrganize(HttpServletRequest request, SysOrganize organize, @RequestParam(value="keyValue", defaultValue="") String keyValue) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Integer isSuccess = sysOrganizeService.updateOrganize(organize,keyValue,request);
		if (isSuccess == 1) {
			map.put("message", "操作成功");
			map.put("status", "success");
		}else{
			map.put("message", "操作失败");
			map.put("status", "failure");
		}
		return map;
	}

	/**    
	* @Description: 删除机构  
	* @author wumin
	* @date 2018-04-08 16:19 
	* @param request
	* @return
	*/
	@RequestMapping(value = "/deleteOrganize")
	@ResponseBody
	public Object deleteOrganize(HttpServletRequest request,@RequestParam(value="keyValue", defaultValue="") String  keyValue) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Integer isSuccess = sysOrganizeService.deleteOrganize(request ,keyValue);
		if (isSuccess == 1) {
			map.put("message", "删除成功");
			map.put("status", "success");
		}else{
			map.put("message", "删除失败");
			map.put("status", "failure");
		}
		return map;
	}

	/**
	 * 机构查看
	 * @param request
	 * @param response
	 * @param keyValue 主键id 机构主键id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("queryOneByOrganizeId")
	@ResponseBody
	public Object queryOneByOrganizeId(HttpServletRequest request, HttpServletResponse response,
												@RequestParam(value="keyValue", defaultValue="") String keyValue) throws Exception {
		return sysOrganizeService.queryOneByOrganizeId(keyValue);
	}
	
	/** 
	 * 修改机构描述
	 * @param request
	 *
	 * @return
	 * @name wangt	
	 * @Date 2018年4月9日 下午6:08:34
	 */
	@RequestMapping(value = "/updateOrganizeDes")
	@ResponseBody
	public Object updateOrganizeDes(HttpServletRequest request,
			@RequestParam(value="F_Description", defaultValue="") String  F_Description,
			@RequestParam(value="F_Id", defaultValue="") String  F_Id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer isSuccess = sysOrganizeService.updateOrganizeDes(request ,F_Description,F_Id);
		if (isSuccess == 1) {
			map.put("message", "操作成功");
			map.put("status", "success");
		}else{
			map.put("message", "操作失败");
			map.put("status", "failure");
		}
		return map;
	}
	
	@RequestMapping("selectAllParentOrganize")
	@ResponseBody
	public Object selectAllParentOrganize(HttpServletRequest request, HttpServletResponse response) {
		
		return sysOrganizeService.selectAllParentOrganize();
	}
}
