var baseURL = "http:127.0.0.1:8080/springboot-web/";



//ajax全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false,
    beforeSend: function () {
        //ajax请求之前
        $.loading.show();
    },
    complete: function () {
        //ajax请求完成，不管成功失败
        $.loading.hide();
    },
    error: function () {
        //ajax请求失败
        $.loading.hide();
    }
});






