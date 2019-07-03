package com.wt.overflow.controller.sys;

import com.wt.overflow.service.SysCheckinInService;
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
import java.util.Map;


/**
 * 考勤相关
 * @author wangt
 *
 */
@Controller
@RequestMapping(value = "sysCheckIn")
public class SysCheckinInController {

	@Autowired
	private SysCheckinInService sysCheckinInService;
	
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
		dateFormat.setLenient(false);  
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	/**
	 * 查询考勤详情
	 * @param request
	 * @param response
	 * @param startTime	开始时间
	 * @param endTime	结束时间
	 * @param userName	某人（名字）
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年5月9日 上午9:12:59
	 */
	@RequestMapping("queryAllCheckInDetail")
	@ResponseBody
	public Map<String, Object> queryAllCheckInDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startTime", defaultValue="2017-01-01 00:00:00") Date startTime,
			@RequestParam(value="endTime", defaultValue="2099-01-01 00:00:00") Date endTime,
			@RequestParam(value="userName", defaultValue="") String userName) throws Exception {
		
		return sysCheckinInService.queryAllCheckInDetail(startTime,endTime,userName);
	}


	/**
	 * 查询具体出勤详情
	 * @param request
	 * @param response
	 * @param startTime
	 * @param endTime
	 * @param userName
	 * @return
	 * @throws Exception
	 * @name wangt	
	 * @Date 2018年5月9日 上午10:04:02
	 */
	@RequestMapping("queryAlllistDetail")
	@ResponseBody
	public Map<String, Object> queryAlllistDetail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="startTime", defaultValue="2017-01-01 00:00:00") Date startTime,
			@RequestParam(value="endTime", defaultValue="2099-01-01 00:00:00") Date endTime,
			@RequestParam(value="typeString", defaultValue="qiandao") String  typeString,
			@RequestParam(value="userName", defaultValue="") String userName) throws Exception {
		return sysCheckinInService.queryAlllistDetail(startTime,endTime,userName,typeString);
	}
}
