$(document).ready(function () {
    var id = document.getElementById('id').value;
    var parentId = document.getElementById('pid').value;
    var sysInfoId = document.getElementById('sysInfoId').value; //系统id
    if (null == sysInfoId || "" == sysInfoId || "undefined" == sysInfoId) {
        layer.alert('系统信息为空，请重试', {icon: 2}, function () {
        });
        return;
    }
    if (null == parentId || "" == parentId || "undefined" == parentId) {
        layer.alert('父级ID不能为空，请重试', {icon: 2}, function () {
        });
        return;
    }
    if (null != id && "" != id) {
        $.ajax({
                   url: webConfig.webUrl + '/getSysResource',
                   type: 'POST',
                   dataType: 'json',
                   data: {id: id}
               })
            .done(function (data) {
                if (data) {
                    if (data.code == "10000" && data.result) {
                        document.getElementById('code').value = data.result.code;  //资源编码
                        document.getElementById('type').value = data.result.type;  //资源类型 按钮为buttn
                        document.getElementById('name').value = data.result.name;  //资源名称
                        document.getElementById('url').value = data.result.url;  //资源链接(对应前端页面和按钮)
                        document.getElementById('location').value = data.result.location;  //页面显示位置
                        document.getElementById('columns').value = data.result.columns;  //列信息
                    }
                }
            });
    }
});

$('#btnUpdate').click(function () {
    var sysResource = {},
        id = document.getElementById('id').value;
    url = webConfig.webUrl + '/addSysResource';
    //id存在则更新，不存在则新增
    if (null != id && "" != id) {
        url = webConfig.webUrl + '/updateSysResource';
        sysResource.id = id;
    }
    //参数
    sysResource.sysInfoId = document.getElementById('sysInfoId').value; //系统id
    sysResource.parentId = document.getElementById('pid').value;  //父级资源项目
    sysResource.code = document.getElementById('code').value;  //资源编码
    sysResource.name = document.getElementById('name').value;  //资源名称
    sysResource.type = document.getElementById('type').value;  //资源类型 按钮为buttn
    sysResource.url = document.getElementById('url').value;  //资源链接(对应前端页面和按钮)
    sysResource.location = document.getElementById('location').value;  //页面显示位置
    sysResource.columns = document.getElementById('columns').value;  //列信息

    if ("" == sysResource.name) {
        layer.alert("资源名称不能为空！", {icon: 2});
        return
    }

    $.ajax({
               url: url,
               type: 'POST',
               dataType: 'json',
               contentType: "application/json; charset=utf-8",
               data: JSON.stringify(sysResource)
           })
        .done(function (data) {
            if (data) {
                if (data.code == "10000") {
                    layer.alert(data.message, {icon: 1}, function () {
                        parent.layer.closeAll('iframe');
                    });
                } else {
                    layer.alert(data.message, {icon: 2});
                }
            }
        });
});

$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});

$('#addColumn').click(function () {
    var columnEn = document.getElementById('columnEn').value,
        columnCn = document.getElementById('columnCn').value,
        columns = document.getElementById('columns').value;
    if ("" == columnEn) {
        layer.alert("列字段不能为空", {icon: 2});
        return;
    }
    if ("" == columnCn) {
        layer.alert("列描述不能为空", {icon: 2});
        return;
    }
    //layer.alert(columns, {icon: 2});
    if ("" != columns) {
        columns = columns + "," + columnEn + ":" + columnCn;
    } else {
        columns = columnEn + ":" + columnCn;
    }
    document.getElementById('columnEn').value = "";
    document.getElementById('columnCn').value = "";
    document.getElementById('columns').value = columns;
});

$('#deleteColumn').click(function () {
    var columns = document.getElementById('columns').value;
    if ("" == columns) {
        layer.alert("没有可删除的信息！", {icon: 2});
        return;
    }
    if(columns.indexOf(",") > 0){
        columns = columns.substring(0, columns.lastIndexOf(",") - 1);
        document.getElementById('columns').value = columns;
    }else{
        document.getElementById('columns').value = "";
    }
});

$('#deleteColumns').click(function () {
    document.getElementById('columns').value = "";
});

$('#editColumns').click(function () {
    var columns = document.getElementById('columns');
    if(columns.getAttribute("disabled")){
        columns.removeAttribute("disabled");
        document.getElementById('editColumns').innerText = "关闭编辑";
    }else{
        columns.setAttribute("disabled","disabled");
        document.getElementById('editColumns').innerText = "编辑";
    }
});