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

    var data_colNames = ['用户Id','<div align="center"><span>用户名</span></div>','<div align="center"><span>真实名称</span></div>','<div align="center"><span>性别</span></div>','<div align="center"><span>电话号码</span></div>', '<div align="center"><span>电子邮件</span></div>','<div align="center"><span>创建时间</span></div>', '<div align="center"><span>最后登录时间</span></div>','<div align="center"><span>是否启用</span></div>', '<div align="center"><span>操作</span></div>'];
    var data_colModel = [
        {name: 'id', index: 'id',hidden: true },
        {name: 'loginName', index: 'loginName', sortable: false, align: 'center', width: 70},
        {name: 'realName', index: 'realName', sortable: false, align: 'center', width: 70},
        {name: 'sex', index: 'sex', sortable: false, align: 'center', width: 50,formatter: function (x) { return sexFormat(x);}},
        {name: 'phone', index: 'phone', sortable: false, align: 'center', width: 80},
        {name: 'email', index: 'email', sortable: false, align: 'center', width: 130},
        {name: 'createTime', index: 'createTime', sortable: false, align: 'center', width: 105,formatter: function (x) {return ChangeDateFormat(x);}},
        {name: 'lastLogin', index: 'lastLogin', sortable: false, align: 'center', width: 105, formatter: function (x) {return ChangeDateFormat(x);}},
        {name: 'available', index: 'available', sortable: false, align: 'center', width: 55,formatter: function (x) {return availableFormat(x);}},
        {name: 'id', index: 'id', sortable: false, resizable: false, align: 'center', width: 105,formatter: function (x,y,z) {return '<a class="btn btn-primary btn-xs" onclick="sysUserDetail(\''+x+'\');">详情</a><a class="btn btn-primary btn-xs" onclick="andOrupdateSysUser(\''+x+'\');">修改</a> <a class="btn btn-primary btn-xs" onclick="deleteSysInfo(\''+x+'\',\''+z['loginName']+'\');">删除</a>';}}];

    initJqgrid(sysUser_list_url,data_colNames,data_colModel);
});




//查询
$("#querySysUsers").bind('click',function(){
    var reloadParams = {
        url: sysUser_list_url,
        postData: setPostData()
    };
    jqgrid_reload(reloadParams);
});


//新增
$("#addSysUser").bind('click',function(){

    //andOrupdateSysUser();

    parent.layer.open({
        type: 2,
        title: '新增/修改系统用户',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['500px', '500px'],
        content: sysUser_addAUpdate_url,
        end: function () {
            reload();
        }
    });

});

//新增或修改用户
function andOrupdateSysUser(sysUserId) {
    parent.layer.open({
        type: 2,
        title: '新增/修改系统用户',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['790px', '500px'],
        content: base_serve_url_config.webUrl + '/andOrupdateSysUserView?sysUserId=' + sysUserId,
        end: function () {
            reload();
        }
    });
}

//详情
function sysUserDetail(sysUserId) {
    parent.layer.open({
        type: 2,
        title: '用户详情',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['768px', '700px'],
        content: base_serve_url_config.webUrl + "/manager/sysUserDetail?sysUserId="+sysUserId,
        end: function () {
            reload();
        }
    });
}

//删除用户
function deleteSysInfo(sysUserId, sysUserName) {

    layer.confirm('确定要删除系统:' + sysUserName, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        confirmDelete(sysUserId);
    }, function () {
        parent.layer.closeAll('iframe');
    });

}

function confirmDelete(sysUserId) {
    var sysUserInfo = {},
        url = base_serve_url_config.webUrl + '/deleteSysUser';
        sysUserInfo.id = sysUserId;
    $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sysUserInfo)
    })
    .done(function (data) {
        if (data) {
            if (data.code == "10000") {
                layer.alert(data.message, {icon: 1});
                reload();
            } else {
                layer.alert(data.message, {icon: 2});
            }
        }
    });
}





//刷新页面
function reload(){
    var reloadParams = {
        url: dataUrl,
        postData: setPostData()
    };
    jqgrid_reload(reloadParams);
}
