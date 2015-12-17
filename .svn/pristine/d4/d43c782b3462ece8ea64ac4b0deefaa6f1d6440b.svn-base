<!doctype html public "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK"/>
    <title>HostManager视频电商主机地址维护页面</title>
    <script src="../../js/libs/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    // 加载xml文档
        var loadXML = function (xmlFile) {
            var xmlDoc;
            if (window.ActiveXObject) {
                xmlDoc = new ActiveXObject('Microsoft.XMLDOM');//IE浏览器
                xmlDoc.async = false;
                xmlDoc.load(xmlFile);
            }
            else if (isFirefox=navigator.userAgent.indexOf("Firefox")>0) { //火狐浏览器
            //else if (document.implementation && document.implementation.createDocument) {//这里主要是对谷歌浏览器进行处理
                xmlDoc = document.implementation.createDocument('', '', null);
                xmlDoc.load(xmlFile);
            }
            else{ //谷歌浏览器
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
            var xmlDoc = loadXML(xmlFile);
            if (xmlDoc == null) {
                alert('您的浏览器不支持xml文件读取,于是本页面禁止您的操作,推荐使用IE5.0以上可以解决此问题!');
                window.location.href = '../err.html';
            }
            return xmlDoc;
        }

        var xmlDoc = checkXMLDocObj('../xml/host.xml');//读取到xml文件中的数据
        // var a = document.getElementsByTagName("a");//获取所有的A标签
        $(document).ready(function () {
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
             for(var j =0;j<nodes.length;j++)
        {
            var resource = nodes[j];
            var id = resource.id;
            var mallHost = resource.getElementsByTagName('mall')[0].innerHTML;
            var videoHost= resource.getElementsByTagName('video')[0].innerHTML;
            var videoImageHost = resource.getElementsByTagName('videoImage')[0].innerHTML;

            // $("#output").html(totalText)//显示
            document.getElementById(id+"mall").value=resource.getElementsByTagName('mall')[0].innerHTML;
            document.getElementById(id+"video").value=resource.getElementsByTagName('video')[0].innerHTML;
            document.getElementById(id+"videoImage").value=resource.getElementsByTagName('videoImage')[0].innerHTML;
        }
        });

    
    function dophp(hostId){
        // alert(hostId);
        // alert(document.getElementById(hostId).value);
        var hostArr = new Array(4);
        hostArr[0] = new Array(3);
        hostArr[1] = new Array(3);
        hostArr[2] = new Array(3);
        hostArr[3] = new Array(3);
        
        hostArr[0] = [document.getElementById('9cooomall').value,document.getElementById('9cooovideo').value,document.getElementById('9cooovideoImage').value];
        hostArr[1] = [document.getElementById('cincemall').value,document.getElementById('cincevideo').value,document.getElementById('cincevideoImage').value];
        hostArr[2] = [document.getElementById('devmall').value,document.getElementById('devvideo').value,document.getElementById('devvideoImage').value];
        hostArr[3] = [document.getElementById('testmall').value,document.getElementById('testvideo').value,document.getElementById('testvideoImage').value];

        var url = 'deal.php';
        // $.post(url,{data : JSON.stringify(hostArr)},function(data){
          
        // },'json');

        $.post(url,{hid:hostId,hValue:document.getElementById(hostId).value},function(data){

        },'json');
  }
    </script>

    
</head>
<body>
<div id="9cooo"style="color:red">
    9cooo:<br/>
    mallHost:<input id="9cooomall" name="9cooomall" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('9cooomall')"><br><br>
    videoHost:<input id="9cooovideo" name="9cooovideo" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('9cooovideo')"><br><br>
    videoImageHost:<input id="9cooovideoImage" name="9cooovideoImage" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('9cooovideoImage')"><br><br>
</div>

<div id="cince"style="color:red">
    cince:<br/>
    mallHost:<input id="cincemall" name="cincemall" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('cincemall')"><br><br>
    videoHost:<input id="cincevideo" name="cincevideo" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('cincevideo')"><br><br>
    videoImageHost:<input id="cincevideoImage" name="cincevideoImage" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('cincevideoImage')"><br><br>
</div>

<div id="dev"style="color:red">
    开发环境:<br/>
    mallHost:<input id="devmall" name="devmall" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('devmall')"><br><br>
    videoHost:<input id="devvideo" name="devvideo" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('devvideo')"><br><br>
    videoImageHost:<input id="devvideoImage" name="devvideoImage" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('devvideoImage')"><br><br>
</div>

<div id="test"style="color:red">
    测试环境:<br/>
    mallHost:<input id="testmall" name="testmall" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('testmall')"><br><br>
    videoHost:<input id="testvideo" name="testvideo" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('testvideo')"><br><br>
    videoImageHost:<input id="testvideoImage" name="testvideoImage" maxlength="255" style="width:500px;"/><input type="button" value="更新" onclick="dophp('testvideoImage')"><br><br>
</div>



</body>
</html>