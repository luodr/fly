$(window).load(function(){
    var body=  $(document.body);


    $('.searchbg').click(function(){
        var ss=$('#exampleInputAmount').val();
        if(ss!=''&&ss!=null&&ss!=undefined){
        window.location.href='/article/'+ss+'/find/1';
        }else{
            
        }
      // window
        //alert(ss);
    });
    //下拉菜单
    var menu=$("#menu");
    //菜单按钮
    $('#menuButton').click(function(){
        if(menu.css("display")==="none")
        {
            $("#menu").slideDown("slow");
        }else{

            $("#menu").slideUp("slow");
        }
        //
        //
    })


    openslide($('#FLinksbtn'),$("#FLinks"));
    function openslide(btn, key) {
        btn.click(function(){

            if(key.css("display")==="none")
            {

                key.slideDown("slow");
            }else{

                key.slideUp("slow");
            }
            //
            //
        })
    }
    //导航条随滚动条滚动
    $('.top').smohanfixednav(0,999);
    $('#menu').smohanfixednav(50,999);



//图片自适应
    var cententImg= parseInt($('#cententImg').css("width"));
    var cententImgLength=$('#cententImg img');
    for(var i=0;i<cententImgLength.length;i++){
        var imgSize=cententImgLength.get(i);
        if(cententImg<=(imgSize.width)){
            imgSize.width=(cententImg*0.99)+"";
        }
    }



    //block


    var mdButton=$("#mdButton");
    var fdate=$('#fm');
    sb(mdButton,'post','/login/Verification',fdate,'/edit/article',iserro);
    var xsButton=$("#xsButton");
    var xsfdate=$('#xsfm');
    sb(xsButton,'post','/login/Verification',xsfdate,'/edit/article',iserro);
    function sb(obj,type,url,fdata,successhrdf,fn) {
        obj.click(function () {
            $.ajax({
                type: type,   //提交的方法
                url:url, //提交的地址
                data:fdata.serialize(),// 序列化表单值
                async: false,
                error: function(request) {  //失败的话
                    alert("Connection error");
                },
                success: function(data) {  //成功
                    fn(data);
                }
            });
        })
    }
    function iserro(data) {
        if(data=='y')
        {
            window.location.href='/edit/article';
        }else {
            // alert("密码错误");  //就将返回的数据显示出来
            $(".erropwd").css("display","block")
        }
    }
    var body=  $(document.body);
    //登录
    //小屏幕登录
    var xslogin=$("#xslogin");
    //小屏幕关闭
    var closeXslogin=$("#closeXslogin");
    //小屏幕打开
    var SendarticlesXS=  $("#SendarticlesXS");

    //大屏幕登录打开
    var SendarticlesMS=$("#SendarticlesMS");
    //大屏幕登录
    var mdLogin=$("#mdLogin");
    //大屏幕关闭
    var MdloginP=$("#MdloginP span");

    sb(SendarticlesXS,'post','/login/IsLogin',xsfdate,'/edit/article',isLogin);
    sb(SendarticlesMS,'post','/login/IsLogin',xsfdate,'/edit/article',isLoginMD);


    function isLogin(data) {
        if(data=='y'){
            window.location.href='/edit/article';
        }else{
            xslogin.addClass("visible-xs  visible-sm ");
            xslogin.css("top",$(document).scrollTop());
            body.css( "overflow-y","hidden");
            $("#menu").slideUp("slow");

        }
    }
    function isLoginMD(data) {
        if(data=='y'){
            window.location.href='/edit/article';
        }else{
            //遮掩居中
            showDiv($("#zyan"));
            $("#zyan").css("display","block");
            showDiv($("#mdLogin"));
            mdLogin.addClass("visible-md  visible-lg ");
            body.css( "overflow-y","hidden");

        }
    }



    closeXslogin.click(function(){
        xslogin.removeClass("visible-xs  visible-sm ");
        body.css( "overflow-y","auto");
    });
    MdloginP.click(function(){
        mdLogin.removeClass("visible-md  visible-lg ");
        $("#zyan").css("display","none");
        body.css( "overflow-y","auto");
    });

});





(function ($) {
    $.fn.smohanfixednav = function (mtop, zindex) {
        var nav = $(this),
            isIE6 = 'undefined' == typeof(document.body.style.maxHeight),
            mtop = mtop,
            zindex = zindex,
            dftop = nav.offset().top - $(window).scrollTop(),
            dfleft = nav.offset().left - $(window).scrollLeft(),
            dfcss = new Array;
        dfcss[0] = nav.css("position"),
            dfcss[1] = nav.css("top"),
            dfcss[2] = nav.css("left"),
            dfcss[3] = nav.css("zindex"),
            $(window).scroll(function (e) {
                $(this).scrollTop() > dftop ? isIE6 ? nav.css({
                    position : "absolute",
                    top : eval(document.documentElement.scrollTop),
                    left : dfleft,
                    "z-index" : zindex
                }) : nav.css({
                    position : "fixed",
                    top : mtop + "px",
                    left : dfleft,
                    "z-index" : zindex
                }) : nav.css({
                    position : dfcss[0],
                    top : dfcss[1],
                    left : dfcss[2],
                    "z-index" : dfcss[3]
                })
            })
    }
})(jQuery)
function showDiv(obj){

    center(obj);
   /* $(window).scroll(function(){
        center(obj);
    });
    */
    $(window).resize(function(){
        center(obj);
    });
}

function center(obj){
    var windowWidth = document.documentElement.clientWidth;
    var windowHeight = document.documentElement.clientHeight;
    var popupHeight = $(obj).height();
    var popupWidth = $(obj).width();
    $(obj).css({
        "position": "absolute",
        "top": (windowHeight-popupHeight)/2+$(document).scrollTop(),
        "left": (windowWidth-popupWidth)/2
    });
}




