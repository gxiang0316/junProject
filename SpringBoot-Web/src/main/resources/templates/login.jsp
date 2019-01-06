<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>登录</title>

    <link rel="stylesheet" href="../static/css/bootstrap-4.0.0.css">
    <link rel="stylesheet" href="../static/css/font-awesome4.7.0.css">

    <style>
        html{
            width:100%;
            height: 100%;
        }
        body{
            width:100%;
            height: 100%;
            background:url('../static/imgs/body-background.jpg') no-repeat;
            background-size: cover;
        }
        .divCenter{
            width: 300px;
            height: 300px;
            position: absolute;
            left: 50%;
            top: 50%;
            margin: -150px 0 0 -150px;
            background-color: rgba(229, 229, 229, 0.64);
        }
        /*修改placeholder属性提示文字的颜色*/
        /*input::input-placeholder{
            !**有些资料显示需要写，有些显示不需要，但是在编辑器webstorm中该属性不被识别 *!
            color:  #000 !important;
        }*/
        ::-webkit-input-placeholder { /* WebKit browsers */
            color: rgb(191, 0, 79) !important;
            font-size: 12px;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            /*color: rgba(255, 0, 0, 0.21);*/
            color: #000 !important;
            font-size: 12px;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            /*color: rgba(255, 0, 0, 0.21);*/
            color: #000 !important;
            font-size: 12px;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            /*color: rgba(255, 0, 0, 0.21);*/
            color: #000 !important;
            font-size: 12px;
        }
        a{
            text-decoration:none;
        }
        /**a标签鼠标移入移出是的样式*/
        a:hover{
            text-decoration:none;/*下掉下划线*/
            color: #0c0c0c;/*鼠标移入时的颜色*/
        }
        /* 覆写自带的样式 */
        .modal-dialog {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 3;
            margin: auto;
            -webkit-transform: translate(-50%, -50%) !important;
            -moz-transform: translate(-50%, -50%) !important;
            -ms-transform: translate(-50%, -50%) !important;
            -o-transform: translate(-50%, -50%) !important;
            transform: translate(-50%, -50%) !important;
        }
    </style>

</head>
<body>
contextPath === ${request.contextPath}
<div class="divCenter">
    <a class="pull-right" href="#" style="font-size: 18px;margin-right: 5px;" data-toggle="modal" data-target="#registerModal">注册</a>
    <div class="container-fluid" style="margin-top: 18px">
        <div class="h3 pt-3 pb-3" style="text-align: center">Gordon Web</div>
        <form class="row m-auto" action="login">
            <div class="col-12 input-group form-group">
                <!--<span class="input-group-prepend"><i class="fa fa-envelope-o fa-fw"></i></span>-->
                <input id="username" type="text" placeholder="用户名" class="form-control" style="box-shadow: none"/>
            </div>
            <div class="col-12 form-group" style="margin-bottom: 4px;">
                <input id="password" type="password" placeholder="密码" class="form-control" style="box-shadow: none"/>
            </div>
            <div class="col-12 form-check">
                <input id="remeberMe" type="checkbox" class="form-check-input" style="margin-top: 7px;margin-left: 0px"/>
                <label class="form-check-label" for="remeberMe" style="margin-left: 16px">记住我</label>
                <a style="float:right" href="#">忘记密码？</a>
            </div>
            <div class="col-12 form-group mt-3 mb-1">
               <button id="login" class="col-12 btn btn-success" type="submit" style="box-shadow: none">登录</button>
            </div>
            <div class="col-12 form-group" style="text-align: center;">
               <span id="login_msg" style="display:none;color: blue;font-weight:bold">用户名或密码错误</span>
            </div>
        </form>
    </div>
    <!--<button id="showLoading" class="btn-success btn">测试loading</button>-->
</div>

<!-- 注册模态框 -->
<div class="modal fade" id="registerModal">
    <div class="modal-dialog" style="width: 350px;">
        <div class="modal-content">
            <!-- 模态框头部 -->
            <div class="modal-header">
                <h4 class="modal-title" style="width: 100%;text-align: center;" >用户注册</h4>
                <button type="button" class="close pull-right" data-dismiss="modal" style="padding: 0 5px 5px 0;">&times;</button>
            </div>

            <!-- 模态框主体 -->
            <div class="modal-body">
                <form class="row m-auto" action="${request.contextPath}/register">
                    <div class="col-12 input-group form-group">
                        <!--<span class="input-group-prepend"><i class="fa fa-envelope-o fa-fw"></i></span>-->
                        <input id="registerusername" type="text" placeholder="用户名" class="form-control" style="box-shadow: none"/>
                    </div>
                    <div class="col-12 form-group">
                        <input id="registerpassword" type="password" placeholder="密码" class="form-control" style="box-shadow: none"/>
                    </div>
                    <div class="col-12 form-group" style="margin-bottom: 4px;">
                        <input id="reregisterpassword" type="password" placeholder="确认密码" class="form-control" style="box-shadow: none"/>
                    </div>
                    <div class="col-12 form-group mt-3 mb-1">
                        <button id="register" class="col-12 btn btn-success" type="submit" style="box-shadow: none">注册</button>
                    </div>
                </form>
            </div>

            <!-- 模态框底部 -->
            <!--<div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
            </div>-->
        </div>
    </div>
</div>

<script src="../static/js/jquery-3.2.1.js"></script>
<script src="../static/js/popper-1.12.9.js"></script>
<script src="../static/js/bootstrap-4.0.0.js"></script>
<script src="../static/js/module/loading.js"></script>
<script src="../static/js/module/common.js"></script>

<script>
    /*$(function () {

    });*/
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






    });


</script>



</body>
</html>