<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2020/7/7
  Time: 11:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <title>CRM系统用户</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layuiadmin/style/admin.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">

                <div class="layui-inline">
                    <label class="layui-form-label">用户名</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">昵称</label>
                    <div class="layui-input-block">
                        <input type="text" name="nickname" placeholder="请输入" autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">
                    <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="cms-user-search">
                        <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                    </button>
                </div>
            </div>
        </div>

        <div class="layui-card-body">
            <%-- 头部工具栏 --%>
            <div style="padding-bottom: 10px;">
                <%-- data-type 事件名称 --%>
                <shiro:hasPermission name="user:del">
                    <button class="layui-btn layuiadmin-btn-useradmin" data-type="batchdel">删除</button>
                </shiro:hasPermission>

                <shiro:hasPermission name="user:add">
                    <button class="layui-btn layuiadmin-btn-useradmin" data-type="add">添加</button>
                </shiro:hasPermission>


            </div>

            <table id="cms-user-table" lay-filter="cms-user-table"></table>
            <script type="text/html" id="imgTpl">
                <img style="display: inline-block; width: 50%; height: 100%;" src= {{ d.avatar }}>
            </script>

            <%-- 行工具栏 --%>
            <script type="text/html" id="cms-user-option">

                <shiro:hasPermission name="user:update">
                    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:del" >
                    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
                </shiro:hasPermission>

            </script>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/layuiadmin/layui/layui.js"></script>
<script>
    layui.config({
        base: '${pageContext.request.contextPath}/layuiadmin/' //静态资源所在路径
    }).extend({
        index: 'lib/index' //主入口模块
    }).use(['index', 'useradmin', 'table'], function(){
        var $ = layui.$
            ,form = layui.form
            ,table = layui.table;

        //监听搜索的表单提交事件
        form.on('submit(cms-user-search)', function(data){
            var field = data.field;

            //执行重载指定名称的表格
            table.reload('cms-user-table', {
                // 刷新表格时额外的数据
                where: field
            });
        });

        //事件
        var active = {
            batchdel: function(){
                var checkStatus = table.checkStatus('cms-user-table')
                    ,checkData = checkStatus.data; //得到选中的数据

                console.log(checkData);


                $.ajax({
                    url: "${pageContext.request.contextPath}/user/userbatchdelete",
                    type:"post",
                    contentType:"application/json",
                    data: JSON.stringify(checkData),
                    success:function(res){
                        layer.msg(res.msg,{
                            time:1000
                        },function() {
                            if(res.code == 2000) {
                                window.location.reload();
                            }
                        })
                    }
                });
            }
            ,add: function(){
                layer.open({
                    type: 2
                    ,title: '添加用户'
                    ,content: '${pageContext.request.contextPath}/user/useradd'
                    ,maxmin: true
                    ,area: ['500px', '450px']
                    ,btn: ['确定', '取消']
                    ,yes: function(index, layero){

                        // 点击确定的事件

                        // 获取子窗口的数据

                        // 先找到当前弹出层的窗口对象
                        var iframeWindow = window['layui-layer-iframe'+ index]

                            // 找到子窗口中提交按钮
                            ,submitID = 'cms-user-add'
                            ,submit = layero.find('iframe').contents().find('#'+ submitID);

                        // 对子窗口的提交按钮，绑定监听提交事件
                        iframeWindow.layui.form.on('submit('+ submitID +')', function(data){

                            // var field = data.field; //获取提交的字段
                            // console.log(field);
                            //提交 Ajax 成功后，静态更新表格中的数据
                            var form = layero.find('iframe').contents().find('#cms-user-add-table');

                            console.log($(form).serialize());

                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/useradd",
                                type:"post",
                                data: $(form).serialize(),
                                success:function(result) {
                                    layer.msg(result.msg,{
                                        time:1000
                                    },function() {
                                        if(result.code == 2000) {
                                            // 只有成功才关闭弹出层并刷新表格
                                            table.reload('cms-user-table'); //数据刷新
                                            layer.close(index); //关闭弹层
                                        }
                                    })
                                }
                            });

                        });

                        submit.trigger('click');
                    }
                });
            }
        };

        $('.layui-btn.layuiadmin-btn-useradmin').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
</body>
</html>
