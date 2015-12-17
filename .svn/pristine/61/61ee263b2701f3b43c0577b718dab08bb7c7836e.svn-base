<html>
<head>
	<title>player.php</title>
	<script src="../../js/libs/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript">

		function getQueryString(name) { 
			alert("getQueryString");
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
			var r = window.location.search.substr(1).match(reg); 
			if (r != null) return unescape(r[2]); return null; 
		} 

		function IsPC() 
		{ 
			alert("IsPC");
			var userAgentInfo = navigator.userAgent; 
			var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"); 
			var flag = true; 
			for (var v = 0; v < Agents.length; v++) { 
				if (userAgentInfo.indexOf(Agents[v]) > 0) 
				{
				 	flag = false; 
				 	break; 
				} 
			} 
			return flag; 
		}

		
		var param = getQueryString("storeId");
		var storeId = param.split(",")[0];
		var platform = param.split(",")[1];
		alert("storeId:"+storeId);
		alert("platform:"+platform);
		// 加载xml文档
		var loadXML = function (xmlFile) {
			alert("loadXML");
			var xmlDoc;
			if (window.ActiveXObject) {
				xmlDoc = new ActiveXObject('Microsoft.XMLDOM');//IE浏览器
				xmlDoc.async = false;
				xmlDoc.load(xmlFile);
			}
			else if (isFirefox=navigator.userAgent.indexOf("Firefox")>0) { 
				//火狐浏览器
				//else if (document.implementation && document.implementation.createDocument) {//这里主要是对谷歌浏览器进行处理
				xmlDoc = document.implementation.createDocument('', '', null);
				xmlDoc.load(xmlFile);
			}
			else{ 
				//谷歌浏览器
				var xmlhttp = new window.XMLHttpRequest();
				xmlhttp.open("GET",xmlFile,false);
				xmlhttp.send(null);
				if(xmlhttp.readyState == 4){
					xmlDoc = xmlhttp.responseXML.documentElement;
				} 
			}
			return xmlDoc;
		}

		// 首先对xml对象进行判断
		var  checkXMLDocObj = function (xmlFile) {
			alert("checkXMLDocObj");
			var xmlDoc = loadXML(xmlFile);
			if (xmlDoc == null) {
				alert('您的浏览器不支持xml文件读取,于是本页面禁止您的操作,推荐使用IE5.0以上可以解决此问题!');
				// window.location.href = '../err.html';
			}

			var nodes;
			if($.browser.msie){ // 注意各个浏览器之间的区别
				nodes = xmlDoc.getElementsByTagName('host'); //读取XML文件中需要显示的数据
			} 
			else if (isFirefox=navigator.userAgent.indexOf("Firefox")>0){
				nodes = xmlDoc.getElementsByTagName('host'); //读取XML文件中需要显示的数据
			}
			else{
				nodes = xmlDoc.getElementsByTagName('host');
			}

			var totalText;
			var flashPlayerHost;
			var html5PlayerHost;
			var subNode;
			if(platform == "9cooo")
			{
				subNode = nodes[0];
			}
			else if (platform == "cince") {
				subNode = nodes[1];
			}
			else if(platform == "dev")
			{
				subNode = nodes[2];
			}
			else if(platform == "test")
			{
				subNode = nodes[3];
			}

			flashPlayerHost = subNode.getElementsByTagName('mall')[0].innerHTML;
			html5PlayerHost = subNode.getElementsByTagName('mall')[0].innerHTML;
			var userAgentInfo = navigator.userAgent; 
			var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod"); 
			var flag = true; 
			for (var v = 0; v < Agents.length; v++) { 
				if (userAgentInfo.indexOf(Agents[v]) > 0) 
				{
				 	flag = false; 
				 	break; 
				} 
			} 
			if(flag)
			{
				window.location = flashPlayerHost+"/Play/Index?id="+storeId;
				alert(flashPlayerHost+"/Play/Index?id="+storeId);
			}else
			{
				window.location = html5PlayerHost+"/m?storeId="+storeId;
				alert(html5PlayerHost+"/m?storeId="+storeId);
			}
		}
		checkXMLDocObj("../xml/host.xml");
	</script>

	<?php
		header("Last-Modified: " . gmdate( "D, d M Y H:i:s" ) . "GMT" );  
		header("Cache-Control: no-cache, must-revalidate" );  
	?>
</head>
<body>
	
		
	
</body>

</html>