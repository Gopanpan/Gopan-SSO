//常量
var dataUrl = webConfig.webUrl + '/sysInfoPageList',
    grid_selector = "#grid-table",
    pager_selector = "#grid-pager",
    pageSize = 20;


//加载页面
$(document).ready(function () {
    $.jgrid.defaults.styleUI = 'Bootstrap';
    var reloadParams = {
        url: dataUrl
    };
    initJqgrid();
});

//初始化表格
function initJqgrid() {
    $(grid_selector).jqGrid({
        height: 'atuo',
        multiselect: true,
        hidegrid: false,
        colNames: ['系统Id', '<div align="center"><span>系统名称</span></div>', '<div align="center"><span>系统编码</span></div>',  '<div align="center"><span>是否可用</span></div>','<div align="center"><span>系统描述</span></div>','<div align="center"><span>操作</span></div>'],
        colModel: [
            {name: 'id', index: 'id',hidden: true },
            {name: 'name', index: 'name', sortable: false, align: 'center', width: 70},
            {name: 'systemCode', index: 'createTime', sortable: false, align: 'center', width: 60},
            {name: 'available', index: 'updateTime', sortable: false, align: 'center', width: 50,
                formatter: function (x) {
                    return availableFormat(x);
                }
            },
            {name:'detailExplain',index:'detailExplain',sortable:false,align:'center',width:300},
            {
                name: 'id', index: 'id', sortable: false, resizable: false, align: 'center', width: 60,
                formatter: function (x, y, z) {
                    return '<a class="btn btn-primary btn-xs" onclick="andOrupdateSysInfo(\'' + x + '\',\'' + z['name'] + '\');">编辑</a> ' +
                        '<a class="btn btn-primary btn-xs" onclick="deleteSysInfo(\'' + x + '\',\'' + z['name'] + '\');">删除</a>';
                }
            }
        ],
        rowList: [10, 20, 30],
        rowNum: pageSize,
        viewrecords: true,
        pager: pager_selector,
        prmNames: {
            page: 'pageIndex', rows: "pageSize", order: null, search: null, sort: null, nd: null, sidx: null
        },
        mtype: 'post',
        datatype: "json",
        jsonReader: {
            //是否允许乱序显示数据,true:不可以
            repeatitems: true,
            root: "result.list",       //当前页的list数据集合
            total: "result.pages",     //总页数
            page: "result.pageNum",    //当前页码
            records: "result.total"    //总数据量
        },
        url: dataUrl,
        loadComplete: function (data) {
            serviceErrorValidate(data);
        }
    }).jqGrid('setGridWidth', $(".ibox-content").width());

    //设置grid宽度为自适应,适应比例根据每列设置的宽度
    $(window).on('resize.jqGrid', function () {
        $(grid_selector).jqGrid('setGridWidth', $(".ibox-content").width());
    });
}

//新增系统页面
$("#addSysInfo").bind('click', function () {
    andOrupdateSysInfo();
});

//新增或修改系统
function andOrupdateSysInfo(sysInfoId) {
    parent.layer.open({
        type: 2,
        title: '新增系统',
        shadeClose: true,
        shade: [0.5],
        maxmin: true, //开启最大化最小化按钮
        area: ['600px', '600px'],
        content: webConfig.webUrl + '/andOrUpdateSysInfoView',
        end: function () {
            reload();
        }
    });
}

//删除系统
function deleteSysInfo(sysInfoId, sysInfoName) {

    layer.confirm('确定要删除系统:' + sysInfoName, {
        btn: ['确定', '取消'] //按钮
    }, function () {
        confirmDelete(sysInfoId);
    }, function () {
        parent.layer.closeAll('iframe');
    });

}

function confirmDelete(sysInfoId) {

    var sysInfo = {},
        url = webConfig.webUrl + '/deleteSystemInfo';
    sysInfo.id = sysInfoId;

    $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(sysInfo)
        })
        .done(function (data) {
            if (data) {
                if (data.code == "10000") {
                    /*layer.alert(data.message, {icon: 1}, function () {
                     parent.layer.closeAll('dialog');
                     return;
                     });*/
                    layer.alert(data.message, {icon: 1});
                    reload();
                } else {
                    layer.alert(data.message, {icon: 2});
                }
            }
        });
}

//刷新页面
function reload() {
    var reloadParams = {
        url: dataUrl
    };
    $(grid_selector).jqGrid("setGridParam", reloadParams).trigger("reloadGrid", [{page: 1}]);
}
