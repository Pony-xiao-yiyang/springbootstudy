<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/4
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>登入 - layuiAdmin</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/login.css" media="all">
</head>
<body>

<!-- action：
          form表单提交的地址
     method
          form表单提交的方法
  -->
<form action="login" method="post" class="layadmin-user-login layadmin-user-display-show" id="login-form" style="display: none;">

    <div class="layadmin-user-login-main">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>RIMI图书管理系统</h2>
            <p>good good study。 day day up</p>
        </div>
        <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
                <input type="text" name="username" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input">
            </div>
            <div class="layui-form-item">
                <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
                <input type="password" name="password" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input">
            </div>


            <div class="layui-form-item"  style="height: 30px">

                    <input type="checkbox" name="remembersad" lay-skin="primary" title="记住密码" >
                    <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>

            </div>
            <div class="layui-form-item">
                <!--  如果修改为表单，则需要手动关闭表单的提交功能，才可以使用自定义的提交 -->
                <!-- 方案一 -->
                <!-- <input type="button" id="login-btn" class="layui-btn layui-btn-fluid" lay-filter="LAY-user-login-submit"  value="登 入"/> -->
                <!-- 方案二 -->
                <input type="button" id="login-btn" class="layui-btn layui-btn-fluid" value="登 入"/>

            </div>
            <div class="layui-trans layui-form-item layadmin-user-login-other">
                <label>社交账号登入</label>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>

                <a href="reg.html" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
            </div>
        </div>

    </div>
</form>

<div class="layui-trans layadmin-user-login-footer">

    <p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>
    <p>
        <span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
        <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
        <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
    </p>
</div>


</div>


<script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>


<script>

    //  开启layui模块
    layui.use(['form','layer'],function() {
        // 表单模块入口
        var form = layui.form;
        // 弹出层模块入口
        var layer = layui.layer;
        // jquery 入口
        var $ = layui.$;

        $("#login-btn").click(function () {

            $.ajax({
                url:"${pageContext.request.contextPath}/login",
                type:"post",
                // 一键序列化表单的输入控件的值
                data: $("#login-form").serialize(),
                success:function(result) {
                    layer.msg(result.msg,{
                        time:1000
                    },function(){
                    //    消息显示结束之后
                        if(result.code == 2000) {
                            window.location.href = "${pageContext.request.contextPath}/index";
                        } else {
                            // 刷新页面
                            window.location.reload();
                        }
                    })
                }
            })
        });
    })
</script>
</body>
</html>