/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */


layui.define(['table', 'form'], function(exports){
  var $ = layui.$
  ,table = layui.table
  ,form = layui.form;

  //用户管理
  table.render({
    elem: '#cms-user-table' // 给哪一个表格渲染数据
    ,url: "http://localhost:8080/cms/user/userinfolist" //接口的真实地址
    ,parseData: function(res){ //res 即为原始返回的数据
      // 将真实的数据格式，和layui需要数据格式做转换
      var data1 = {
        "code": res.code == 2000 ? 0 : res.code, //解析接口状态
        "msg": res.msg, //解析提示文本
        "count": res.data.size, //解析数据长度
        "data": res.data.lists //解析数据列表
      };
      console.log(data1);
      return data1;
    }
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'userId', title: 'ID', sort: true}
      ,{field: 'username', title: '用户名'}
      //,templet: '#imgTpl'
      ,{field: 'headerImage', title: '头像'}
      ,{field: 'nickname', title: '昵称'}
      ,{field: 'roles', title: '用户角色'}
      ,{field: 'userInsertTime', title: '注册时间'}
      ,{field: 'userLastModifiedTime',title: '最后修改时间'}
      ,{title: '操作', width: 150, align:'center', fixed: 'right', toolbar: '#cms-user-option'}
    ]]  // 表格中数据和真实数据的对应关系
    ,page: true // 是否开启分页
    ,limit: 5   //  每页多少条
    ,height: 'full-220'
    ,text: '对不起，加载出现异常！'  // 如果数据异常的显示消息
  });
  
  //监听行工具条
  table.on('tool(cms-user-table)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      // 行工具栏的删除事件
        console.log(data);

        $.ajax({
          url: "http://localhost:8080/cms/user/userdelete",
          type:"post",
          data: {
            id: data.userId
          },
          success:function(result) {
              layer.msg(result.msg,{
                time:1000
              }, function() {
              //   消息提示完成的回调
                  if(result.code == 2000) {
                    // 刷新当前页
                    window.location.reload();
                  } else {
                  //   失败
                  }
              })
          }
        });
    } else if(obj.event === 'edit'){
      var tr = $(obj.tr);

      layer.open({
        type: 2
        ,title: '编辑用户'
        // 编辑页面的地址
        ,content: "http://localhost:8080/cms/user/useredit"
        ,maxmin: true
        ,area: ['500px', '450px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          //  找到弹出层
          var iframeWindow = window['layui-layer-iframe'+ index]
          //    找到修改确认提交按钮
          ,submitID = 'cms-user-edit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            // var field = data.field; //获取提交的字段
            // 默认的field取checkbox有bug，采用原始的jquery去做
            var form = layero.find('iframe').contents().find('#cms-user-edit-table');
            $.ajax({
              url:"http://localhost:8080/cms/user/useredit",
              type:"post",

              data: $(form).serialize(),
              success:function(result) {
                // 弹出提示消息
                layer.msg(result.msg,{
                  time:1000
                },function(){
                  if(result.code == 2000) {
                    table.reload('cms-user-table'); //数据刷新
                    layer.close(index); //关闭弹层
                  }
                })
              }
            })

          });  
          
          submit.trigger('click');
        }
        //  弹出层渲染完成的回调函数，在此处将数据回显
        ,success: function(layero, index){

            // data代表当前行数据，name分别代表每一列的input name属性
            for(var name in data) {
              // 对除了checkbox以外的input，填写普通字符串，前面一个调用是layui的固定写法
              var demo = layero.find('iframe').contents().find("[type!='checkbox'][name='"+ name + "']").val(data[name]);
            }

            // 角色复选框特殊处理
            // 当前用户的多种角色遍历, 用逗号隔开，所以，用，可以将其分割成数组
            var rolearray = data.roles.split(",");
            for(var index in rolearray) {
              // 利用下标可以在数组中找到对应的角色
              // 在弹出层找到对应角色的checkbox，勾选
              layero.find('iframe').contents().find("[name='roles'][title='"+ rolearray[index] + "']").attr("checked",true);
            }

        }
      });
    }
  });

  //管理员管理
  table.render({
    elem: '#LAY-user-back-manage'
    ,url: layui.setter.base + 'json/useradmin/mangadmin.js' //模拟接口
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', width: 80, title: 'ID', sort: true}
      ,{field: 'loginname', title: '登录名'}
      ,{field: 'telphone', title: '手机'}
      ,{field: 'email', title: '邮箱'}
      ,{field: 'role', title: '角色'}
      ,{field: 'jointime', title: '加入时间', sort: true}
      ,{field: 'check', title:'审核状态', templet: '#buttonTpl', minWidth: 80, align: 'center'}
      ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text: '对不起，加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-user-back-manage)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.prompt({
        formType: 1
        ,title: '敏感操作，请验证口令'
      }, function(value, index){
        layer.close(index);
        layer.confirm('确定删除此管理员？', function(index){
          console.log(obj)
          obj.del();
          layer.close(index);
        });
      });
    }else if(obj.event === 'edit'){
      var tr = $(obj.tr);

      layer.open({
        type: 2
        ,title: '编辑管理员'
        ,content: '../../../views/user/administrators/adminform.html'
        ,area: ['420px', '420px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submitID = 'LAY-user-back-submit'
          ,submit = layero.find('iframe').contents().find('#'+ submitID);

          //监听提交
          iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
            var field = data.field; //获取提交的字段
            
            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            table.reload('LAY-user-front-submit'); //数据刷新
            layer.close(index); //关闭弹层
          });  
          
          submit.trigger('click');
        }
        ,success: function(layero, index){           
          
        }
      })
    }
  });

  //角色管理
  table.render({
    elem: '#LAY-user-back-role'
    ,url: layui.setter.base + 'json/useradmin/role.js' //模拟接口
    ,cols: [[
      {type: 'checkbox', fixed: 'left'}
      ,{field: 'id', width: 80, title: 'ID', sort: true}
      ,{field: 'rolename', title: '角色名'}
      ,{field: 'limits', title: '拥有权限'}
      ,{field: 'descr', title: '具体描述'}
      ,{title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-useradmin-admin'}
    ]]
    ,text: '对不起，加载出现异常！'
  });
  
  //监听工具条
  table.on('tool(LAY-user-back-role)', function(obj){
    var data = obj.data;
    if(obj.event === 'del'){
      layer.confirm('确定删除此角色？', function(index){
        obj.del();
        layer.close(index);
      });
    }else if(obj.event === 'edit'){
      var tr = $(obj.tr);

      layer.open({
        type: 2
        ,title: '编辑角色'
        ,content: '../../../views/user/administrators/roleform.html'
        ,area: ['500px', '480px']
        ,btn: ['确定', '取消']
        ,yes: function(index, layero){
          var iframeWindow = window['layui-layer-iframe'+ index]
          ,submit = layero.find('iframe').contents().find("#LAY-user-role-submit");

          //监听提交
          iframeWindow.layui.form.on('submit(LAY-user-role-submit)', function(data){
            var field = data.field; //获取提交的字段
            
            //提交 Ajax 成功后，静态更新表格中的数据
            //$.ajax({});
            table.reload('LAY-user-back-role'); //数据刷新
            layer.close(index); //关闭弹层
          });  
          
          submit.trigger('click');
        }
        ,success: function(layero, index){
        
        }
      })
    }
  });

  exports('useradmin', {})
});