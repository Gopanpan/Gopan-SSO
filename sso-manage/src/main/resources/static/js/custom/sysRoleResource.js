
//角色权限树形结构的请求地址
var treeUrl = webConfig.webUrl + "/getSysRoleResourceTreeList";
//角色权限修改请求地址
var updateUrl = webConfig.webUrl + '/assignSysRoleResource';

//ztree相关配置
var setting = {
    check: {
        enable: true,
        chkStyle: "checkbox",
        radioType: "all"
    },
    view: {
        dblClickExpand: false
    },
    data:{
        simpleData :{
            enable:true,
            idKey: "id",
            pIdKey: "parentId"
        }
    },
    //回调函数
    callback:{
        onCheck:onCheck
    }
};

$(document).ready(function(){
    var zNodes;
    $.ajax({
        url		: treeUrl,
        type	: 'POST',
        dataType: 'json',
        data:{
            sysInfoId : $("#sysId").val(),
            roleId : $("#roleId").val()
        },
        success:function(data){
            zNodes = data.result;
            //加载ztree
            $.fn.zTree.init($("#tree"), setting, zNodes);
            //加载后全部展开
            var treeObj = $.fn.zTree.getZTreeObj("tree");
            treeObj.expandAll(true);
        }
    });

});

//监听checkbox被点击事件
function onCheck(e,treeId,treeNode){
    var nodeIds='';
    //获取到tree
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    //获取到node
    var nodes = treeObj.getCheckedNodes(true);
    //拼接成字符串
    for(var i=0;i<nodes.length;i++){
        nodeIds += nodes[i].id + ',';
    }
    //去掉字符串最末尾的','
    nodeIds = nodeIds.substring(0,nodeIds.length-1);
    return nodeIds;
}

//修改权限
$('#updateLimit').click(function(){
    $.ajax({
        url		: updateUrl,
        type	: 'POST',
        dataType: 'json',
        data	: {
            sysInfoId : $("#sysId").val(),
            roleId : $("#roleId").val(),
            ids: onCheck()	//此处传字符串数组
        },
        success:function(data){
            if(data){
                if(data.code == "10000" ){
                    layer.alert(data.message, {icon: 1},function(){
                        parent.layer.closeAll('iframe');
                    });
                }else{
                    layer.msg(data.message);
                }
            }
        }
    });
});

$('#cancle').click(function(){
    parent.layer.closeAll('iframe');
});
