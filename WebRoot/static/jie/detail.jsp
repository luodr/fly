<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@ page  import="com.ldr.bean.*"   %>
<%@ page  import="com.ldr.util.DateUtil"   %>
<%
Article article=(Article) request.getAttribute("article");
ArrayList<Integer> likeList=(ArrayList<Integer>)request.getAttribute("likeList");
ArrayList<Comment> comments=(ArrayList<Comment>)request.getAttribute("comments");  
User user=(User)request.getSession().getAttribute("user");
ArrayList<Article> views=(ArrayList) request.getAttribute("views");
Map<Integer,Boolean> map=new HashMap<Integer,Boolean> ();
for(int i=0;i<likeList.size();i++){
map.put(likeList.get(i), true);
}
 %>
 
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title><%=article.getTitle() %></title>
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

<div class="layui-hide-xs">
  <div class="fly-panel fly-column">
    <div class="layui-container">
      <ul class="layui-clear">
        <li class="layui-hide-xs"><a href="${pageContext.request.contextPath}/">首页</a></li> 
   <li value="Node.js"><a href="${pageContext.request.contextPath}/search?type=Node.js">Node.js</a></li> 
      <li value="Java"><a href="${pageContext.request.contextPath}/search?type=Java">Java</a></li> 
      <li value="JavaScript"><a href="${pageContext.request.contextPath}/search?type=JavaScript">JavaScript</a></li> 
      <li value="Mysql"><a href="${pageContext.request.contextPath}/search?type=Mysql">Mysql</a></li> 
      <li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block"><span class="fly-mid"></span></li> 
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
</div>

<div class="layui-container">
  <div class="layui-row layui-col-space15">
    <div class="layui-col-md8 content detail">
      <div class="fly-panel detail-box">
        <h1><%= article.getTitle() %></h1>
        <div class="fly-detail-info">
        <% if(article.getAudit()==0) {%>
        <span class="layui-badge">审核中</span> 
        <%} %>
          <div class="fly-admin-box" data-id="<%=article.getId()%>">
          <% if(user!=null&&user.getUser().equals(article.getUserName())) {%>
            <span class="layui-btn layui-btn-xs jie-admin1" type="del">删除</span>
               <% }%>
      
          </div>
           
          <span class="fly-list-nums"> 
            <a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> <%=article.getReply() %></a>
            <i class="iconfont" title="人气">&#xe60b;</i>  <%=article.getViews() %>
          </span>
        </div>
        <div class="detail-about">
          <a class="fly-avatar" href="${pageContext.request.contextPath}/home?name=<%=article.getName() %>">
            <img src="<%= article.getImg() %>">
          </a>
          <div class="fly-detail-user">
            <a href="${pageContext.request.contextPath}/home?name=<%=article.getName() %>" class="fly-link">
              <cite><%=article.getName() %></cite>
            
            </a>
            <span><%=DateUtil.FormatYMD(article.getCreationDate().getTime()) %></span>
          </div>
          <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
              <% if(user!=null&&user.getUser().equals(article.getUserName())) {%>
         <!--    <span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>--> 
      
                <% }%>
                   <br>
          </div>
        </div>
        <div class="detail-body photos">
          <%= article.getContent() %>
        
        </div>
        <i style="position: relative;float:right;cursor:pointer;" class="jb">举报</i>
     
      </div>

      <div class="fly-panel detail-box" id="flyReply">
        <fieldset class="layui-elem-field layui-field-title" style="text-align: center;">
          <legend>回帖</legend>
        </fieldset>

        <ul class="jieda" id="jieda">
        <!--  
          <li data-id="111" class="jieda-daan">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="">
                <img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">
              </a>
              <div class="fly-detail-user">
                <a href="" class="fly-link">
                  <cite>贤心</cite>
                  <i class="iconfont icon-renzheng" title="认证信息：XXX"></i>
                  <i class="layui-badge fly-badge-vip">VIP3</i>              
                </a>
                
                <span>(楼主)</span>
            
              </div>

              <div class="detail-hits">
                <span>2017-11-30</span>
              </div>

              <i class="iconfont icon-caina" title="最佳答案"></i>
            </div>
            <div class="detail-body jieda-body photos">
              <p>香菇那个蓝瘦，这是一条被采纳的回帖</p>
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan zanok" type="zan">
                <i class="iconfont icon-zan"></i>
                <em>66</em>
              </span>
              <span type="reply">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
              <div class="jieda-admin">
                <span type="edit">编辑</span>
                <span type="del">删除</span>
           
              </div>
            </div>
          </li>
          -->
          <% for(int i=0;i<comments.size();i++){ 
          Comment comment=comments.get(i);
                    %>
          <li  data-id="<%=comment.getId()%>"  data-comment="<%=comment.getId()%>" data-article="<%=article.getId()%>">
            <a name="item-1111111111"></a>
            <div class="detail-about detail-about-reply">
              <a class="fly-avatar" href="${pageContext.request.contextPath}/home?name=<%=comment.getName() %>">
                <img src="<%= comment.getImg() %>" alt=" ">
              </a>
              <div class="fly-detail-user">
                <a href="${pageContext.request.contextPath}/home?name=<%=comment.getName() %>" class="fly-link">
                  <cite> <%= comment.getName()%></cite>       
                </a>
              </div>
              <div class="detail-hits">
                <span><%= DateUtil.FormatYMDHm(comment.getCreationdate().getTime()) %></span>
              </div>
            </div>
            <div class="detail-body jieda-body photos">
            <%= comment.getCommentcontent() %>
            </div>
            <div class="jieda-reply">
              <span class="jieda-zan" type="zan">
                 <% 
                 Object cId=map.get(comment.getId());
                 if(cId!=null){
                  %>
                 <i class="iconfont icon-zan" style="color:#c00"></i>
              <%
              }else{
               %>
                 <i class="iconfont icon-zan"></i>
               <%} %>
                <em><%=comment.getLike()%></em>
                 
              </span>
              <span type="reply" class="reply_bt" value="<%=comment.getName() %>">
                <i class="iconfont icon-svgmoban53"></i>
                回复
              </span>
              <div class="jieda-admin">
               <% 
               //如果是评论者自己   有权删除
               if(user!=null&&comment.getUsername().equals(article.getUserName())) {%>
               <font value="<%=comment.getId() %>" class="delete_C"  style="cursor: pointer;">删除</font> 
                  <% }%>
              </div>
            </div>
          </li>
          <%
          }
           %>
        </ul>
        
        <div class="layui-form layui-form-pane">
          <form method="get">
            <div class="layui-form-item layui-form-text">
              <a name="comment"></a>
              <div class="layui-input-block">
                <textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>
              </div>
            </div>
            <div class="layui-form-item">
              <input type="hidden" name="articleId" value="<%=article.getId() %>">
               <input type="hidden" name="zz" value="<%=article.getName() %>">
                 <input type="hidden" name="responderName" value="" id="responderName">
              <button class="layui-btn"  lay-filter="comment" lay-submit>提交回复</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="layui-col-md4">
      <dl class="fly-panel fly-list-one">
        <dt class="fly-panel-title " >访问排行</dt>
            <%
        for(int i=0;i<views.size();i++){
        Article article1=views.get(i);
         %>
        <dd>
          <a href="${pageContext.request.contextPath}/detail?itemid=<%= article1.getId() %>"><%=article1.getTitle() %></a>
          <span><i class="iconfont icon-pinglun1"></i> <%= article1.getReply() %></span>
        </dd>
         <%
       }
         %>
      </dl>

    

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
  ,avatar: '${pageContext.request.contextPath}/static/res/images/avatar/00.jpg'
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
  //如果你是采用模版自带的编辑器，你需要开启以下语句来解析。
  
  $('.detail-body').each(function(){
    var othis = $(this), html = othis.html();
    othis.html(fly.content(html));
  });
 var forms=layui.form;
  var url='${pageContext.request.contextPath}/reply';
  forms.on('submit(comment)',function(data){

     if(data){
     $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/reply',
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

   $(".jb").click(function(){
   layer.prompt({
  formType: 0,
  value: '',
  title: '请输入举报原因',
  area: ['800px', '350px'] //自定义文本域宽高
}, function(value, index, elem){
 
  
       $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/report',
     data:  'articleId='+<%=article.getId()%>+'&value='+value,
     success:function (res){
     res=JSON.parse(res);
      layer.msg(res.msg);
     }
     
     });
   
  layer.close(index);
});

})
    $(".jie-admin1").click(function(){
      layer.confirm('确认删除该贴么？', function(index){
     
           $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/delete',
     data:  'articleId='+<%=article.getId() %>,
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
        
        
        
      });
    
     });   
     
     $('.reply_bt').click(function(){
    
     $("#responderName").val($(this).attr('value'));
     });
  
     $('.delete_C').click(function(){
     
      $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/deletecomment',
     data:  'articleId='+<%=article.getId() %>+'&commentID='+$(this).attr('value'),
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
      
     });
  
  
});
</script>

</body>
</html>