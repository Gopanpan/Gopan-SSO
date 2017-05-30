//封装请求页面加载的参数
function setPostData(){
    return {
        name:$("#name").val(),
        systemCode:$("#systemCode").val(),
        available:$("#available").val()

    };
}

//加载页面
$(document).ready(function () {

    var data_colNames = [
        'Id','<div align="center"><span>系统名称</span></div>','<div align="center"><span>系统编码</span></div>',
        '<div align="center"><span>是否可用</span></div>','<div align="center"><span>创建人</span></div>',
        '<div align="center"><span>创建时间</span></div>','<div align="center"><span>修改人</span></div>',
        '<div align="center"><span>修改时间</span></div>',
        '<div align="center"><span>操作</span></div>'
    ];
    var data_colModel = [
        {name: 'id', index: 'id',hidden: true },
        {name: 'name', index: 'name', sortable: false, align: 'center', width: 100},
        {name: 'systemCode', index: 'systemCode', sortable: false, align: 'center', width: 100},
        {name: 'available', index: 'available', sortable: false, align: 'center', width: 50,formatter: function (x) {
            return availableFormat(x);
        }},
        {name: 'createUser', index: 'createUser', sortable: false, align: 'center', width: 100},
        {name: 'createTime', index: 'createTime', sortable: false, align: 'center', width: 100,formatter:function (x) {
            return changeDateFormat(x);
        }},
        {name: 'updateUser', index: 'updateUser', sortable: false, align: 'center', width: 100},
        {name: 'updateTime', index: 'updateTime', sortable: false, align: 'center', width: 100, formatter: function (x) {return changeDateFormat(x);}},
        {name: 'id', index: 'id', sortable: false, resizable: false, align: 'center', width: 100,formatter: function (x,y,z) {return '<a class="btn btn-primary btn-xs" onclick="generalDetail(\''+x+'\');">详情</a> <a class="btn btn-primary btn-xs" onclick="generalUpdate(\''+x+'\');">修改</a> <a class="btn btn-primary btn-xs" onclick="generalDelete(\''+x+'\',\''+z['loginName']+'\');">删除</a>';}}];

    initJqgrid(ssoSystem_list_url,data_colNames,data_colModel);
});




//查询
$("#generalQuery").bind('click',function(){
    var reloadParams = {
        url: ssoSystem_list_url,
        postData: setPostData()
    };
    jqgrid_reload(reloadParams);
});

//刷新页面
function reload(){
    var reloadParams = {
        url: ssoSystem_list_url,
        postData: setPostData()
    };
    jqgrid_reload(reloadParams);
}



//新增
$("#generalAdd").bind('click',function(){
    openPage(ssoSystem_addAUpdate_view_url);
});
function generalUpdate(id){
    openPage(ssoSystem_addAUpdate_view_url +"?id="+id);

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
function generalDetail(id) {
    parent.layer.open({
        type: 2,
        title: '用户详情',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['768px', '700px'],
        content: ssoUser_detail_view_url+"?id="+id,
        end: function () {
            reload();
        }
    });
}

//删除用户
function generalDelete(id, sysUserName) {

    layer.confirm('确定要删除系统用户:' + sysUserName, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        $.ajax({
            url: ssoUser_delete_url,
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

