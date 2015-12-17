<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="<%=request.getContextPath() %>"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,target-densitydpi=high-dpi,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>微信扫描支付</title>
<link rel="stylesheet" href="${ctx}/source/css/himall/buy_package.css" />
</head>
<body>

<div class="sj_buy-video">
   <div class="sj-money-videoCons">
   	<div class="money-je">
         支付金额：<span>￥${params.totalFee}</span>
    </div>
    <div class="money-weixin">
         <img src="${params.imgUrl}" width="500" height="500" />
           
         <div class="wx-wap-fonts">[长按并"识别图中二维码"即可完成支付]</div>
    </div>
   </div>
   
</div>
</body>
</html>
