<%@page import="com.ldr.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ page  import="com.ldr.bean.*" %> 
<%@ page  import="com.ldr.dao.*" %> 
<%
  ArrayList<Article> list=(ArrayList)request.getAttribute("list");
    ArrayList<Article> views=(ArrayList)request.getAttribute("views");
  User user=(User)request.getSession().getAttribute("user");
    ArrayList<HotComment> hotUser=(ArrayList<HotComment>)request.getAttribute("hotUser");
  Integer c_page=(Integer)request.getAttribute("page");
  boolean isHot=(Boolean)request.getAttribute("hot");
    Integer count=(Integer)request.getAttribute("count");
    String xx="";
  if(isHot){
  xx="&hot=true";
  }
 %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>sinlo论坛</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="keywords" content="fly,layui,前端社区">
  <meta name="description" content="Fly社区是模块化前端UI框架Layui的官网社区，致力于为web开发提供强劲动力">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/res/layui/css/layui.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/res/css/global.css">
  <link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />  
</head>
<body >

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

<div class="fly-panel fly-column">
  <div class="layui-container">
    <ul class="layui-clear">
      <li class="layui-hide-xs layui-this"><a href="${pageContext.request.contextPath}/">首页</a></li> 
     <li><a href="${pageContext.request.contextPath}/search?type=Node.js">Node.js</a></li> 
      <li><a href="${pageContext.request.contextPath}/search?type=Java">Java</a></li> 
      <li><a href="${pageContext.request.contextPath}/search?type=JavaScript">JavaScript</a></li> 
      <li><a href="${pageContext.request.contextPath}/search?type=Mysql">Mysql</a></li> 

      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li> 
    
      <!-- 用户登入后显示 -->
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><a href="${pageContext.request.contextPath}/userIndex">我发表的贴</a></li> 
     
    </ul> 
    
    <div class="fly-column-right layui-hide-xs"> 
      <span class="fly-search"><i class="layui-icon"></i></span> 
      <a href="${pageContext.request.contextPath}/add" class="layui-btn">发表新帖</a> 
    </div> 
    <div class="layui-hide-sm layui-show-xs-block" style="margin-top: -10px; padding-bottom: 10px; text-align: center;"> 
      <a href="${pageContext.request.contextPath}/add" class="layui-btn">发表新帖</a> 
    </div> 
  </div>
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8">
    
    <!-- 
      <div class="fly-panel">
        <div class="fly-panel-title fly-filter">
          <a>置顶</a>
          <a href="#signin" class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin" style="color: #FF5722;">去签到</a>
        </div>
        <ul class="fly-list">
        
          <li>
            <a href="user/home.html" class="fly-avatar">
              <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">
            </a>
            <h2>
              <a class="layui-badge">动态</a>
              <a href="jie/detail.html">基于 layui 的极简社区页面模版</a>
            </h2>
            <div class="fly-list-info">
              <a href="user/home.html" link>
                <cite>贤心</cite>
                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                <i class="layui-badge fly-badge-vip">VIP3</i>
              </a>
              <span>刚刚</span>
              
            
              <span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
              <span class="fly-list-nums"> 
                <i class="iconfont icon-pinglun1" title="回答"></i> 66
              </span>
            </div>
            <div class="fly-list-badge">
          
            </div>
          </li>
         
        </ul>
      </div>
 -->
      <div class="fly-panel" style="margin-bottom: 0;">
        
        <div class="fly-panel-title fly-filter">
        <!--   <a href="" class="layui-this">综合</a>
          <span class="fly-mid"></span>
          <a href="">未结</a>
          <span class="fly-mid"></span>
          <a href="">已结</a>
          <span class="fly-mid"></span>
          <a href="">精华</a> -->
          <span class="fly-filter-right layui-hide-xs">
          <%if(!isHot){ %>
              <a href="${pageContext.request.contextPath}/index" class="layui-this">按最新</a>
            <span class="fly-mid"></span>
            <a href="${pageContext.request.contextPath}/index?hot=true">按热议</a>
        <%}else{ %>
            <a href="${pageContext.request.contextPath}/index" >按最新</a>
            <span class="fly-mid"></span>
            <a href="${pageContext.request.contextPath}/index?hot=true" class="layui-this">按热议</a>
        <%} %>
          </span>
        </div>

        <ul class="fly-list">          
        <%
        for(int i=0;i<list.size();i++){
         Article article=list.get(i);
         %>
           <li>
            <a href="${pageContext.request.contextPath}/home?name=<%=article.getName() %>" class="fly-avatar">
              <img src="<%= article.getImg() %>" >
            </a>
            <h2>
              <a class="layui-badge" style="width: 64px;"><%= article.getType() %></a>
              <a href="${pageContext.request.contextPath}/detail?itemid=<%= article.getId() %>"> <%= article.getTitle() %> </a>
            </h2>
            <div class="fly-list-info">
              <a href="${pageContext.request.contextPath}/home?name=<%=article.getName() %>" link>
                <cite><%= article.getName() %></cite>
                <!--
                <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                <i class="layui-badge fly-badge-vip">VIP3</i>
                -->
              </a>
              <span><%= DateUtil.FormatYMDHm(article.getCreationDate().getTime()) %></span>
              
             
              <!--<span class="layui-badge fly-badge-accept layui-hide-xs">已结</span>-->
              <span class="fly-list-nums"> 
                <i class="iconfont icon-pinglun1" title="回答"></i><%= article.getReply() %>
              </span>
            </div>
            <div class="fly-list-badge">
              <!--<span class="layui-badge layui-bg-red">精帖</span>-->
            </div>
          </li>
          <% } %>
        </ul>
      <!--   <div style="text-align: center">
          <div class="laypage-main">
            <a href="jie/index.html" class="laypage-next">更多求解</a>
          </div>
        </div> -->
 <div style="text-align: center">
          <div class="laypage-main">
          <%if(c_page>1){ %>
          <a href="${pageContext.request.contextPath}/index?page=<%=c_page-1%><%=xx%>" class="laypage-next">上一页</a>
           <% } %>
          <span class="laypage-curr"><%=c_page%></span>
               <%if(count>=(c_page+1)*10){ %>
          <a href="${pageContext.request.contextPath}/index?page=<%=c_page+1%><%=xx%>"><%=c_page+1%>
    
          <%} %>
            <%if(count>=(c_page+2)*10){ %>
          <a href="${pageContext.request.contextPath}/index?page=<%=c_page+2%><%=xx%>"><%=c_page+2%>
    
          <%} %>
           <%if(count>=(c_page+3)*10){ %>
          <a href="${pageContext.request.contextPath}/index?page=<%=c_page+3%><%=xx%>"><%=c_page+3%>
    
          <%} %>
          
            <%if(count>=(c_page+1)*10){ %>
       <a href="${pageContext.request.contextPath}/index?page=<%=c_page+1%><%=xx%>" class="laypage-next">下一页</a>
    
          <%} %>
         
          </div>
        </div>
      </div>
    </div>
    <div class="layui-col-md4">
      <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank">
        <h3 class="fly-panel-title">回贴榜</h3>
      
        <dl>
          <!--<i class="layui-icon fly-loading">&#xe63d;</i>-->
            <% for(int i=0;i<hotUser.size();i++){
        HotComment comment=hotUser.get(i);
         %>
          <dd>
            <a href="${pageContext.request.contextPath}/home?name=<%=comment.getName() %>">
                 <img src="<%=comment.getImg()%>"><cite><%=comment.getName() %></cite><i><%=comment.getCount() %>次回答</i>
            </a>
          </dd>
            <%} %>
        </dl>
      </div>

      <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title " >访问排行</dt>
        <%
        for(int i=0;i<views.size();i++){
        Article article=views.get(i);
         %>
        <dd>
          <a href="${pageContext.request.contextPath}/detail?itemid=<%= article.getId() %>"><%=article.getTitle() %></a>
          <span><i class="iconfont icon-pinglun1"></i> <%= article.getReply() %></span>
        </dd>
         <%
       }
         %>
        <!-- 无数据时 -->
        <!--
        <div class="fly-none">没有相关数据</div>
        -->
      </dl>

     
  <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title " >打赏论坛</dt>
        <form action="${pageContext.request.contextPath}/play" method="post">
        <dd>
         <input type="radio" name="das"  value="1">1元 
          <input type="radio"  name="das"  value="10">10元  
         <input type="radio"  name="das" value="100" >100元 
          <input type="radio"  name="das" value="5000000" >500万
          <button class="layui-btn" type="submit">支付宝打赏</button>
        </dd>
         
       
        </dd>
       </form>
        <!-- 无数据时 -->
        <!--
        <div class="fly-none">没有相关数据</div>
        -->
      </dl>

    </div>
  </div>
</div>

<div class="fly-footer" >
  <p><a href="http://fly.layui.com/" target="_blank"  >sinlo社区</a> 2019 &copy; <a href="http://www.layui.com/" target="_blank"  >layui.com 出品</a></p>
 
</div>
 
<script src="${pageContext.request.contextPath}/static/res/layui/layui.js"></script>
<script>
layui.cache.page = '';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '${pageContext.request.contextPath}/static/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "3.0.0"
  ,base: '${pageContext.request.contextPath}/static/res/mods/' //这里实际使用时，建议改成绝对路径
}).extend({
  fly: 'index'
}).use('fly');
</script>

</body>
</html>