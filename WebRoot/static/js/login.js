$(window).load(function(){


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
    body.css( "overflow-y","hidden");
    showDiv($("#mdLogin"));
    //遮掩居中
     showDiv($("#zyan"));

});
function showDiv(obj){
   
    center(obj);
    /*$(window).scroll(function(){
     center(obj);
    });*/
    $(window).resize(function(){
     center(obj);
    }); 
   }
//居中
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

