<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page  import="com.ldr.bean.*" %> 
<%
User user=(User)request.getSession().getAttribute("user");
if(user==null){
System.out.println("跳转");
response.sendRedirect(request.getContextPath()+"/login");
return ;
}
 %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>帐号设置</title>
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
    <li class="layui-nav-item  layui-this">
      <a href="${pageContext.request.contextPath}/set">
        <i class="layui-icon">&#xe620;</i>
        基本设置
      </a>
    </li>
    <li class="layui-nav-item ">
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
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title" id="LAY_mine">
        <li class="layui-this" lay-id="info">我的资料</li>
        <li lay-id="avatar">头像</li>
      
        <li lay-id="pass">邮箱激活</li>
     
      </ul>
      <div class="layui-tab-content" style="padding: 20px 0;">
        <div class="layui-form layui-form-pane layui-tab-item layui-show">
          <form method="post" >
            <div class="layui-form-item">
              <label for="L_email" class="layui-form-label">账号</label>
              <div class="layui-input-inline">
                <input type="text" id="L_email" name="user" value="<%= user.getUser() %>" required lay-verify="required"  disabled="disabled"  autocomplete="off"  class="layui-input">
              </div>
             
            </div>
            <div class="layui-form-item">
              <label for="L_username" class="layui-form-label">昵称</label>
              <div class="layui-input-inline">
                <input type="text" id="L_username" name="name"  value="<%= user.getName() %>"  required lay-verify="required" autocomplete="off"  class="layui-input">
              </div>
              
            </div>
            <div class="layui-form-item">
              <label for="L_city" class="layui-form-label">城市</label>
              <div class="layui-input-inline">
                <input type="text" id="L_city" name="address" autocomplete="off" value="<%=user.getAddress() %>" class="layui-input">
              </div>
            </div>
            <div class="layui-form-item layui-form-text">
              <label for="L_sign" class="layui-form-label">签名</label>
              <div class="layui-input-block">
                <textarea placeholder="随便写些什么刷下存在感" id="L_sign"    name="sign" autocomplete="off" class="layui-textarea" style="height: 80px;"><%=user.getSignature() %></textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <button class="layui-btn" key="set-mine"    lay-filter="setInfo" lay-submit>确认修改</button>
            </div>
          </div>
          
          <div class="layui-form layui-form-pane layui-tab-item">
            <div class="layui-form-item">
              <div class="avatar-add">
                <p>建议尺寸168*168，支持jpg、png、gif，最大不能超过50KB</p>
                <button type="button" class="layui-btn upload-img">
                  <i class="layui-icon">&#xe67c;</i>上传头像
                </button>
                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg">
                <span class="loading"></span>
              </div>
            </div>
          </div>
          
          <div class="layui-form layui-form-pane layui-tab-item">
            <form action="${pageContext.request.contextPath}/sendMail" method="post">
            <%if(user.getMail()!=null&&!user.getMail().equals("")) {%>
            您已绑定邮箱<%=user.getMail() %>，你可以重新绑定：
               <%}%>
              <div class="layui-form-item">
                <label for="L_nowpass" class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" id="jhEmal" name="mail" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
                <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">验证码</label>
                <div class="layui-input-inline">
                  <input type="text" id="yzm" name="yzm" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux"><button type="button"  id="phoneNum" class="layui-btn" style="position: relative;top:-7px; display: inline-block;
    height: 38px;
    line-height: 38px;
    padding: 0 18px;
    background-color: #009688;
    color: #fff;
    white-space: nowrap;
    text-align: center;
    font-size: 14px;
    border: none;
    border-radius: 2px;
    cursor: pointer;
    -moz-user-select: none;
    -webkit-user-select: none;
    -ms-user-select: none;">获取验证码</button></div>
              </div>
              <div class="layui-form-item">
                <button class="layui-btn" key="set-mine" lay-filter="mail" lay-submit>确认绑定</button>
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

<script src="${pageContext.request.contextPath}/static/res/layui/layui.js" charset="GBK"></script>
<script>
layui.cache.page = 'user';
layui.cache.user = {
  username: '游客'
  ,uid: -1
  ,avatar: '${pageContext.request.contextPath}/static/res/images/avatar/00.jpg'
  ,experience: 83
  ,sex: '男'
};
layui.config({
  version: "2.0.0"
  ,base: '${pageContext.request.contextPath}/static/res/mods/'
}).extend({
  fly: 'index'
}).use(['fly', 'face','form'],function(){


 var forms=layui.form;
var $ = layui.$;
forms.on('submit(setInfo)',function(data){
      
     if(data){
     $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/setInfo',
     data:data.field,
     success:function (res){
     res=JSON.parse(res);
     if(res.code==0){
      setTimeout(function(){
      location.reload();
      }, 800);
     }
      layer.msg(res.msg);
     }
     
     });
     
     
     
     }
return false;

  })
  
  
  forms.on('submit(mail)',function(data){
      
     if(data&&checkMail($('#jhEmal').val())){
     $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/mail',
     data:data.field,
     success:function (res){
     if(res)
    { res=JSON.parse(res);
     if(res.code==0){
      setTimeout(function(){
      location.reload();
      }, 800);
     }
      layer.msg(res.msg);
     }
    else{
     layer.msg("绑定失败!过一会再尝试");
    
    }
     }
     });
     
     
     
     }else{
     layer.msg("邮箱不正确！");
     
     }
return false;

  })
  
  
  
  
    //邮箱验证
function checkMail(phone){ 
    
      if(!( /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(phone))){ 
       
        return false; 
    } else{
     return true; }
}
  var now=0;
//一分钟
var jg=1000*20;
var sondF=false;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
$('#phoneNum').click(function(){
  
  
   ///PhoneVerify
 
   if(!sondF)
   {
   var jhEmal=$('#jhEmal').val();
   if(checkMail(jhEmal))
     { now=Date.now();
      sondF=true;
      
         $.ajax({
       type:'get',
       url:'${pageContext.request.contextPath}/mail',
       data:  "mail="+jhEmal,
       success:function (res){
        res=JSON.parse(res);
      if(res.code==0){
       layer.msg(res.msg);
      
      }else{
       sondF=false;
       layer.msg(res.msg);
      }
     }
      
     });
      
      
      }else{
      
        layer.msg("邮箱有误请重新输入");
        }
   }
});

setInterval(function(){

var v=now+jg-Date.now();

if(!sondF)  return ;
  if(v<=0){
   $('#phoneNum').text("获取验证码");
     sondF=false;
  }else{
   $('#phoneNum').text(Math.ceil(v/1000)+ "秒后再试");
  }
}, 1000);




});
  
  
  
  
  
 
  
  
  
  
  
  
  
  
  

</script>

</body>
</html>