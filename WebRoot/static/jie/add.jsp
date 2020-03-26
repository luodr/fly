<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ page  import="com.ldr.bean.*" %> 
<%
User user=(User)request.getSession().getAttribute("user");
if(user==null){
response.sendRedirect(request.getContextPath()+"/login");
}
 %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>发表帖子</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/res/css/global.css">
  <link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />  
</head>
<body>
<div class="fly-header layui-bg-black">
  <div class="layui-container">
    <a class="fly-logo" href="${pageContext.request.contextPath}/">
          <img src="${pageContext.request.contextPath}/static/res/images/logo.png"  style="width: 135px;height: 37px;" >
    </a>
    <ul class="layui-nav fly-nav layui-hide-xs">
    <!--    <li class="layui-nav-item layui-this">
        <a href="${pageContext.request.contextPath}/"><i class="iconfont icon-jiaoliu"></i>交流</a>
      </li>-->
     
    </ul>
    
    <ul class="layui-nav fly-nav-user">
      
      <!-- 未登入的状态 -->
      <% if(user==null){ %>
      <li class="layui-nav-item">
        <a class="iconfont icon-touxiang layui-hide-xs" href="${pageContext.request.contextPath}/login"></a>
      </li>
      <li class="layui-nav-item">
        <a href="${pageContext.request.contextPath}/login">登入</a>
      </li>
      <li class="layui-nav-item">
        <a href="${pageContext.request.contextPath}/register">注册</a>
      </li>
     
         <% } %>
         <% if(user!=null){ %>
      <!-- 登入后的状态 -->
 
      <li class="layui-nav-item">
        <a class="fly-nav-avatar" href="javascript:;">
          <cite class="layui-hide-xs"><%= user.getName() %></cite>
       
          <img src="<%= user.getImg() %>">
        </a>
        <dl class="layui-nav-child">
          <dd><a href="${pageContext.request.contextPath}/set"><i class="layui-icon">&#xe620;</i>基本设置</a></dd>
          <dd><a href="${pageContext.request.contextPath}/message"><i class="iconfont icon-tongzhi" style="top: 4px;"></i>我的消息</a></dd>
          <dd><a href="${pageContext.request.contextPath}/home"><i class="layui-icon" style="margin-left: 2px; font-size: 22px;">&#xe68e;</i>我的主页</a></dd>
          <hr style="margin: 5px 0;">
          <dd><a href="${pageContext.request.contextPath}/loginOut" style="text-align: center;">退出</a></dd>
        </dl>
      </li>
       <% } %>
    </ul>
  </div>
</div>

<div class="layui-container fly-marginTop">
  <div class="fly-panel" pad20 style="padding-top: 5px;">
    <!--<div class="fly-none">没有权限</div>-->
    <div class="layui-form layui-form-pane">
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li class="layui-this">发表新帖<!-- 编辑帖子 --></li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <form action="${pageContext.request.contextPath}/add" method="post">
              <div class="layui-row layui-col-space15 layui-form-item">
                <div class="layui-col-md3">
                  <label class="layui-form-label">所在专栏</label>
                  <div class="layui-input-block">
                    <select lay-verify="required" name="class" > 
                   
                      <option value="Node.js">Node.js</option> 
                      <option value="Java">Java</option> 
                      <option value="JavaScript">JavaScript</option> 
                      <option value="Mysql">Mysql</option> 
                    </select>
                  </div>
                </div>
                <div class="layui-col-md9">
                  <label for="L_title" class="layui-form-label">标题</label>
                  <div class="layui-input-block">
                    <input type="text" id="L_title" name="title" required lay-verify="required" autocomplete="off" class="layui-input">
                    <!-- <input type="hidden" name="id" value="{{d.edit.id}}"> -->
                  </div>
                </div>
              </div>
              <div class="layui-row layui-col-space15 layui-form-item " id="LAY_quiz">
             <!--    <div class="layui-col-md3">
                  <label class="layui-form-label">所属产品</label>
                  <div class="layui-input-block">
                    <select name="project">
                      
                      <option value="Node.js">Node.js</option>
                      <option value="Java">Java</option>
                      <option value="Vue">Vue</option>
                      <option value="Mysql">Mysql</option>
                      <option value="javaScript">javaScript</option>
                    </select>
                  </div>
                </div> -->
            
              </div>
              <div class="layui-form-item layui-form-text">
                <div class="layui-input-block">
                  <textarea id="L_content" name="content" required lay-verify="required" placeholder="详细描述" class="layui-textarea fly-editor" style="height: 260px;"></textarea>
                </div>
              </div>
              <!--
              <div class="layui-form-item">
                <div class="layui-inline">
                  <label class="layui-form-label">悬赏飞吻</label>
                  <div class="layui-input-inline" style="width: 190px;">
                    <select name="experience">
                      <option value="20">20</option>
                      <option value="30">30</option>
                      <option value="50">50</option>
                      <option value="60">60</option>
                      <option value="80">80</option>
                    </select>
                  </div>
                  <div class="layui-form-mid layui-word-aux">发表后无法更改飞吻</div>
                </div>
              </div>
             -->
              <div class="layui-form-item">
                <button class="layui-btn" lay-filter="addArticle" lay-submit>立即发布</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="fly-footer" >
  <p><a href="http://fly.layui.com/" target="_blank"  >sinlo社区</a> 2019 &copy; <a href="http://www.layui.com/" target="_blank"  >layui.com 出品</a></p>
 
</div>

<script src="${pageContext.request.contextPath}/static/res/layui/layui.js" charset="gbk"></script>
<script>
layui.cache.page = 'jie';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '/lt_56/static/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '/lt_56/static/res/mods/'
}).extend({
  fly: 'index'
}).use(['fly', 'face','form'], function(){
  var $ = layui.$
  ,fly = layui.fly,forms =layui.form;
  //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
  
 
  forms.on('submit(addArticle)',function(data){

     if(data){
     $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/add',
     data:data.field,
     success:function (res){
     res=JSON.parse(res);
     if(res.code==0){
      setTimeout(function(){ //发布成功
     //itemId
     //detail?itemid=351
 
       window.location.href='${pageContext.request.contextPath}/detail?itemid='+res.itemId;
      }, 800);
     }
      layer.msg(res.msg);
     }
     
     });
     
     
     
     }
return false;

  })

    
  
});

//addArticle
</script>

</body>
</html>