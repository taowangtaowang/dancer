package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysItems;
import com.wt.overflow.service.SysItemsService;
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
 * 用户登录相关
 * 
 * @author wangt
 *
 */
@Controller
@RequestMapping(value = "items")
public class SysItemController {

	@Autowired
	private SysItemsService sysItemsService;

	
	/**
	 * 字典数据分类列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 上午10:26:51
	 */
	@RequestMapping("queryAllItemsList")
	@ResponseBody
	public Map<String, Object> queryAllItemsList(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		return sysItemsService.queryAllItemsList();
	}
	
	
	
	/**
	 * 字典数结构ztree
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 上午10:26:51
	 */
	@RequestMapping("queryAllDataByZtree")
	@ResponseBody
	public List<Map<String, Object>> queryAllDataByZtree(HttpServletRequest request, HttpServletResponse response ) throws Exception {
		return sysItemsService.queryAllDataByZtree();
	}
	
	/**
	 * 字典数据分页查询
	 * @param request
	 * @param response
	 * @param rows
	 * @param page
	 * @param sidx
	 * @param sord
	 * @param keyword	关键字查询（筛选F_FullName或F_EnCode字段）
	 * @param itemId 	模糊查询对应Sys_Items表F_Id
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 上午10:46:35
	 */
	@RequestMapping("queryAllDataByPage")
	@ResponseBody
	public List<Map<String, Object>> queryAllDataByPage(HttpServletRequest request, HttpServletResponse response,
			Integer rows,Integer page, String sidx, String sord, String keyword,String itemId) throws Exception {
		return sysItemsService.queryAllDataByPage(rows,page,sidx,sord,keyword,itemId);
	}
	
	
	
	
	@RequestMapping("queryOneByFId")
	@ResponseBody
	public SysItems queryOneByFId(HttpServletRequest request, HttpServletResponse response, String keyValue) throws Exception {
		return sysItemsService.queryOneByFId(keyValue);
	}
	
	
	/**
	 * 根据主键删除
	 * @param request
	 * @param response
	 *
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 上午11:35:48
	 */
	@RequestMapping("deleteItemsByFId")
	@ResponseBody
	public Map<String, Object> deleteItemsByFId(HttpServletRequest request, HttpServletResponse response,String keyValue) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysItemsService.deleteItemsByFId(keyValue,request);
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
	 * 新增 修改
	 * @param request
	 * @param response
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月16日 下午2:38:21
	 */
	@RequestMapping("updateItems")
	@ResponseBody
	public Map<String, Object> updateItems(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="keyValue", defaultValue="") String keyValue,
			@RequestParam(value="fParentid", defaultValue="") String fParentid,
			@RequestParam(value="fEncode", defaultValue="") String fEncode,
			@RequestParam(value="fSortcode", defaultValue="0") Integer fSortcode,
			@RequestParam(value="fEnabledmark", defaultValue="1") Integer fEnabledmark,
			@RequestParam(value="fDescription", defaultValue="") String fDescription,
			@RequestParam(value="fFullname", defaultValue="") String fFullname) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		//@RequestParam(value="fEnabledmark", defaultValue="1") Integer fEnabledmark,
		Integer status = sysItemsService.updateItems(request,keyValue,fFullname,fEncode,fSortcode,
				fEnabledmark,fParentid,fDescription);
		if(status>0){
			parameter.put("state", "success");
			parameter.put("message", "操作成功");
			return parameter;
		}else if(status==-99){
			parameter.put("state", "error");
			parameter.put("message", "编号重复，请重新设置");
			return parameter;
		}else{
			parameter.put("state", "error");
			parameter.put("message", "操作失败");
			return parameter;
		}
	}
	
}
