$(document).ready(function () {
    var userId = document.getElementById('userId').value;
    var sysInfoId = document.getElementById('sysInfoId').value; //系统id
    var subStationStatus = document.getElementById('subStationStatus').value; //系统子站状态
    var roleId = document.getElementById('roleId').value; //角色id
    if(null == sysInfoId || "" == sysInfoId || "undefined" == sysInfoId){
        layer.alert('系统信息为空，请重试',{icon:2},function(){
            parent.layer.closeAll('iframe');
        });
    }
    if(null == userId || "" == userId || "undefined" == userId){
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
                //初始化角色信息
                setRoleInfo(roleId);
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
                   data :{},
                   dataType: "json",// 数据返回类型
                   success: function(data){
                       if(data.code == "10000" && data.result !=null ){
                           var result = data.result;
                           $("#subStationId").append("<option value='-1' selected='selected'>"+'=请选择='+"</option>");
                           $.each(result,function(index,value){
                               $("#subStationId").append("<option value='"+value.subStationNo+"'>"+value.subStationName+"</option>");
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

//初始化角色信息
function setRoleInfo(roleId) {
    if(null == roleId || "" == roleId || "undefined" == roleId){
        return;
    }
    document.getElementById('roleSelect').value = roleId;  //角色
}






$('#btnUpdate').click(function(){
    var subStationStatus = document.getElementById('subStationStatus').value;


    var sysUserRole = {},
        selectRoleId = document.getElementById('roleSelect').value,
        userRoleId = document.getElementById('userRoleId').value,
        subStationId = document.getElementById('subStationId').value,
        roleId = document.getElementById('roleId').value;

    //判断是否选择角色
    if(null == selectRoleId || ""== selectRoleId || "undefined" == selectRoleId){
        layer.alert("请选择角色！", {icon: 2});
        return;
    }

    alert(subStationId);
    if(subStationStatus==1){
        //判断是否选择子站
        if(null == subStationId || ""== subStationId || "undefined" == subStationId){
            layer.alert("请选择子站！", {icon: 2});
            return;
        }
    }
    
    
    if(selectRoleId == roleId ){
        layer.alert("角色未做修改！", {icon: 2});
        return;
    }

    var url=webConfig.webUrl+'/addSysUserRole';
    //id存在则更新，不存在则新增
    if(null != roleId && ""!= roleId && "undefined" != roleId){
        if(null == userRoleId || ""== userRoleId || "undefined" == userRoleId){
            layer.alert("未获取到用户角色信息，更新失败！", {icon: 2});
            return;
        }
        url = webConfig.webUrl+'/updateSysUserRole';
        sysUserRole.id = userRoleId;
    }
    //参数
    sysUserRole.sysInfoId = document.getElementById('sysInfoId').value; //系统id
    sysUserRole.userId = document.getElementById('userId').value;  //用户id
    sysUserRole.roleId = selectRoleId;
    sysUserRole.subStationId = subStationId;

    $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType:"application/json; charset=utf-8",
            data: JSON.stringify(sysUserRole)
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