$(document).ready(function () {
    var userIds = document.getElementById('userIds').value;
    var sysInfoId = document.getElementById('sysInfoId').value; //系统id
    var subStationStatus = document.getElementById('subStationStatus').value; //系统子站状态
    if(null == sysInfoId || "" == sysInfoId || "undefined" == sysInfoId){
        layer.alert('系统信息为空，请重试',{icon:2},function(){
            parent.layer.closeAll('iframe');
        });
    }
    if(null == userIds || "" == userIds || "undefined" == userIds){
        layer.alert('未获取到用户信息，请重试',{icon:2},function(){
            parent.layer.closeAll('iframe');
        });
    }
    $.ajax({
        type: "POST",// 请求方式
        url: webConfig.webUrl + "/getSysRoleList",
        data :{
            sysInfoId:sysInfoId
        },
        dataType: "json",// 数据返回类型
        success: function(data){
            if(data.code == "10000" && data.result !=null ){
                var result = data.result;
                $("#roleSelect").append("<option value=''>"+'=请选择='+"</option>");
                $.each(result,function(index,value){
                    $("#roleSelect").append("<option value='"+value.id+"'>"+value.roleName+"</option>");
                });
            }else{
                layer.alert(data.message, {icon: 2});
            }
        }
    })


    if(subStationStatus ==1){
        document.getElementById("subStationSelect").style.display = "block";
        $.ajax({
                   type: "POST",// 请求方式
                   url: webConfig.webUrl + "/getSubStationList",
                   data :{
                       sysInfoId:sysInfoId
                   },
                   dataType: "json",// 数据返回类型
                   success: function(data){
                       if(data.code == "10000" && data.result !=null ){
                           var result = data.result;
                           $("#subStationSelect").append("<option value=''>"+'=请选择='+"</option>");
                           $.each(result,function(index,value){
                               $("#subStationSelect").append("<option value='"+value.subStationId+"'>"+value.subStationName+"</option>");
                           });
                           //初始化角色信息
                           //setRoleInfo(roleId);
                       }else{
                           layer.alert(data.message, {icon: 2});
                       }
                   }
               })
    }
});

//批量分配
$('#btnUpdate').click(function(){
    var sysInfoId = document.getElementById('sysInfoId').value,
        userIds = document.getElementById('userIds').value,
        selectRoleId = document.getElementById('roleSelect').value;
    //判断是否获取到用户id
    if(null == userIds || ""== userIds || "undefined" == userIds){
        layer.alert("未获取到用户信息，请重试！", {icon: 2});
        parent.layer.closeAll('iframe');
        return;
    }

    //判断是否选择角色
    if(null == selectRoleId || ""== selectRoleId || "undefined" == selectRoleId){
        layer.alert("请选择角色！", {icon: 2});
        return;
    }

    $.ajax({
            url: webConfig.webUrl+'/batchAddSysUserRole',
            type: 'POST',
            dataType: 'json',
            data :{
                userIds:userIds,
                sysInfoId:sysInfoId,
                roleId:selectRoleId
            }
        })
        .done(function(data) {
            if(data){
                if(data.code == "10000"){
                    layer.alert(data.message, {icon: 1},function(){
                        parent.layer.closeAll('iframe');
                    });
                }else{
                    layer.alert(data.message, {icon: 2});
                }
            }
        });
});

$('#btnCancel').click(function(){
    parent.layer.closeAll('iframe');
});
