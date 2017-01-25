
//角色列树形结构的请求地址
var treeUrl = webConfig.webUrl + "/getSysRoleColumnsTreeList";
//列分配请求地址
var updateUrl = webConfig.webUrl + '/assignSysRoleColumns';

//监听事件
function addHoverDom(treeId, treeNode) {
    var id=treeNode.id,
        resourceColumns = treeNode.resourceColumns,
        columns = treeNode.columns;
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#updateBtn_"+treeNode.tId).length>0) return;

    if(treeNode.canEdit && "true" == treeNode.canEdit ){
        var addStr = "<span class='tempBtn button edit' id='updateBtn_" + treeNode.tId
            + "' title='修改' onfocus='this.blur();' onclick='updateDetails(\""+id+"\",\""+columns+"\",\""+resourceColumns+"\")'></span>";
        sObj.after(addStr);
    }
}

//tree节点点击事件
function updateDetails(id,columns,resourceColumns){

    document.getElementById('roleColumnId').value = id;
    //点击时候先清空，防止重复加载
    document.getElementById("columns").innerHTML = "";
    var columnsArray = columns.split(",");
    $.each(columnsArray,function(index,value){
        var columnStr;
        if(null != resourceColumns && "" != resourceColumns && "undefined" != resourceColumns && resourceColumns.indexOf(value) >= 0 ){
            columnStr ="&nbsp;&nbsp;"+ "<input type='checkbox' name='column' checked='checked' value='"+value+"' />"+value+"";
        }else{
            columnStr ="&nbsp;&nbsp;" + "<input type='checkbox' name='column' value='"+value+"' />"+value+"";
        }
        if((index+1) % 2 == 0){
            columnStr = columnStr +"<br>";
        }
        $("#columns").append(columnStr);
    });
}

//获取选中的列
function getCheckedColumns() {
    var columnsChecked='';
    //获取页面所有checkbox（checkbox的name设置一致）
    var items = document.getElementsByName("column");
    //遍历checkbox
    $.each(items,function(index,item){
        if(item.checked){
            columnsChecked += item.value + ',';
        }
    });
    //去掉字符串最末尾的','
    columnsChecked = columnsChecked.substring(0,columnsChecked.length-1);
    return columnsChecked;
}

//ztree相关配置
var setting = {
    //不需要checkbox,也不需要回调
    view: {
        dblClickExpand: false,
        addHoverDom:addHoverDom
    },
    data:{
        simpleData :{
            enable:true,
            idKey: "resourceId",
            pIdKey: "pid"
        }
    }
};

//初始化树形结构
$(document).ready(function(){
    loadColumnsTree();
});

//加载树形结构
function loadColumnsTree() {
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
}

//修改权限
$('#updateLimit').click(function(){

    var roleColumnId = document.getElementById('roleColumnId').value;
    if(null == roleColumnId || "" == roleColumnId || "undefined" == roleColumnId){
        layer.msg("请选择需要编辑的列信息！");
        return
    }

    var sysRoleResource = {};
    //参数
    sysRoleResource.id = document.getElementById('roleColumnId').value; //id
    sysRoleResource.resourceColumns = getCheckedColumns();  //列信息
    $.ajax({
        url		: updateUrl,
        type	: 'POST',
        dataType: 'json',
        contentType:"application/json; charset=utf-8",
        data: JSON.stringify(sysRoleResource),
        success:function(data){
            if(data){
                if(data.code == "10000"){
                    loadColumnsTree();
                    layer.msg(data.message);
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
