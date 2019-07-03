<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证码页面</title>
<script type="text/javascript"
    src="${pageContext.request.contextPath}/page/js/lib/jquery-1.11.1.js"></script>
</head>
<body>
    <form action="${pageContext.request.contextPath}/login/verifyUser" method="post">
        	请输入验证码：<input type="text" name="ImageCode" style="width: 80px;" /> <img id="imgObj" alt="验证码"
            src="${pageContext.request.contextPath}/login/getImage"><a href="#" onclick="changeImg()">换一张</a><br/> <input
            type="submit" value="提交" />
    </form>

</body>
<script type="text/javascript">
    $(function() {

    });

    function changeImg() {        
        var imgSrc = $("#imgObj");    
        var src = imgSrc.attr("src");        
        imgSrc.attr("src", chgUrl(src));
    }
    
    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 30);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }

</script>
</html>