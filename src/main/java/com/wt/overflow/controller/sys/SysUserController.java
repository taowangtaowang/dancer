package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.SysUser;
import com.wt.overflow.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户相关
 * @author wangt
 *
 */
@Controller
@RequestMapping(value = "sysUser")
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping("queryUserByPage")
	@ResponseBody
	public Object queryUserByPage(HttpServletRequest request, HttpServletResponse response, 
			Integer rows,@RequestParam(value="page", defaultValue="1") Integer page,
			String sidx, String sord, String keyword) {
		return sysUserService.queryUserByPage(rows, page, sidx, sord, keyword);
	}
	

	
	/**
	 * 新增或者修改用户
	 * @param request
	 * @param response
	 * @param sysUser 新增或者修改用户
	 * @return
	 * @name wangt	
	 * @Date 2018年4月10日 上午9:21:10
	 */
	@RequestMapping("updateSysUser")
	@ResponseBody
	public Object updateSysUser(HttpServletRequest request, HttpServletResponse response, SysUser sysUser,
			String fUserpassword,@RequestParam(value="keyValue", defaultValue="") String keyValue,
			@RequestParam(value="f_Birthday", defaultValue="") String f_Birthday) {
		Map<String, Object> parameter = new HashMap<String, Object>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			sysUser.setfBirthday(sdf.parse(f_Birthday));
//		} catch (ParseException e) {
//			e.printStackTrace();
//			parameter.put("state", "error");
//			parameter.put("message", "时间选择错误");
//			return parameter;
//		}
		Integer status = sysUserService.updateSysUser(request,sysUser,fUserpassword,keyValue);
		if(status>0){
			parameter.put("state", "success");
			parameter.put("message", "操作成功");
		}else if (status == -1){
			parameter.put("state", "error");
			parameter.put("message", "用户账号相同，请重新设置");
		}else{
			parameter.put("state", "error");
			parameter.put("message", "操作失败");
		}
		return parameter;
	}
	
	
	@RequestMapping("deleteSysUser")
	@ResponseBody
	public Map<String, Object> deleteSysUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="keyValue", defaultValue="") String keyValue) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysUserService.deleteSysUser(request,keyValue);
		if(status>0){
			parameter.put("state", "success");
			parameter.put("message", "操作成功");
		}else{
			parameter.put("state", "error");
			parameter.put("message", "操作失败");
		}
		return parameter;
	}
	
	
	@RequestMapping("queryOneBySysUserId")
	@ResponseBody
	public SysUser queryOneBySysUserId(HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(value="keyValue", defaultValue="admin") String keyValue) throws Exception {
		return sysUserService.queryOneBySysUserId(keyValue);
		
	}
	
	
	/**
	 * 启用禁用用户
	 * @param request
	 * @param response
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月10日 上午10:05:26
	 */
	@RequestMapping("updateSysUserEnabled")
	@ResponseBody
	public Map<String, Object> updateSysUserEnabled(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="keyValue", defaultValue="") String keyValue) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysUserService.updateSysUserEnabled(request,keyValue);
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
	 * 用户密码重置
	 * @param request
	 * @param response
	 * @param keyValue
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月10日 上午10:05:26
	 */
	@RequestMapping("updateSysUserReset")
	@ResponseBody
	public Map<String, Object> updateSysUserReset(HttpServletRequest request, HttpServletResponse response,
			SysUser sysUser,
			@RequestParam(value="keyValue", defaultValue="") String keyValue) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysUserService.updateSysUserReset(request,sysUser,keyValue);
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
	 * 保存用户头像
	 * @param request
	 * @param response
	 * @param fId 用户主键
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年4月23日 下午4:37:46
	 */
	@RequestMapping("updateSysUserHeadIcon")
	@ResponseBody
	public Map<String, Object> updateSysUserHeadIcon(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="fHeadicon", defaultValue="") String fHeadicon,
			@RequestParam(value="fId", defaultValue="") String fId) throws Exception {
		Map<String, Object> parameter = new HashMap<String, Object>();
		Integer status = sysUserService.updateSysUserHeadIcon(request,fHeadicon,fId);
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
	 * 根据当前登录用户所在单位 查询出当前单位的所有人员
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年5月30日 上午9:26:05
	 */
	@RequestMapping("querySysUserByOrganId")
	@ResponseBody
	public Map<String, Object> querySysUserByOrganId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return sysUserService.querySysUserByOrganId(request);
	}
}
