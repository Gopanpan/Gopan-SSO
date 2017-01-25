function moduleDetails(id,pid){
    var sysInfoId = document.getElementById('sysInfoId').value; //系统id
    parent.layer.open({
        type: 2,
        title: '资源信息',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['768px', '550px'],
        content: webConfig.webUrl + '/manager/sysResourceDetail?id='+id+'&pid='+pid+'&sysInfoId='+sysInfoId,
        end:function(){
            findResource();
        }
    });
}

function addHoverDom(treeId, treeNode) {
    var sid=treeNode.id,
        spid=treeNode.parentId;
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
    var addStr = "<span class='tempBtn button add' id='addBtn_" + treeNode.tId
        + "' title='添加' onfocus='this.blur();' onclick='moduleDetails(\"\",\""+sid+"\")'></span><span class='tempBtn button edit' id='updateBtn_" + treeNode.tId
        + "' title='修改' onfocus='this.blur();' onclick='moduleDetails(\""+sid+"\",\""+spid+"\")'></span><span class='tempBtn button remove' id='updateBtn_" + treeNode.tId
        + "' title='删除' onfocus='this.blur();' onclick='beforeRemove(\""+sid+"\")'></span>";
    sObj.after(addStr);
}

function removeHoverDom(treeId, treeNode) {
    $(".tempBtn").remove();
}

//删除
function beforeRemove(sid){
    layer.confirm('确认删除吗？', {icon: 3, title:'提示'}, function(index){
        $.ajax({
            url: webConfig.webUrl + "/deleteSysResource",
            type: 'POST',
            dataType: 'json',
            data :{
                id : sid
            },
            success:function(data){
                if(data.code=="10000") {
                    layer.alert(data.message, {icon: 1}, function (index) {
                        findResource();
                        layer.close(index);
                    });
                }else{
                    layer.alert(data.message, {icon: 1}, function (index) {
                        findResource();
                        layer.close(index);
                    });
                    //layer.alert(data.message, {icon: 2});
                }
            }
        });
    });
}

$(document).ready(function () {
    $.ajax({
        type: "POST",// 请求方式
        url: webConfig.webUrl + "/getSysInfoList",
        data :{
        },
        dataType: "json",// 数据返回类型
        success: function(data){
            if(data.code == "10000" && data.result !=null ){
                var result = data.result;
                $.each(result,function(index,value){
                    $("#sysInfoId").append("<option value='"+value.id+"'>"+value.name+"</option>");
                });
                findResource();
            }else{
                layer.alert(data.message, {icon: 2});
            }
        }
    })
});

$("#findResource").bind('click',function(){
    findResource();
});

//ztree相关配置
var setting = {
    //不需要checkbox,也不需要回调
    view: {
        dblClickExpand: false,
        addHoverDom:addHoverDom,
        removeHoverDom:removeHoverDom
    },
    data:{
        simpleData :{
            enable:true,
            idKey: "id",
            pIdKey: "parentId"
        }
    }
};

function findResource() {
    var sysInfoId = document.getElementById('sysInfoId').value; //系统id
    var zNodes;
    $.ajax({
        type: "POST",// 请求方式
        url: webConfig.webUrl + "/getSysResourceList",
        data :{
            sysInfoId : sysInfoId
        },
        dataType: "json",// 数据返回类型
        success: function(data){
            if(data.code== "10000"){
                if(data !=null && data.result !=null ){
                    zNodes = data.result;
                    $.fn.zTree.init($("#tree"), setting, zNodes);
                    //加载后全部展开
                    var treeObj = $.fn.zTree.getZTreeObj("tree");
                    treeObj.expandAll(true);
                }
            }else{
                layer.alert(data.message);
            }
        }
    })
}
