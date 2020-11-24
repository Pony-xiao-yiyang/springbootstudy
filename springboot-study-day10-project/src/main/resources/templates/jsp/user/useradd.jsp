<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/13
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>layuiAdmin 网站用户 iframe 框</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
</head>
<body>

<form class="layui-form" lay-filter="cms-user-add-table" id="cms-user-add-table" style="padding: 20px 0 0 0;">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">手机号码</label>--%>
        <%--<div class="layui-input-inline">--%>
            <%--<input type="text" name="phone" lay-verify="phone" placeholder="请输入号码" autocomplete="off" class="layui-input">--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-inline">
            <input type="text" name="nickname" lay-verify="required" placeholder="请输入昵称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">头像</label>
        <div class="layui-input-inline">
            <input type="text" name="headerImage" lay-verify="required" placeholder="请上传图片" autocomplete="off" class="layui-input" >
        </div>
        <%--<button style="float: left;" type="button" class="layui-btn" id="layuiadmin-upload-useradmin">上传图片</button>--%>
    </div>
    <div class="layui-form-item" pane="">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-block">
            <input type="checkbox" name="roles" lay-skin="primary" title="管理员" value="1">
            <input type="checkbox" name="roles" lay-skin="primary" title="普通用户" checked="" value="2">
            <input type="checkbox" name="roles" lay-skin="primary" title="开发者"  value="3">
        </div>
    </div>
    <%--<div class="layui-form-item" lay-filter="sex">--%>
        <%--<label class="layui-form-label">选择性别</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="radio" name="sex" value="男" title="男" checked>--%>
            <%--<input type="radio" name="sex" value="女" title="女">--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="cms-user-add" id="cms-user-add" value="确认">
    </div>
</form>

<script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'form', 'upload'], function(){
        var $ = layui.$
            ,form = layui.form
            ,upload = layui.upload ;

        upload.render({
            elem: '#layuiadmin-upload-useradmin'
            ,url: layui.setter.base + 'json/upload/demo.js'
            ,accept: 'images'
            ,method: 'get'
            ,acceptMime: 'image/*'
            ,done: function(res){
                $(this.item).prev("div").children("input").val(res.data.src)
            }
        });
    })
</script>
</body>
</html>