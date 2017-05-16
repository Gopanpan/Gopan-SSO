/**
 * 系统访问路径配置
 */
var webConfig = "http://localhost:3333/manage";


/**
 * 登陆登出、系统连接相关
 */
var user_login_url = webConfig + "/loginSysUser";
var user_login_out_url = webConfig + "/loginOut";
var system_main_url = webConfig + "/ssoMain";


var sysUser_list_url = webConfig + "/sysUser/systemUserList";


var sysInfo_list_url = webConfig + "/sysInfoPageList";


/**
 * jqgrid 公共参数配置
 * @type {{}}
 */
var jqgrid_config = {};
$.extend(jqgrid_config,{
    grid_selector : "#grid-table",
    pager_selector : "#grid-pager",
    pageSize : 10,
    optional_table_rowNum_1 : 10,
    optional_table_rowNum_2 : 30,
    optional_table_rowNum_3 : 50

});
