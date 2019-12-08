$(window).load(function(){
    var responderemail=$("#responderemail");
    var respondername=$("#respondername");
    var responderid=$("#responderid");
    var replyToComments=$("#replyToComments");


  //回复评论
        replyComments();
        Comments();




$(".reply").click(function() {

    $("#replyInfo").css("display","none")
    $('#pinglun').insertAfter($($(this).parent().parent()));
    $('#pinglun').css("display", "block");
    // alert($(this).attr("myEmainl"));//
    //alert($(this).attr("myName"));//
    // alert($(this).attr("Myresponderid"));
    responderemail.val($(this).attr("myEmainl"));
    responderid.val($(this).attr("Myresponderid"))
    var name = $(this).attr("myName");
    respondername.val(name)
    replyToComments.val("@" + name + "   ");


});









}
);
function replyComments() {
    var userName=$("#userName");
    var Email=$("#Email");
    var http=$("#http");
    var replyInfo=$("#replyInfo");
    var replyToComments=$("#replyToComments");
    $("#replyButton").click(function () {
        var userNameVal=userName.val().replace(/(^\s*)|(\s*$)/g, "");
        var EmailVal=Email.val().replace(/(^\s*)|(\s*$)/g, "");
        var httpVal=http.val().replace(/(^\s*)|(\s*$)/g, "");
        var replyToCommentsVal=replyToComments.val().replace(/(^\s*)|(\s*$)/g, "");

        if(userNameVal==''||EmailVal==''||httpVal==''||httpVal==undefined||userNameVal==undefined||EmailVal==undefined){
            replyInfo.css("display","block")
            replyInfo.text("请填写昵称或邮箱、网址(可以是http://)")
        }else if(replyToCommentsVal==''){

            replyInfo.css("display","block")
            replyInfo.text("请重新填写回复内容");
        }else if(!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(EmailVal)){
            replyInfo.css("display","block")
            replyInfo.text("邮箱格式不正确!");
        }else{
            $("#replyForm").submit()
        }

    });
}
function Comments() {
    var userName=$("#userName1");
    var Email=$("#Email1");
    var http=$("#http1");
    var replyInfo=$("#replyInfo1");
    var replyToComments=$("#replyToComments1");
    $("#replyButton1").click(function () {
        var userNameVal=userName.val().replace(/(^\s*)|(\s*$)/g, "");
        var EmailVal=Email.val().replace(/(^\s*)|(\s*$)/g, "");
        var httpVal=http.val().replace(/(^\s*)|(\s*$)/g, "");
        var replyToCommentsVal=replyToComments.val().replace(/(^\s*)|(\s*$)/g, "");
        if(userNameVal==''||EmailVal==''||httpVal==''||httpVal==undefined||userNameVal==undefined||EmailVal==undefined){
            replyInfo.css("display","block")
            replyInfo.text("请填写昵称或邮箱、网址(可以是http://)")
        }else if(replyToCommentsVal==''){

            replyInfo.css("display","block")
            replyInfo.text("请填写评论内容!!");
        }else if(!/^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/.test(EmailVal)){
            replyInfo.css("display","block")
            replyInfo.text("邮箱格式不正确!");
        }else{

            $("#formcCmment").submit()
        }

    });
}