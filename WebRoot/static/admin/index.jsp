
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<%@page import="com.ldr.bean.User"%>
<% 
User user=(User)request.getSession().getAttribute("user");
 %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理帖子</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/admin/res/css/layui.css">
  <link rel="Shortcut Icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />  
</head>
<body>
<div style="height: 100vh;">
    <div >
        <ul class="layui-nav" style="border-radius: 0px;">
            <li class="layui-nav-item">
              <a href="${pageContext.request.contextPath}/">前台首页</a>
            </li>
            <li class="layui-nav-item">
         <form  method="post" id="form_search">
            <input type="text" id="search_value"  name ="search" style="height:30px;
           padding-left: 12px;
           background-color: #424652;
           background-color: rgba(255,255,255,.05);
           border: none 0;
           color: #fff;
           color: rgba(255,255,255,.5);
            font-size: 12px;" placeholder="搜索">
         </form>
            </li>
       
            <li class="layui-nav-item" style="position: relative;float: right">
              <a href=""><img src="<%= user.getImg()%>" class="layui-nav-img">我</a>
              <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/set" target="_blank">修改信息</a></dd>
                <dd><a href="${pageContext.request.contextPath}/loginOut">退了</a></dd>
              </dl>
            </li>
          </ul>
    </div>
<div style="position: absolute; top:50px;bottom: 0px; background: #393D49; float: left; ">
    <ul class="layui-nav layui-nav-tree"   style="border-radius: 0px;" lay-filter="test">
        <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
         <li class="layui-nav-item  layui-nav-itemed"><a href="">全部帖子</a></li>
          <li class="layui-nav-item">
            <a href="javascript:;" va>模板管理</a>
            <dl class="layui-nav-child">
              <dd><a  value="Node.js" class="ManagerTypt">Node.js</a></dd>
              <dd><a value="Java" class="ManagerTypt">Java</a></dd>
            <dd><a value="JavaScript" class="ManagerTypt"> JavaScript</a></dd>
             <dd><a value="Mysql" class="ManagerTypt">Mysql</a></dd>
            </dl>
          </li>
          <li class="layui-nav-item">
            <a href="javascript:;">举报管理</a>
            <dl class="layui-nav-child">
               <dd><a  class="reportType" value="Node.js">Node.js</a></dd>
              <dd><a  class="reportType"  value="Java">Java</a></dd>
            <dd><a  class="reportType" value="JavaScript">JavaScript</a></dd>
             <dd><a  class="reportType" value="Mysql" >Mysql</a></dd>
            </dl>
          </li>
            <li class="layui-nav-item">
            <a href="javascript:;">待审核</a>
            <dl class="layui-nav-child">
               <dd><a  class="audit" value="Node.js">Node.js</a></dd>
              <dd><a  class="audit"  value="Java">Java</a></dd>
            <dd><a  class="audit" value="JavaScript">JavaScript</a></dd>
             <dd><a  class="audit" value="Mysql" >Mysql</a></dd>
            </dl>
          </li>
          <!-- <li class="layui-nav-item"><a href="">产品</a></li>
          <li class="layui-nav-item"><a href="">大数据</a></li> -->
        </ul>
</div>
<div style="float:left;margin-left:220px">

    <table class="layui-hide" id="test" lay-filter="test"></table>
 

 
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-xs" lay-event="see">查看</a>

  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="reportSc">
 <a class="layui-btn layui-btn-xs" lay-event="see">查看</a>
  <a class="layui-btn layui-btn-xs" lay-event="report">查看举报原因</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script type="text/html" id="auditSc">
 <a class="layui-btn layui-btn-xs" lay-event="see">查看</a>
 <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="sh">通过审核</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
</div>

</div>

<script src="${pageContext.request.contextPath}/static/admin/res/layui.js" charset="GBK" ></script>
<script charset="UTF-8">



layui.use(['laydate', 'laypage', 'layer', 'table', 'carousel', 'upload', 'element'], function(){
  var laydate = layui.laydate //日期
  ,laypage = layui.laypage //分页
  ,layer = layui.layer //弹层
  ,table = layui.table //表格
  ,carousel = layui.carousel //轮播
  ,upload = layui.upload //上传
  ,element = layui.element //元素操作

  
 var $ = layui.$;
  
  //监听Tab切换
  element.on('tab(demo)', function(data){
    layer.tips('切换了 '+ data.index +'：'+ this.innerHTML, this, {
      tips: 1
    });
  });
  tableInfo(' ${pageContext.request.contextPath}/items');
  
  $('.ManagerTypt').click(function(){
  
      tableInfo(' ${pageContext.request.contextPath}/items?type='+$(this).attr('value'));
  });
  $('.reportType').click(function(){
   tableReport(' ${pageContext.request.contextPath}/reportItems?type='+$(this).attr('value'));
  });
  
    $('.audit').click(function(){
   tableAudit(' ${pageContext.request.contextPath}/audit?type='+$(this).attr('value'));
  });
  
  function tableInfo(url){
  table.render({
    elem: '#test'
    ,url:url
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
      title: '提示'
      ,layEvent: 'LAYTABLE_TIPS'
      ,icon: 'layui-icon-tips'
    }]    ,page: true
    
    ,method:'post'
    ,title: '用户数据表'
    ,cols: [[ //表头
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
      ,{field: 'title', title: '标题', width:300}
      ,{field: 'name', title: '作者', width: 200, sort: true, totalRow: true}
      ,{field: 'type', title: '类型', width:100, sort: true}
      ,{field: 'views', title: '访客', width: 80, sort: true, totalRow: true}
      ,{field: 'reply', title: '评论', width:150, sort: true} 
      ,{fixed: 'right', width: 165, align:'center', toolbar: '#barDemo'}
    ]]
 
  });
  }
   function tableReport(url){
  table.render({
    elem: '#test'
    ,url:url
     ,page: true
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
      title: '提示'
      ,layEvent: 'LAYTABLE_TIPS'
      ,icon: 'layui-icon-tips'
    }]
    ,method:'post'
    ,title: '用户数据表'
    ,cols: [[ //表头
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
      ,{field: 'title', title: '标题', width:300}
      ,{field: 'name', title: '作者', width: 200, sort: true, totalRow: true}
      ,{field: 'type', title: '类型', width:100}
      ,{field: 'report', title: '举报数', width:150, sort: true} 
      ,{fixed: 'right', width: 300, align:'center', toolbar: '#reportSc'}
    ]]
    
  });
  }
     //审核
    function tableAudit(url){
  table.render({
    elem: '#test'
    ,url:url
     ,page: true
    ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
    ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
      title: '提示'
      ,layEvent: 'LAYTABLE_TIPS'
      ,icon: 'layui-icon-tips'
    }]
    ,method:'post'
    ,title: '用户数据表'
    ,cols: [[ //表头
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
      ,{field: 'title', title: '标题', width:300}
      ,{field: 'name', title: '作者', width: 200, sort: true, totalRow: true}
      ,{field: 'type', title: '类型', width:100}
      ,{fixed: 'right', width: 300, align:'center', toolbar: '#auditSc'}
    ]]
    
  });
  }
  
  
  //头工具栏事件
  table.on('toolbar(test)', function(obj){
    var checkStatus = table.checkStatus(obj.config.id);
    switch(obj.event){
      case 'getCheckData':
        var data = checkStatus.data;
        layer.alert(JSON.stringify(data));
      break;
      case 'getCheckLength':
        var data = checkStatus.data;
        layer.msg('选中了：'+ data.length + ' 个');
      break;
      case 'isAll':
        layer.msg(checkStatus.isAll ? '全选': '未全选');
      break;
      
      
      
      //自定义头工具栏右侧图标 - 提示
      case 'LAYTABLE_TIPS':
        layer.alert('这是工具栏右侧自定义的一个图标按钮');
      break;
    };
  });
  
  //监听行工具事件
  table.on('tool(test)', function(obj){
    var data = obj.data;
    //console.log(obj)
    if(obj.event === 'del'){
    layer.confirm('真的删除行么', function(index){
        //obj.del(); //删除对应行（tr）的DOM结构
     //   layer.close(index);
        //向服务端发送删除指令
       $.ajax({
     type:'post',
     url:'${pageContext.request.contextPath}/delete',
     data:'articleId='+data.id,
     success:function (res){
     res=JSON.parse(res);
     if(res.code==0){
     obj.del(); //删除对应行（tr）的DOM结构
     layer.close(index);
     }
      layer.msg(res.msg);
     }});
     });  
     
   
        
   
    } else if(obj.event === 'edit'){
      layer.prompt({
        formType: 2
        ,value: data.email
      }, function(value, index){
        obj.update({
          email: value
        });
        layer.close(index);
      });
    } else if(obj.event === 'see'){
 
   window.open("${pageContext.request.contextPath}/detail?itemid="+ data.id,'_blank'); 
    }else if(obj.event ==='sh'){
    
     layer.confirm('确定要通过审核吗？', function(index){

       $.ajax({
     type:'get',
     url:'${pageContext.request.contextPath}/audit',
     data:'articleId='+data.id,
     success:function (res){
     res=JSON.parse(res);
     if(res.code==0){
     obj.del(); //删除对应行（tr）的DOM结构
     layer.close(index);
     }
      layer.msg(res.msg);
     }});
     });  
    
    }
    
    
    
  });

  //分页
  laypage.render({
    elem: 'pageDemo' //分页容器的id
    ,count: 100 //总页数
    ,skin: '#1E9FFF' //自定义选中色值
    ,skip: true //开启跳页
    ,jump: function(obj, first){
 
      if(!first){
        layer.msg('第'+ obj.curr +'页', {offset: 'b'});
      }
    }
  });
  
  //上传
  upload.render({
    elem: '#uploadDemo'
    ,url: '' //上传接口
    ,done: function(res){
      console.log(res)
    }
  });
  

  
 $("#form_search").submit(function (data) {
  
   if($('#search_value').val())
    {
     $('.layui-this').removeClass('layui-this');
   tableInfo("${pageContext.request.contextPath}/adminSearch?search="+$('#search_value').val());
    }
    
    //返回false
    return false;
  }); 
});

layui.use('element', function(){
  var element = layui.element;
  
  //一些事件监听
  element.on('tab(demo)', function(data){
    console.log(data);
  });
});




</script> 
</body>
</html>