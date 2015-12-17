<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ include file="/WEB-INF/views/inc/header.jsp"%>
<c:set var="rootpath" value="${CONTEXT_PATH}/video"/>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<%@include file="/WEB-INF/views/inc/_css.jsp" %>
	<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/source/css/imgcut/imgareaselect-default.css"/>
	<link rel="stylesheet" type="text/css" href="${CONTEXT_PATH}/source/css/progressBar.css"/>
	<%@ include file="/WEB-INF/views/inc/_scripts.jsp" %>
	<script type = "text/javascript" charset="utf-8" src="${CONTEXT_PATH}/source/js/plus/jquery.imgareaselect.pack.js"></script>
	<script type = "text/javascript" charset="utf-8" src="${CONTEXT_PATH}/source/js/ajaxfileupload.js"></script>
</head>
<body>

<div class="container-fluid">
    <form:form class="form-horizontal" commandName="video" modelAttribute="video" data-widget="validator" 
    	data-item-class="form-group" data-item-error-class="has-error" data-explain-class="j-explain" 
    	role="form" action="${rootpath}/${video!=null?'update':'save'}">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                	<c:choose>
                		<c:when test="${video!=null}">
                			编辑[${video.id}]
                			<input type = "hidden" name = "id" value = "${video.id}" />
                 		</c:when>
                		<c:otherwise>新建</c:otherwise>
                	</c:choose>
                </h3>
            </div>
            <div class="panel-body ">
            	<span style="color: red;"><form:errors path="*" /></span>
            	<input type="hidden" name="id" value="" />
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="j-email">视频名称</label>
                    <div class="col-sm-6">
                        <input class="form-control " id="j-name" value="<c:if test="${video!=null}">${video.name }</c:if>" name="title" required type="text" placeholder="视频名称">
                    </div>
                    <div class="col-sm-4 control-label j-explain" style="text-align:left;font-style:italic;font-size:12px;color:green;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="j-select">视频描述</label>
                    <div class="col-sm-6">
                    	<textarea  class="form-control" id="j-desc" name="desc" required  placeholder="视频描述"><c:if test="${video!=null}">${video.desc}</c:if></textarea>
                    </div>
                    <div class="col-sm-4 control-label j-explain" style="text-align:left;font-style:italic;font-size:12px;color:green;">
                    </div>
                </div>
                <label style = "margin-left:200px;">激活</label>
               	<label style = "margin-left:27px;">
               		<input name = "active" type = "radio" value = "1" <c:if test="${video==null||video.active==true}">checked</c:if>/> 是
               		<input name = "active" type = "radio" value = "0" <c:if test="${video!=null&&video.active==false}">checked</c:if> /> 否
               	</label>
               	<br/>
               	<label style = "margin-left:200px;">视频</label>
               	<label style = "margin-left:27px;">
               		<input type = "file" name = "up_video" id="up_video" value="选择视频" onchange="javascript:v.upload.video();" <c:if test="${video!=null}">disabled</c:if>></input>
               	</label>
              	<div id="progress">
				  <div id="title">
				  	<span id="text">上传进度</span>
				  	<div id="close" onclick= "javascript:$('#progress').hide();">X</div>
				  </div>
				  <div id="progressBar">
				  		<div id="uploaded"></div>
				  </div>
				  <div id="info"></div>
				</div>
               		<div id ="uploaded_video" style="<c:if test="${video==null}">display:none;</c:if>margin-left:255px;">
               			<c:if test="${video!=null}">
               				<input type="button" onclick="javascript:v.del(this);" value="${video.video }   删除" video_name="${video.video }" upload_asset_id="${video.uploadAssetId }" encode_asset_id = "${video.encodeAssetId}"></input>
               			</c:if>
               		</div>
               	<br/>
               	<label style = "margin-left:200px;">图片</label>
               	<label style = "margin-left:27px;">
               		<input type = "file" name = "up_big_img" id="up_big_img" onchange="javascript:v.upload.img_cover();" value="选择图片"></input>
               	</label>
           		<div id = "show_big_img" style = "margin-left:255px;">
            		<div>
            			<img alt="" src="" id = "cover_img_big"></img></div>
            		<div id="cover_div_small"></div>
            		<div ><input type="button" style="width:70px;" id = "cut_big_img_div" onclick="javascript:v.upload.img_cut();" value= "裁剪" ></input></div>
            		<div><img style="<c:if test="${video==null}">display:none;</c:if>width:120px;height:120px;"
            		 src="<c:if test="${video!=null}">${video.imgMin}</c:if>" id = "cuted_img"></img></div>
            		
           		</div>
           		<input type="hidden" name = "video_id" required value="<c:if test="${video!=null}">${video.video}</c:if>"></input>
           		<input type="hidden" name = "src_img_id" <c:if test="${video==null}">required</c:if> ></input>
           		<input type="hidden" name = "cut_img_id" <c:if test="${video==null}">required</c:if> ></input>
           		<input type="hidden" name = "upload_asset_id" <c:if test="${video==null}">required</c:if> ></input>
           		<input type="hidden" name = "encode_asset_id" <c:if test="${video==null}">required</c:if> ></input>
               		
            </div>
        </div>
        <div class="clearfix" style="margin-bottom:10px;">
            <div class="pull-right">
            	<button class="btn btn-default btn-lg " type="reset" onclick="history.go(-1);">返回</button>&nbsp;&nbsp;
                <button class="btn btn-default btn-lg " type="reset">重置</button>&nbsp;&nbsp;
                <button class="btn btn-primary btn-lg " type="submit">确认</button>
            </div>
        </div>
    </form:form>
</div>

<script type="text/javascript" language="javascript">
	
	seajs.use(['$','widget'],function($,Widget){
	    //表单验证
	    Widget.autoRenderAll();
	});
var ctx = "${ctx}";
</script>
<script type = "text/javascript" charset="utf-8" src="${CONTEXT_PATH}/source/js/file_upload.js"></script>
</body>
</html>