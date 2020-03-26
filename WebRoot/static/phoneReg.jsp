<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>注册</title>
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

      </li>
    </ul>
    

  </div>
</div>

<div class="layui-container fly-marginTop">
  <div class="fly-panel fly-panel-user" pad20>
    <div class="layui-tab layui-tab-brief" lay-filter="user">
      <ul class="layui-tab-title">
        <li><a href="${pageContext.request.contextPath}/login">登入</a></li>
        <li class="layui-this">注册</li>
      </ul>
      <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
        <div class="layui-tab-item layui-show">
          <div class="layui-form layui-form-pane">
            <form  >
              <div class="layui-form-item">
                <label for="L_email" class="layui-form-label">手机号</label>
                <div class="layui-input-inline">
                  <input type="text" id="user" name="user" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                 <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
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
                <label for="L_username" class="layui-form-label">昵称</label>
                <div class="layui-input-inline">
                  <input type="text" id="L_username" name="username" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label for="L_pass" class="layui-form-label">密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_pass" name="password" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">6到16个字符</div>
              </div>
              <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">确认密码</label>
                <div class="layui-input-inline">
                  <input type="password" id="L_repass" name="rePassword" required lay-verify="required" autocomplete="off" class="layui-input">
                </div>
              </div>
             
               
              </div>
              <div class="layui-form-item">
                  <!-- <button class="layui-btn"  id='reg_bt' type="button">立即注册</button> -->
                    <button class="layui-btn" lay-filter="reg" lay-submit>立即注册</button>
              </div>
             <div class="layui-form-item">
               
                    <a href="${pageContext.request.contextPath}/static/reg.jsp" > 其它方式注册</a>
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
<script charset="utf-8">

layui.config({
  version: "3.0.0"
  ,base: '${pageContext.request.contextPath}/static/res/mods/'
}).extend({
  fly: 'index'
}).use(['form'],function(){

 var $ = layui.$

  ,forms=layui.form
  //手机号验证
function checkPhone(phone){ 
    
      if(!(/^1[3456789]\d{9}$/.test(phone))){ 
       
        return false; 
    } else{
     return true; }
}
forms.on('submit(reg)',function(data){

     if(!checkPhone($('#user').val()))
     {
     layer.msg("手机号码有误请重新输入");
     return false;
     }
      
      //  password rePassword
          if(data.field.yzm.length!=6){
           layer.msg("验证码有误");
          return false;
        }
        if(data.field.password){
       
        if(data.field.password!=data.field.rePassword)
            {
            layer.msg('两次输入密码不一致');
            }else if(data.field.password.length<6){
               layer.msg('密码最少6位');
            }else{
            
              $.ajax({
       type:'post',
       url:'${pageContext.request.contextPath}/PhoneRegister',
       data:  data.field,
       success:function (res){
        res=JSON.parse(res);
      if(res.code==0){
       layer.msg(res.msg);
       setTimeout(function(){
       window.location.href="${pageContext.request.contextPath}/login" 
    //  location.reload();
       }, 800);
      }else{
        layer.msg(res.msg);
      }
     }
      
     });
            
            
            }
       
        }
        
      return false;
    
     });   
 


$('#reg_bt').click(function(){
// user L_username  L_pass L_repass  

});
var now=0;
//一分钟
var jg=1000*60;
var sondF=false;

$('#phoneNum').click(function(){
  
  
   ///PhoneVerify
 
   if(!sondF)
   {
   var user=$('#user').val();
   if(checkPhone(user))
     { now=Date.now();
      sondF=true;
      
         $.ajax({
       type:'post',
       url:'${pageContext.request.contextPath}/PhoneVerify',
       data:  "PhoneNumbers="+user,
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
      
        layer.msg("手机号码有误请重新输入");
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