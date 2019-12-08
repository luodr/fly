<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
    
        <script src="${pageContext.request.contextPath}/static/js/login.js"></script>

        <link th:href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet"/>
    <link th:href="${pageContext.request.contextPath}/static/css/indexlogin.css" rel="stylesheet"/>
   
        <link th:href="${pageContext.request.contextPath}/static/css/index.css" rel="stylesheet"/>

    <title>æ³¨å</title>
</head>
<body >
          <div class="col-xs-12   visible-xs visible-sm" >
          <br/>
          <br/>
           <h3 >æ³¨å</h3>
           <span class="erropwd" style="color: red; display: none;">è´¦å·æèå¯ç éè¯¯!</span>
           <div class="m20">
              <form class="form-horizontal" action="register" method="POST" id="xsfm">
                  <div class="form-group">
                      <label class="sr-only" for="xsUserName">ç¨æ·å</label>
                      <div class="input-group">
                          <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                          <input type="text" class="form-control" name="user" id="xsUserName" placeholder="è¯·è¾å¥ç¨æ·å">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="sr-only" for="exampleInputAmount">å¯ç </label>
                      <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                        <input type="password" class="form-control" name="password" id="exampleInputAmount" placeholder="è¯·è¾å¥å¯ç ">
                      </div>
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="xsUserPassword">ç±»å</label>
                        <div class="input-group">
                            <div class="input-group-addon"><span class="glyphicon glyphicon-cog"></span></div>
                            <select class="form-control">
                                <option>æ®éç¨æ·</option>
                                <option>ç®¡çå</option>
                            </select>
                        </div>
                    </div>
              <button type="button" id="xsButton" class="btn btn-success btn-block" style="position: relative;top: 10px">ç»å½</button>
                </form>
           </div>
          </div>  
          <div id="mdLogin" class="  col-xs-3 visible-md visible-lg">
             <br/>
             <br/>
              <h3 >æ³¨å</h3>
           <div class="m20">
            <span class="erropwd" style="color: red;display: none;">è´¦å·æèå¯ç éè¯¯!</span>
              <form class="form-horizontal" action="${pageContext.request.contextPath}/register" method="POST"">
                  <div class="form-group">
                      <label class="sr-only" for="xsUserName">ç¨æ·å</label>
                      <div class="input-group">
                          <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                          <input type="text" class="form-control" name="user" id="xsUserName" placeholder="è¯·è¾å¥ç¨æ·å">
                      </div>
                  </div>
                  <div class="form-group">
                      <label class="sr-only" for="xsUserPassword">å¯ç </label>
                      <div class="input-group">
                          <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
                          <input type="password" class="form-control" name="password"  id="xsUserPassword" placeholder="è¯·è¾å¥å¯ç ">
                      </div>
                  </div>
                <div class="form-group">
                    <label class="sr-only" for="xsUserPassword">ç±»å</label>
                    <div class="input-group">
                        <div class="input-group-addon"><span class="glyphicon glyphicon-cog"></span></div>
                        <select name="type" class="form-control">
                            <option value="0">æ®éä¼å</option>
                            <option value="1">ç®¡çå</option>
                        </select>
                    </div>
                </div>
              <button type="submit"  class="btn btn-success btn-block" style="position: relative;top: 10px">ç»å½</button>
                </form>
           </div>
          </div>
          <div id="zyan"></div>
</body>

</html>