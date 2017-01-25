$(document).ready(function () {
    $.jgrid.defaults.styleUI = 'Bootstrap';
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
                    $("#sysInfoId").append("<option value='"+value.id+"@"+value.subStationStatus+"'>"+value.name+"</option>");
                });
                initJqgrid();
            }else{
                layer.alert(data.message, {icon: 2});
            }
        }
    })
});

$("#findUserRole").bind('click',function(){
    query();
});




//查询
function query() {

    var reloadParams = {
        url: dataUrl,
        postData: setPostData()
    };
    $(grid_selector).jqGrid("setGridParam", reloadParams).trigger("reloadGrid",[{ page: 1 }]);
}


var dataUrl = webConfig.webUrl+'/getSysUserRolePageList',
    grid_selector = "#grid-table",
    pager_selector = "#grid-pager",
    pageSize = 20;

function initJqgrid() {
    $(grid_selector).jqGrid({
        height:'atuo',

        //是否允许全选
        multiselect: true,

        //全选的宽度(只有multiselect:true时)
        multiselectWidth: 30,

        //是否允许显示/隐藏，只有caption不为空时有效
        hidegrid: false,

        //是否显示行号
        //rownumbers: true,

        //table → caption
        caption: "用户列表",

        //显示的标题
        colNames: ['用户编号', '用户名称', '', '', '角色名称', '操作'],

        //列属性，和colNames的个数和顺序必须对应
        //参考：http://www.helloweba.com/view-blog-162.html#col
        colModel: [
            { name: 'id', index: 'id', sortable: false, align: 'center', width: 50 },
            {
                name: 'userName', index: 'userName', sortable: false, align: 'center', width: 100
            },
            { name: 'userRoleId', index: 'userRoleId', hidden: true },
            { name: 'roleId', index: 'roleId', hidden: true },
            {
                name: 'roleName', index: 'roleName', sortable: false, align: 'center', width: 100
            },
            {
                name: 'id', index: 'id', sortable: false, resizable: false, align: 'center', width: 125, formatter: function (x,y,z) {
                /*if(z['userRoleId'] && "" != z['userRoleId'] && "undefined" != z['userRoleId']){
                    return '<a class="btn btn-primary btn-xs" onclick="assignRole(\''+x+'\',\''+z['userRoleId']+'\',\''+z['roleId']+'\');">修改角色</a>';
                }else{
                }*/
                return '<a class="btn btn-primary btn-xs" onclick="assignRole(\''+x+'\',\''+z['userRoleId']+'\',\''+z['roleId']+'\');">分配角色</a>';
            }
            }
        ],

        //可选显示的行数
        rowList: [10, 20, 30],

        //默认显示行数
        rowNum: pageSize,

        //总条数
        viewrecords: true,

        //分页层 id
        pager: pager_selector,

        //分页参数
        prmNames: {
            page: 'pageIndex', rows: "pageSize", order: null, search: null, sort: null, nd: null, sidx: null
        },

        //提交方式，默认get
        mtype: 'post',

        postData: setPostData(),

        //数据格式
        datatype: "json",

        jsonReader: {
            //是否允许乱序显示数据,true:不可以
            repeatitems: true,
            root: "result.data",
            total: "result.page",
            page: "result.pageIndex",
            records: "result.recordsTotal"
        },

        //数据来源地址
        url: dataUrl
    }).jqGrid('setGridWidth', $(".ibox-content").width());

    //设置grid宽度为自适应,适应比例根据每列设置的宽度
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', $(".ibox-content").width());
    });
}

//设置参数
function setPostData(){
    var sysInfo=$("#sysInfoId").val();
    var subString=sysInfo.indexOf('@');
    var sysInfoId=sysInfo.substr(0,subString);
    var subStationStatus=sysInfo.substr(subString+1,sysInfo.length);

    $("#subStationStatus").val(subStationStatus);

    return {
        sysInfoId:sysInfoId,
        //sysInfoId:$("#sysInfoId").val(),
        userName:$("#userName").val(),
        pageSize: pageSize
    };
}

//角色分配
function assignRole(userId,userRoleId,roleId) {
    var sysInfo=$("#sysInfoId").val();
    var subString=sysInfo.indexOf('@');
    var sysInfoId=sysInfo.substr(0,subString);
    var subStationStatus=sysInfo.substr(subString+1,sysInfo.length);
    if(null == sysInfoId || "" == sysInfoId || "undefined" == sysInfoId){
        layer.alert('未获取到系统信息，请重试！',{icon:2},function(){
        });
        return;
    }
    parent.layer.open({
        type: 2,
        title: '配置角色',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['368px', '300px'],
        //角色分配页面
        content: webConfig.webUrl + "/manager/sysUserRoleAssign?userId="+userId+'&sysInfoId='+sysInfoId+'&userRoleId='+userRoleId+'&roleId='+roleId+'&subStationStatus='+subStationStatus,
        end:function(){
            query();
        }
    });
}

$("#batchAssignRole").bind('click',function(){
    batchAssignRole();
});

//批量分批角色
function batchAssignRole(){
    var sysInfo=$("#sysInfoId").val();
    var subString=sysInfo.indexOf('@');
    var sysInfoId=sysInfo.substr(0,subString);
    var subStationStatus=sysInfo.substr(subString+1,sysInfo.length);
    if(null == sysInfoId || "" == sysInfoId || "undefined" == sysInfoId){
        layer.alert('未获取到系统信息，请重试！',{icon:2},function(){
        });
        return;
    }
    // 获取被分配用户id
    var idStr = "";
    var idsArray = $("#grid-table").jqGrid('getGridParam', 'selarrrow');

    for(var i = 0; i < idsArray.length; i++){
        idStr += $(grid_selector).jqGrid('getCell', idsArray[i], 'id') + ',';
    }
    idStr = idStr.substr(0, idStr.length - 1);
    if(!idStr){
        layer.alert('请先选择被分配用户',{icon:2}); return false;
    }
    parent.layer.open({
        type: 2,
        title: '批量配置角色',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['368px', '300px'],
        //角色分配页面
        content: webConfig.webUrl + "/manager/sysUserRoleBatchAssign?userIds="+idStr+'&sysInfoId='+sysInfoId+'&subStationStatus='+subStationStatus,
        end:function(){
            query();
        }
    });
}



