package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysItemsdetail;
import com.wt.overflow.service.SysItemsDetailService;
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
@RequestMapping(value = "sysItemsDetail")
public class SysItemsDetailController {
	
	@Autowired
	private SysItemsDetailService sysItemsDetailService;
	
	@RequestMapping("getSelectJson")
	@ResponseBody
	public List<Map<String, Object>> getSelectJson(String enCode){
		List<Map<String, Object>> list=sysItemsDetailService.getSelectJson(enCode);
		return list;
		
	}
	

	/**
	 * 查询主要角色分类
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月9日 上午8:57:50
	 */
	@RequestMapping("queryMainRole")
	@ResponseBody
	public List<Map<String, Object>> queryMainRole(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return sysItemsDetailService.queryMainRole();
	}

	
	
	@RequestMapping("queryOneByFId")
	@ResponseBody
	public SysItemsdetail queryOneByFId(HttpServletRequest request, HttpServletResponse response, String keyValue) throws Exception {
		return sysItemsDetailService.queryOneByFId(keyValue);
	}
	
	
	/**
	 * 根据主键删除
	 * @param request
	 * @param response
	 * @param keyword
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 上午11:35:48
	 */
	@RequestMapping("deleteItemsDetailByFId")
	@ResponseBody
	public Map<String, Object> deleteItemsByFId(HttpServletRequest request, HttpServletResponse response,String keyValue) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysItemsDetailService.deleteItemsDetailByFId(keyValue,request);
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
	 * 新增或者修改数据字典
	 * @param request
	 * @param response
	 * @param keyValue
	 * @param F_ItemName
	 * @param F_ItemCode
	 * @param F_SortCode
	 * @param F_IsDefault
	 * @param F_EnabledMark
	 * @param F_Description
	 * @param F_ItemId
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 下午2:05:15
	 */
	@RequestMapping("updateItemsDetail")
	@ResponseBody
	public Map<String, Object> updateItemsDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="keyValue", defaultValue="") String keyValue,
			@RequestParam(value="fItemname", defaultValue="") String F_ItemName,
			@RequestParam(value="fItemcode", defaultValue="") String F_ItemCode,
			@RequestParam(value="fSortcode", defaultValue="0") Integer F_SortCode,
			@RequestParam(value="fIsdefault", defaultValue="1") Integer F_IsDefault,
			@RequestParam(value="fEnabledmark", defaultValue="1") Integer F_EnabledMark,
			@RequestParam(value="fDescription", defaultValue="") String F_Description,
			@RequestParam(value="fItemid", defaultValue="") String F_ItemId) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysItemsDetailService.updateItemsDetail(request,keyValue,F_ItemName,F_ItemCode,F_SortCode,
				(F_IsDefault==1?true:false),(F_EnabledMark==1?true:false),F_Description,F_ItemId);
		if(status>0){
			parameter.put("state", "success");
			parameter.put("message", "操作成功");
		}else{
			parameter.put("state", "error");
			parameter.put("message", "操作失败");
		}
		return parameter;
	}
}
