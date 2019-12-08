<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import="com.ldr.bean.*"  %>
<%
   Article article=(Article)request.getAttribute("article");
 %>
<!DOCTYPE html>
<html  >
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/article.js"></script>
        <!-- Include all compiled plugins (below), or include individual files as needed -->
        <script src="${pageContext.request.contextPath}/static/js/js.js"  ></script>

    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/static/css/login.css" rel="stylesheet"/>
    <title th:text="article.title">title</title>
</head>
<body >
    
<div class="top fontWhite">
        <div class="container">
             
            <div class="row">
                    <div class="col-md-5">
                                <a href="/" class="titleA"> <img src="@{/img/img.jpg}" class="img-circle" height="40px" width="40px">
                          <span>123</span></a>
                          <span class="pull-right visible-sm visible-xs" id="menuButton">
                          <span class="glyphicon glyphicon-list" height="50px"></span></span>
                    </div>          
                
                    <div class="col-md-7 hidden-xs hidden-sm" >
                            <div class="row">
                                        <div class="col-ms-4 ">                    
                                        </div>
                                    <div class="col-md-2"><a href="@{/}" class="titleA">首页</a></div>
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
                    <div class="col-md-9 ">
                        <div class="col-md-12 bg">
                            <br/>
                            <center><h3 th:text="${article.title}">标题</h3></center>
                            <p  >日期:<span >2020-1-1</span> <span class="pull-right" >浏览:<span > <%= article.getViews() %></span></span></p>
                            <hr />
                            <div   id="cententImg">
                   <%= article.getContent() %>
                            </div>
                            <p>上一篇:<a href=""></a></p>
                            <p >下一篇:<a href="" ></a></p>

                            <hr />
                            <span>最新评论:</span>
                            <br/>
                            <br/>
                            <p>暂无评论!</p>


                       <div >
                           <div >
                               <a  href="123"><img src="'https://s.gravatar.com/avatar/'+874471992+'?s=55'" class="img-circle pull-left "/></a>
                               <p ><a href="#" class="colorRed" >!!!!</a></p>
                               <p class="pEvaluatedown indent15" > !!!!</p>
                               <p class="bordersolid">&nbsp;<span class="pull-right reply " style="cursor:pointer;">回复</span></p>
                           </div>
                       </div>
                            <br/>
                        </div>
                        <!--发表评论-->

                            <div class="col-md-12 bg    downheight10px top15">
                                <span class="glyphicon glyphicon-pencil"></span>      <span>发表评论</span>
                                <div class="hrEvaluate"></div>
                                <span style="width: auto;color: red;margin: auto;display: none;" id="replyInfo1"></span>
                                <form class="form-horizontal" id="formcCmment" action="/comment/commentArticle" method="post">
                                    <input type="text" name="articleid"   th:value="${article.id}" style="visibility:hidden" >
                                    <div class="row">
                                        <div class="col-md-4 col-xs-12">
                                            <label class="sr-only" for="userName1">称呼:</label>
                                            <input type="text" id="userName1" name="username" class="form-control" placeholder="称呼">
                                            <br/>
                                        </div>
                                        <div class="col-md-4 col-xs-12">
                                            <label class="sr-only" for="Email1">邮箱:</label>
                                            <input type="text" id="Email1" name="email" class="form-control" placeholder="邮箱">
                                            <br/>
                                        </div>
                                        <div class="col-md-4 col-xs-12">
                                            <label class="sr-only" for="http">http://</label>
                                            <input type="text" id="http1" name="http" class="form-control" value="http://" >
                                            <br/>
                                        </div>
                                    </div>
                                    <textarea class="form-control" rows="3" name="commentcontent" id="replyToComments1"></textarea>
                                    <br/>

                                    <button type="button" id="replyButton1" class="btn btn-success center-block">提交评论</button>

                                </form>

                            </div>

                    </div>

                      <!--右侧-->        
                    <div class="col-md-3 hidden-xs hidden-sm" >
                        
                            <div class="row">
                                    <div class="col-md-1">
                                    </div>          
                                    <div class="col-md-10 " >
                                        <!--搜索框-->                        
                                         <div class="row height100 bg"  >
                                             <div class="col-xs-1"></div>
                                                <div class="col-xs-10">
                                                        <form class="form-horizontal top32">
                                                                <div class="form-group ">
                                                                  <label class="sr-only" for="exampleInputAmount">search (in dollars)</label>
                                                                  <div class="input-group">
                                                                    <input type="search" class="form-control search"  id="exampleInputAmount" placeholder="search">
                                                                    <div class="input-group-addon  search  searchbg"><span>搜索</span></div>
                                                                  </div>
                                                                </div>                                              
                                                              </form>
                                          
                                                    </div>
                                
                                         </div>



                                           <!--右侧下面-->
                                  <div class="row theRight bg " style="text-indent: 25px;">
                                      <br>
                                      <h4  style=" text-align: center;    margin: 0 auto;   color: olivedrab;">点击排行</h4>
                                      <div th:each="click:${clicks}" >
                                          <p><a href="@{'/article/'+${click.id}+'/blog'}" th:text="${click.title}"> </a></p>
                                      </div>

                                  
                                    </div>
                                  </div>
                                
                                  </div>
                                

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
    <a href="@{/}" class="titleA">  <p>
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
        <form class="form-horizontal" id="fm" method="POST" th:action="@{login/Verification}">
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


<div class="row " style="display: none;" id="pinglun">
    <div class="col-md-12 bg  downheight10px">

         <span style="width: auto;color: red;margin: auto;display: none;" id="replyInfo"></span>
        <form class="form-horizontal" action="/comment/replyComment" method="post" id="replyForm">
            <input type="hidden" name="responderid"  id="responderid" >
            <input type="hidden" name="responderemail" id="responderemail" >
            <input type="hidden" name="respondereanme" id="respondername" >
            <input type="text" name="articleid"   th:value="${article.id}" style="visibility:hidden" >
            <div class="row">
                <div class="col-md-4 col-xs-12">
                    <label class="sr-only" for="userName">称呼:</label>
                    <input type="text" id="userName" name="username" class="form-control" placeholder="您的称呼">
                    <br/>
                </div>
                <div class="col-md-4 col-xs-12">
                    <label class="sr-only" for="Email">邮箱:</label>
                    <input type="text" id="Email" name="email" class="form-control" placeholder="您的邮箱">
                    <br/>
                </div>
                <div class="col-md-4 col-xs-12">
                    <label class="sr-only" for="http">http://</label>
                    <input type="text" id="http" name="http" class="form-control" value="http://" >
                    <br/>
                </div>
            </div>
            <textarea class="form-control" rows="3" name="commentcontent" id="replyToComments"></textarea>
            <br/>

            <button type="button" id="replyButton" class="btn btn-success center-block">回复评论</button>

        </form>

    </div>
</div>
</body>

</html>