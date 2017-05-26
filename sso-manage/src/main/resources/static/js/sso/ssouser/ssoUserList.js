//封装请求页面加载的参数
function setPostData(){
    return {
        phone:$("#phone").val(),
        loginName:$("#loginName").val(),
        realName:$("#realName").val()

    };
}

//加载页面
$(document).ready(function () {

    var data_colNames = [
        '用户Id','<div align="center"><span>用户名</span></div>','<div align="center"><span>真实名称</span></div>',
        '<div align="center"><span>性别</span></div>','<div align="center"><span>电话号码</span></div>',
        '<div align="center"><span>电子邮件</span></div>','<div align="center"><span>创建时间</span></div>',
        '<div align="center"><span>最后登录时间</span></div>','<div align="center"><span>是否启用</span></div>',
        '<div align="center"><span>操作</span></div>'
    ];
    var data_colModel = [
        {name: 'id', index: 'id',hidden: true },
        {name: 'loginName', index: 'loginName', sortable: false, align: 'center', width: 70},
        {name: 'realName', index: 'realName', sortable: false, align: 'center', width: 70},
        {name: 'sex', index: 'sex', sortable: false, align: 'center', width: 50,formatter: function (x) { return sexFormat(x);}},
        {name: 'phone', index: 'phone', sortable: false, align: 'center', width: 80},
        {name: 'email', index: 'email', sortable: false, align: 'center', width: 130},
        {name: 'createTime', index: 'createTime', sortable: false, align: 'center', width: 105,formatter: function (x) {return changeDateFormat(x);}},
        {name: 'lastLogin', index: 'lastLogin', sortable: false, align: 'center', width: 105, formatter: function (x) {return changeDateFormat(x);}},
        {name: 'available', index: 'available', sortable: false, align: 'center', width: 55,formatter: function (x) {return availableFormat(x);}},
        {name: 'id', index: 'id', sortable: false, resizable: false, align: 'center', width: 105,formatter: function (x,y,z) {return '<a class="btn btn-primary btn-xs" onclick="sysUserDetail(\''+x+'\');">详情</a> <a class="btn btn-primary btn-xs" onclick="updateSysUser(\''+x+'\');">修改</a> <a class="btn btn-primary btn-xs" onclick="deleteSysInfo(\''+x+'\',\''+z['loginName']+'\');">删除</a>';}}];

    initJqgrid(ssoUser_list_url,data_colNames,data_colModel);
});




//查询
$("#querySysUsers").bind('click',function(){
    var reloadParams = {
        url: ssoUser_list_url,
        postData: setPostData()
    };
    jqgrid_reload(reloadParams);
});

//刷新页面
function reload(){
    var reloadParams = {
        url: ssoUser_list_url,
        postData: setPostData()
    };
    jqgrid_reload(reloadParams);
}



//新增
$("#addSysUser").bind('click',function(){
    openPage(sysUser_addAUpdate_view_url);
});
function updateSysUser(id){
    openPage(sysUser_addAUpdate_view_url +"?id="+id);

}

function openPage(requestUrl){
    parent.layer.open({
        type: 2,
        title: '新增/修改系统用户',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['768px', '600px'],
        content: requestUrl,
        end: function () {
            reload();
        }
    });
}

//详情
function sysUserDetail(id) {
    parent.layer.open({
        type: 2,
        title: '用户详情',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['768px', '700px'],
        content: sysUser_detail_url+"?id="+id,
        end: function () {
            reload();
        }
    });
}

//删除用户
function deleteSysInfo(id, sysUserName) {

    layer.confirm('确定要删除系统用户:' + sysUserName, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: sysUser_delete_url,
            type: 'POST',
            dataType: 'json',
            data: {sysUserId:id}
        })
            .done(function (data) {
                serviceValidateHandleCurrent(data,true,true);
                reload();
            });
    }, function () {
        parent.layer.closeAll('iframe');
    });

}

