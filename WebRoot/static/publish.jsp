<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");

%>

<!DOCTYPE html>
<html   >
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src=" ${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/themes/default/default.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/plugins/code/prettify.css" />
	<script charset="utf-8" src="${pageContext.request.contextPath}/static/js/kindeditor-all.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/static/lang/zh-CN.js"></script>
	<script charset="utf-8" src="${pageContext.request.contextPath}/static/plugins/code/prettify.js"></script>
	
        <script src="${pageContext.request.contextPath}/static/js/js.js"></script>
        <link  href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet"/>
   
    <link  href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet"/>
    <title>发表</title>
    
</head>
<body >
<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content1"]', {
				cssPath : './static/plugins/code/prettify.css',
				uploadJson : './static/upload_json.jsp',
				fileManagerJson : './static/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>
    
<div class="top fontWhite">

        <div class="container">
             
            <div class="row">
                    <div class="col-md-5">
                                <a href="/" class="titleA"> <img src="@{/img/img.jpg}" alt="sin" class="img-circle" height="40px" width="40px">
                          <span>没有钱的小咸鱼</span></a>
                          <span class="pull-right visible-sm visible-xs" id="menuButton">
                          <span class="glyphicon glyphicon-list" height="50px"></span></span>
                    </div>          
                
                    <div class="col-md-7 hidden-xs hidden-sm" >
                            <div class="row">
                                        <div class="col-ms-4 ">                    
                                        </div>
                                    <div class="col-md-2"><a  href="@{/}" class="titleA">首页</a></div>
                                <div class="col-md-2" ><span id="SendarticlesMS">写博客</span></div>
                                <div class="col-md-2"><a href="/comment/leaveMeAMessage" class="titleA">留言</a></div>
                                <div class="col-md-2"><a href="https://GitHub.com/sinlo0806"  target="view_window" class="titleA">GitHub</a></div>
                                  </div>              
                  </div>
                  
                  </div>
            </div>
</div>

      <div class="container top65">
            <div class="row">
                       <!--左则-->
                    <div class="col-xs-12 bg" style="height: 1500px;">
                      <form name="example" method="post" action="${pageContext.request.contextPath}/publish" enctype="multipart/form-data">
                          <div class="form-group">
                              <label for="title">文章标题:</label>
                              <input type="text" name="title" class="form-control" id="title" placeholder="Email">
                          </div>
                           <div class="form-group">
                              <label for="title">文章技术类型:</label>
                             <select name="type" class="form-control">
                             <option>node.js</option>
                             <option>java</option>
                             <option>JavaScript</option>
                              <option>html</option>
                                <option>mysql</option>
                              <option>c#</option>
                                <option>c++</option>
                                  <option>前端</option>
                                <option>后端</option>
                                   <option>计算机基础</option>
                             </select>
                          </div>
                          
                          
                          <div class="form-group">
                              <label for="Introduction">文章简介:</label>
                              <textarea id="Introduction" name="introduction" class="form-control" rows="3"></textarea>
                          </div>
                          <div class="form-group">
                              <label for="imgFile">封面图片:</label>
                              <input type="file" name="imgFile1" class="form-control" id="imgFile" >
                          </div>
                          <div class="form-group">
                              <label for="content">文章内容:</label>
                              	<textarea name="content1" cols="100" rows="8" style="width:100%;height:700px;"></textarea>
                          </div>

                        <input type="submit" value="发布">
                      </form>
                           
                    </div>   
          
                  </div>
                 
                     
          </div>
          <div class="down fontWhite"><center><p >isin.win</p></center></div>


    <div class="col-xs-12  xslogin" id="xslogin">
        <span class="glyphicon glyphicon-chevron-left translation" id="closeXslogin"></span>

        <h3 >登录</h3>
        <span class="erropwd" style="color: red; display: none">账号或密码错误!</span>
        <div class="m20">
            <form class="form-horizontal" id="xsfm" method="POST" action="login/Verification">
                <div class="form-group">
                    <label class="sr-only" for="xsUserName">用户名</label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                        <input type="text" class="form-control" name="user" id="xsUserName" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="sr-only" for="xsUserPassword">密码</label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                        <input type="password" class="form-control" name="password"  id="xsUserPassword" placeholder="请输入密码">
                    </div>
                </div>
                <button type="button" id="xsButton" class="btn btn-success btn-block" style="position: relative;top: 10px">登录</button>
            </form>
        </div>
    </div>

    <!--菜单-->
    <div id="menu" class="indent ">
        <a  href="@{/}" class="titleA">  <p>
            首页</p></a>
        <hr  class="menuHr"/>
        <p id="SendarticlesXS">写博客</p>
        <hr  class="menuHr" />
        <a href="/comment/leaveMeAMessage" class="indent titleA" >  <p > 留言</p></a>
        <hr class="menuHr" />
        <a href="https://GitHub.com/sinlo0806" class="indent titleA" target="view_window">   <p> GitHub</p></a>
        <hr  class="menuHr"/>
    </div>

    <div id="mdLogin">
        <p id="MdloginP"><span class="glyphicon glyphicon-remove translation" ></span></p>
        <h3 >登录</h3>
        <div class="m20">
            <span class="erropwd" style="color: red; display: none">账号或密码错误!</span>
            <form class="form-horizontal" id="fm" method="POST"  action="@{login/Verification}">
                <div class="form-group">
                    <label class="sr-only" for="mdUserName">用户名</label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                        <input type="text" class="form-control" name="user" id="mdUserName" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="form-group">
                    <label class="sr-only" for="mdUserPassword">密码</label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                        <input type="password" class="form-control" name="password" id="mdUserPassword" placeholder="请输入密码">
                    </div>
                </div>
                <button type="button" id="mdButton" class="btn btn-success btn-block" style="position: relative;top: 10px">登录</button>
            </form>
        </div>
    </div>
    <div id="zyan"></div>



</body>

</html>
<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>