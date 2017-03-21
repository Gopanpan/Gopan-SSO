//常量
var dataUrl = webConfig.webUrl+'/systemUserList',
    grid_selector = "#grid-table",
    pager_selector = "#grid-pager",
    pageSize = 10;


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
    $.jgrid.defaults.styleUI = 'Bootstrap';
    var reloadParams = {
        url: dataUrl,
        postData: setPostData()
    };
    initJqgrid();

});


//初始化表格
function initJqgrid() {
    $(grid_selector).jqGrid({
        height:'atuo',

        //是否允许全选
        multiselect: true,

        //全选的宽度(只有multiselect:true时)
        //multiselectWidth: 30,

        //是否允许显示/隐藏，只有caption不为空时有效
        hidegrid: false,

        //是否显示行号
        //rownumbers: true,

        //显示的标题
        colNames: ['用户Id','<div align="center"><span>用户名</span></div>','<div align="center"><span>真实名称</span></div>','<div align="center"><span>性别</span></div>','<div align="center"><span>电话号码</span></div>','<div align="center"><span>电子邮件</span></div>','<div align="center"><span>创建时间</span></div>','<div align="center"><span>更新时间</span></div>', '<div align="center"><span>操作</span></div>'],

        //列属性，和colNames的个数和顺序必须对应
        colModel: [
            {name: 'id', index: 'id',hidden: true },
            {name: 'loginName', index: 'loginName', sortable: false, align: 'center', width: 100},
            {name: 'realName', index: 'realName', sortable: false, align: 'center', width: 100},
            {
                name: 'sex', index: 'sex', sortable: false, align: 'center', width: 50,
                formatter: function (x) {
                    return sexFormat(x);
                }
            },
            {name: 'phone', index: 'phone', sortable: false, align: 'center', width: 100},
            {name: 'email', index: 'email', sortable: false, align: 'center', width: 80},
            {
                name: 'createTime', index: 'createTime', sortable: false, align: 'center', width: 100,
                formatter: function (x) {
                    return ChangeDateFormat(x);
                }
            },
            {
                name: 'updateTime', index: 'updateTime', sortable: false, align: 'center', width: 100,
                formatter: function (x) {
                    return ChangeDateFormat(x);
                }
            },
            {
                name: 'id', index: 'id', sortable: false, resizable: false, align: 'center', width: 100,
                formatter: function (x,y,z) {
                    return '<a class="btn btn-primary btn-xs" onclick="sysUserDetail(\''+x+'\');">详情</a> ' +
                        '<a class="btn btn-primary btn-xs" onclick="andOrupdateSysUser(\''+x+'\');">修改</a> ' +
                        '<a class="btn btn-primary btn-xs" onclick="deleteSysInfo(\''+x+'\',\''+z['loginName']+'\');">删除</a>';
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
            root: "result.list",       //当前页的list数据集合
            total: "result.pages",     //总页数
            page: "result.pageNum",    //当前页码
            records: "result.total"    //总数据量
        },

        //数据来源地址
        url: dataUrl
    }).jqGrid('setGridWidth', $(".ibox-content").width());

    //设置grid宽度为自适应,适应比例根据每列设置的宽度
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', $(".ibox-content").width());
    });
}




//查询
$("#querySysUsers").bind('click',function(){
    querySysUsers();
});

//查询
function querySysUsers() {
    var reloadParams = {
        url: dataUrl,
        postData: setPostData()
    };
    $(grid_selector).jqGrid("setGridParam", reloadParams).trigger("reloadGrid",[{ page: 1 }]);
}




//新增
$("#addSysUser").bind('click',function(){
    andOrupdateSysUser();
});

//新增或修改用户
function andOrupdateSysUser(sysUserId) {

    parent.layer.open({
        type: 2,
        title: '新增/修改系统用户',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['500px', '500px'],
        content: webConfig.webUrl + '/andOrupdateSysUserView?sysUserId=' + sysUserId,
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
        area: ['768px', '400px'],
        content: webConfig.webUrl + "/manager/sysUserDetail?sysUserId="+sysUserId,
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
        url = webConfig.webUrl + '/deleteSysUser';
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
    $(grid_selector).jqGrid("setGridParam", reloadParams).trigger("reloadGrid", [{page: 1}]);
}
