<%@page import="com.ldr.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ page  import="com.ldr.bean.*" %> 
<%
User user_your=(User)request.getAttribute("user");
ArrayList<Article> list=(ArrayList)request.getAttribute("list");
 User user=(User)request.getSession().getAttribute("user");
 ArrayList<UserComment> userComments=( ArrayList<UserComment>)request.getAttribute("userComments");

 %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>用户主页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/res/css/global.css">
  <link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />  
</head>
<body style="margin-top: 65px;">

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

<div class="fly-home fly-panel" style="background-image: url();">
   <img src="<%=user_your.getImg()%>">
  
  <h1>
    <%=user_your.getName() %>
   <!--  <i class="iconfont icon-nan"></i> -->
    <!-- <i class="iconfont icon-nv"></i>  -->
  
    <!--
    <span style="color:#c00;">（管理员）</span>
    <span style="color:#5FB878;">（社区之光）</span>
    <span>（该号已被封）</span>
    -->
  </h1>

  

  <p class="fly-home-info">
       <!-- <i class="iconfont icon-kiss" title="飞吻"></i><span style="color: #FF7200;">66666 飞吻</span>  -->
    <i class="iconfont icon-shijian"></i><span><%= DateUtil.FormatYMD(user_your.getRegTime()) %> 加入</span>
    <i class="iconfont icon-chengshi"></i><span>来自<%=user_your.getAddress() %></span>
  </p>

  <p class="fly-home-sign"><%=user_your.getSignature() %></p>



</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md6 fly-home-jie">
      <div class="fly-panel">
        <h3 class="fly-panel-title"><%= user_your.getName() %> 的帖子</h3>
        <ul class="jie-row">
        <% for(int i=0;i<list.size();i++){
           Article article=list.get(i);
         %>
          <li>
           <!--   <span class="fly-jing">精</span>-->
            <a href="${pageContext.request.contextPath}/detail?itemid=<%= article.getId()%>" class="jie-title"> <%= article.getTitle() %></a>
            <i><%= DateUtil.FormatYMD(article.getCreationDate().getTime()) %></i>
            <em class="layui-hide-xs"> <%= article.getViews() %>阅/<%= article.getReply() %>答</em>
          </li>
         <% } %>
          <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><i style="font-size:14px;">没有发表任何求解</i></div> -->
        </ul>
      </div>
    </div>
    
    <div class="layui-col-md6 fly-home-da">
      <div class="fly-panel">
        <h3 class="fly-panel-title"><%= user_your.getName() %> 最近的回答</h3>
        <ul class="home-jieda">
        <% for(int i=0;i<userComments.size();i++){ 
            UserComment userComment=userComments.get(i);
            if(userComment.getTitle()!=null){
        %>
          <li>
          <p>
         
              在<a href="${pageContext.request.contextPath}/detail?itemid=<%=userComment.getArticleid() %>" target="_blank"><%=userComment.getTitle() %></a>中回答：
          </p>
          <div class="home-dacontent">
               <%=userComment.getCommentcontent() %>
          </div>
        </li>
          <%} } %>
   
        
          <!-- <div class="fly-none" style="min-height: 50px; padding:30px 0; height:auto;"><span>没有回答任何问题</span></div> -->
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

  $('.home-dacontent').each(function(){
    var othis = $(this), html = othis.html();
    othis.html(fly.content(html));
  });
  });
</script>

</body>
</html>