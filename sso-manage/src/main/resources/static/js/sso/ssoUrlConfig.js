/**
 * 系统访问路径配置
 */
var base_serve_url_config = "http://localhost:3333";


/**
 * 登陆登出、系统连接相关
 */
var user_login_url = base_serve_url_config + "/loginSysUser";
var user_login_out_url = base_serve_url_config + "/loginOut";
var system_main_url = base_serve_url_config + "/ssoMain";


var sysUser_list_url = base_serve_url_config + "/sysUser/systemUserList";
var sysUser_addAUpdate_url = base_serve_url_config + "/sysUser/addAUPdateSysUser";


var sysInfo_list_url = base_serve_url_config + "/sysInfoPageList";


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
