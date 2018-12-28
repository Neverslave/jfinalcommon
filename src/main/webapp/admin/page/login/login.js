layui.config({
	base : "js/"
}).use(['form','layer'],function() {
    var form = layui.form(),
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //video背景
    $(window).resize(function () {
        if ($(".video-player").width() > $(window).width()) {
            $(".video-player").css({
                "height": $(window).height(),
                "width": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        } else {
            $(".video-player").css({
                "width": $(window).width(),
                "height": "auto",
                "left": -($(".video-player").width() - $(window).width()) / 2
            });
        }
    }).resize();




    //登录按钮事件
    form.on("submit(login)", function (data) {
        $.ajax({
            url: "/login/doLogin",
            dataType: "json",
            data: data.field,
            method:"post",
            success: function (ret) {
                if (ret.state === "ok") {
                    window.location.href = '/admin/page/';
                    return false;
                }


                if (ret.state === "fail") {
                    layer.msg(ret.msg, {
                        shift: 6
                        , shade: 0.3
                        , time: 2500
                        , offset: "165px"
                        , closeBtn: 1
                        , shadeClose: true
                    }, function () {
                        updateCaptcha();

                    })

                }

                //validator层校验失败
                if (ret.userNameMsg) {
                    $('#responseMsg').html(ret.userNameMsg)

                }
                if (ret.passwordMsg) {
                    $('#responseMsg').html(ret.passwordMsg)

                }
                if (ret.captchaMsg) {
                    updateCaptcha();
                    $('#responseMsg').html(ret.captchaMsg)

                }
            },
            error: function (ret) {
                alert("暂时无法登录")

            },
            complete:function (ret) {


            }
        })



    });
    function updateCaptcha() {
        $("#captcha").attr("src", "/login/captcha?v=" + Math.random());
        $('#captchaInput').val("");

    }

  $("#captcha").click(updateCaptcha);

    });
