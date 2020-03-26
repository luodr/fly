<%@page import="com.ldr.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ page  import="com.ldr.bean.*" %> 


<%
User user=(User)request.getSession().getAttribute("user");
ArrayList<UserComment> list=(ArrayList<UserComment>)request.getAttribute("list");

if(user==null){
response.sendRedirect(request.getContextPath()+"/login");
return ;
}
 %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>我的消息</title>
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
          <img src="${pageContext.request.contextPath}/static/res/images/logo.png"  style="width: 135px;height: 37px;"  >
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
    
           <img src="<%=user.getImg()%>">
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


<div class="layui-container fly-marginTop fly-user-main">
    <ul class="layui-nav layui-nav-tree layui-inline" lay-filter="user">
    <li class="layui-nav-item">
      <a href="${pageContext.request.contextPath}/home">
        <i class="layui-icon">&#xe609;</i>
        我的主页
      </a>
    </li>
    <li class="layui-nav-item">
      <a href="${pageContext.request.contextPath}/userIndex">
        <i class="layui-icon">&#xe612;</i>
        用户中心
      </a>
    </li>
    <li class="layui-nav-item ">
      <a href="${pageContext.request.contextPath}/set">
        <i class="layui-icon">&#xe620;</i>
        基本设置
      </a>
    </li>
    <li class="layui-nav-item layui-this">
      <a href="${pageContext.request.contextPath}/message">
        <i class="layui-icon">&#xe611;</i>
        我的消息
      </a>
    </li>
  </ul>

  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  <div class="site-tree-mobile layui-hide">
    <i class="layui-icon">&#xe602;</i>
  </div>
  <div class="site-mobile-shade"></div>
  
  
  <div class="fly-panel fly-panel-user" pad20>
	  <div class="layui-tab layui-tab-brief" lay-filter="user" id="LAY_msg" style="margin-top: 15px;">
	    <button class="layui-btn layui-btn-danger" id="all_qk"  >清空全部消息</button>
	    <div  id="LAY_minemsg" style="margin-top: 10px;">
        <!--<div class="fly-none">您暂时没有最新消息</div>-->
        <ul class="mine-msg">
        <% for(int i=0;i<list.size();i++){
        UserComment comment=list.get(i);
         %>
          <li data-id="<%= comment.getId()%>">
            <blockquote class="layui-elem-quote">
              <a href="${pageContext.request.contextPath}/home?username=<%=comment.getReplyName()%>" target="_blank"><cite><%=comment.getReplyName()%></cite></a>回答了您<a target="_blank" href="${pageContext.request.contextPath}/detail?itemid=<%=comment.getArticleid()%>"><cite><%=comment.getCommentcontent() %></cite></a>
            </blockquote>
            <p><span><%= DateUtil.FormatYMDHm(comment.getCreationdate().getTime()) %></span><!-- <a href="javascript:;" class="layui-btn layui-btn-small layui-btn-danger fly-delete">删除</a> --></p>
          </li>
        <%} %>
        </ul>
      </div>
	  </div>
	</div>

</div>


<div class="fly-footer" >
  <p><a href="http://fly.layui.com/" target="_blank"  >sinlo社区</a> 2019 &copy; <a href="http://www.layui.com/" target="_blank"  >layui.com 出品</a></p>
 
</div>

<script src="${pageContext.request.contextPath}/static/res/layui/layui.js"></script>
<script>
layui.cache.page = 'user';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '../../res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '${pageContext.request.contextPath}/static/res/mods/'
}).extend({
  fly: 'index'
}).use(['fly', 'face','form'], function(){
  var $ = layui.$
  ,fly = layui.fly;

$('#all_qk').click(function(){
          $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/message',
     data:'all=true',
     success:function (res){
     location.reload();
     }
    

     
     });
});
});
//
</script>

</body>
</html>