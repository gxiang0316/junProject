$(document).ready(function () {

    /*hover() 方法规定当鼠标指针悬停在被选元素上时要运行的两个函数。
            方法触发 mouseenter(当鼠标指针位于元素上方时) 和 mouseleave(鼠标移出) 事件。*/
    // $("#username").hover(function () {
    //     $(this).attr("placeholder","");
    // },function () {
    //     $(this).attr("placeholder","用户名");
    // });

    $("#username").bind("focus",function () {
        $(this).attr("placeholder","");
        $("#login_msg").hide();
    });
    $("#username").bind("blur",function () {
        $(this).attr("placeholder","用户名");
    });
    $("#password").bind("focus",function () {
        $(this).attr("placeholder","");
        $("#login_msg").hide();
    });
    $("#password").bind("blur",function () {
        $(this).attr("placeholder","密码");
    });
    $("#registerusername").bind("focus",function () {
        $(this).attr("placeholder","");
        $("#login_msg").hide();
    });
    $("#registerusername").bind("blur",function () {
        $(this).attr("placeholder","用户名");
    });
    $("#registerpassword").bind("focus",function () {
        $(this).attr("placeholder","");
        $("#login_msg").hide();
    });
    $("#registerpassword").bind("blur",function () {
        $(this).attr("placeholder","密码");
    });
    $("#reregisterpassword").bind("focus",function () {
        $(this).attr("placeholder","");
        $("#login_msg").hide();
    });
    $("#reregisterpassword").bind("blur",function () {
        $(this).attr("placeholder","确认密码");
    });
    // $modal.on('shown.bs.modal', function(event) {
    //     var $modalDialog = $modal.find('.modal-dialog');
    //     h = $modalDialog.height();
    //     w = $modalDialog.width();
    //
    //     $modalDialog.css({
    //         marginLeft: -w / 2,
    //         marginTop: -h / 2
    //     });
    // });

    // $("#showLoading").bind("click",function () {
    //     $.loading.show();
    // });

    // F5刷新会重新提交表单数据，浏览器弹窗重新提交提示框
    // $("#loginForm").bind("submit",function (event) {
    //     var url = $(this).attr("action");
    //     var method = $(this).attr("method");
    //     var formData = new FormData(this);
    //     console.log("ajax from 提交");
    //     $.ajax({
    //         url:url,
    //         type:method,
    //         data:formData,
    //         success:function (result) {
    //             var flag = result.code;
    //             if(flag == "0000"){
    //                 console.log("登录成功");
    //                 $("#login_msg").hide();
    //                 location.href = "/index";
    //             }else{
    //                 console.log("登录失败");
    //                 $("#login_msg").show();
    //             }
    //         }
    //     });
    // });


    // 点击一次 却提交了两次

    // 推荐使用此方式，不会造成F5刷新时重新提交表单数据
    $("#login").bind("click",function () {
        // var url = $("#loginForm").attr("action");
        // var method = $("#loginForm").attr("method");
        var formData = new FormData();
        formData.append("username",$("#username").val());
        formData.append("password",$("#password").val());
        formData.append("remeberMe",$("#remeberMe").is(":checked"));
        // 不要使用checked属性，高版本不准，jquery1.6版本后选中时是true ，没选中是undefined
        console.log("remeberMe = "+$("#remeberMe").is(":checked"));
        // console.log("ajax url : "+url + "   method : " + method);
        $.ajax({
            url:"login",
            type:"post",
            data:formData,// form标签不能写action method 否则会提交两次(ajax一次，form本身一次)
            dataType:"json",
            // contentType: "application/json;charset=UTF-8",
            // processData默认为true,为true时提交的data不会序列化，
            // 导致前端报Uncaught TypeError: Illegal invocation
            processData:false,
            contentType:false,
            success:function (result) {
                var flag = result.code;
                if(flag == "0000"){
                    console.log("登录成功");
                    location.href = "main.html";
                }else{
                    console.log("登录失败");
                    $("#login_msg").show().html(result.msg);
                }
                $.loading.hide();
            },
        });
        // 必须返回false，否则form或formData提交时会自动跳转页面
        return false;
    });


});