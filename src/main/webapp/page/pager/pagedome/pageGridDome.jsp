<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 引入分页页面工具类 -->
<%@ taglib prefix="limit" uri="http://www.util.com/limit"%>
<%
	String path = request.getContextPath();
	String fileUrl = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort();
%>

<script type="text/javascript" src="<%=path%>/page/js/lib/jquery-1.11.1.js"></script>

 <!-- 加入分页-->
<link href="<%=path%>/page/pager/pagination.css" type="text/css"  rel="stylesheet"   />
 <!-- 加入分页-->
<script type="text/javascript" src="<%=path%>/page/pager/jquery.pagination.js"></script>

<script type="text/javascript">
var currentPage; //定义开始页
var showCount;  // 定义每页显示条数
//加载数据
load_grid = function(state){
      if(state){
	      currentPage=1;
	  }
      $.ajax({
			type: "POST",
			data:{
				 'currentPage':currentPage, // 显示页
				 'showCount':showCount // 每页显示条数
			},
		    url: <%=path%>+'/sysUser/findSysUserList.action', // 这里只是 举列  并不能真正 跳转
			success: function(response){
			    
			},
			error:function(){
			   
			}
	  });
};

//分页时请求的数据
pageselectCallback = function(page_id,perpage,jq){
		showCount = perpage;
        currentPage = page_id+1;
        alert("点击下一步");
		
        // 重新加载 下一页 数据
   		load_grid();
}
</script>

<!-- 放入分页 div -->
<div id="pagers" class="pagination"></div>

<script type="text/javascript">
	//分页加载的主方法
	$(function(){
		var pager = eval({"showCount":10,"totalPage":0,"totalResult":11,"currentPage":1,"currentResult":0}); //分页信息json字符串 ${page} 为后台返回的 分页对象
		$("#pagers").pagination({'page':pager,callback: pageselectCallback});
	});
</script>
 