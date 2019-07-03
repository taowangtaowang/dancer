package com.wt.overflow.controller.sys;

import com.wt.overflow.bean.User;
import com.wt.overflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class StudentController {

	@Autowired
	private UserService service;

	@RequestMapping("Student")
	@ResponseBody
	public String Get() {
		List<User> students = service.selectByCondition(new User());
		String jsonResult = com.alibaba.fastjson.JSON.toJSONString(students);
		return jsonResult;
	}
}
