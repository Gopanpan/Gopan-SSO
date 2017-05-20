
/**
 * jqgrid 公共参数配置
 * @type {{}}
 */
var jqgrid_config = {};
$.extend(jqgrid_config,{
    grid_selector : "#grid-table",
    pager_selector : "#grid-pager",
    pageSize : 10,
    optional_table_rowNum_1 : 20,
    optional_table_rowNum_2 : 30,
    optional_table_rowNum_3 : 50

});


/**
 * 初始化列表数据
 * @param data_url          数据请求地址
 * @param data_colNames     显示的标题
 * @param data_colModel     列属性,和data_colNames的个数和顺序必须对应
 */
function initJqgrid(data_url,data_colNames,data_colModel) {
    $.jgrid.defaults.styleUI = 'Bootstrap';
    $(jqgrid_config.grid_selector).jqGrid({
        height:'atuo',
        multiselect: true,   //是否允许全选
        hidegrid: false,     //是否允许显示/隐藏，只有caption不为空时有效

        rowList: [           //可选显示的行数
            jqgrid_config.optional_table_rowNum_1,jqgrid_config.optional_table_rowNum_2,
            jqgrid_config.optional_table_rowNum_3
        ],
        rowNum: jqgrid_config.pageSize,             //默认显示行数
        viewrecords: true,                          //总条数
        pager: jqgrid_config.pager_selector,        //分页层 id
        mtype: 'post',                              //提交方式，默认get
        postData: setPostData(),
        datatype: "json",                           //数据格式
        jsonReader: {
            repeatitems: true,                      //是否允许乱序显示数据,true:不可以
            root: "result.list",                    //当前页的list数据集合
            total: "result.pages",                  //总页数
            page: "result.pageNum",                 //当前页码
            records: "result.total"                 //总数据量

        },
        url: data_url,                              //数据来源地址

        prmNames: {                                 //分页参数
            page: 'pageIndex', rows: "pageSize", order: null, search: null, sort: null, nd: null, sidx: null
        },
        colNames: data_colNames,                  //显示的标题
        colModel: data_colModel,
        loadComplete: function (data) {
            serviceValidateErrorTipsMessage(data);
        }
    }).jqGrid('setGridWidth', $(".ibox-content").width());

    //设置grid宽度为自适应,适应比例根据每列设置的宽度
    $(window).on('resize.jqGrid', function () {
        $(jqgrid_config.grid_selector).jqGrid('setGridWidth', $(".ibox-content").width());
    });
}


function jqgrid_reload(reloadParams){
    $(jqgrid_config.grid_selector).jqGrid("setGridParam", reloadParams).trigger("reloadGrid", [{page: 1}]);
}