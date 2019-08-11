package com.wt.overflow.exception;

import java.io.Serializable;

public class ResultUtil implements Serializable {
	private Integer code;
	private String state;
	private String msg="";
	private Long count=0L;
	private Object data;
	
	public ResultUtil() {
		super();
	}

	public ResultUtil(Integer code,String state) {
		super();
		this.code = code;
		this.state = state;
	}

	public ResultUtil(Integer code, String state,String msg) {
		super();
		this.code = code;
		this.state = state;
		this.msg = msg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static ResultUtil ok(){
		return new ResultUtil(0,"success");
	}
	
	public static ResultUtil ok(Object resData){
		ResultUtil result = new ResultUtil();
		result.setCode(0);
		result.setState("success");
		result.setData(resData);
		return result;
	}
	public static ResultUtil ok(String resMsg){
		ResultUtil result = new ResultUtil();
		result.setCode(0);
		result.setState("success");
		result.setMsg(resMsg);
		return result;
	}
	
	public static ResultUtil error(){
		return new ResultUtil(500,"error","没有此权限，请联系超管！");
	}
	public static ResultUtil error(String str){
		return new ResultUtil(500,"error",str);
	}
}
