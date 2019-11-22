<#include "frame.ftl">

<#macro title_info>用户列表页</#macro>

<#macro frame_content>
<div class="ibox">
    <div class="ibox-heading">
        <div class="ibox-title">
            <h5>应用系统管理用户列表 </h5>
        </div>
        <div class="ibox-oper">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="control-label" >登陆名：</label>
                    <input id="loginName" name="loginName" value="" maxlength="20"/>
                    &nbsp;&nbsp;
                    <label class="control-label" >真实名：</label>
                    <input id="realName" name="realName" value="" maxlength="20"/>
                    &nbsp;&nbsp;
                    <label class="control-label" >电话号码：</label>
                    <input id="phone" name="phone" value="" maxlength="20"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="btn btn-primary false btn-xs" type="button" value="查询" id="generalQuery"/>
                    &nbsp;&nbsp;
                    <input class="btn btn-primary false btn-xs" type="button" value="新增" id="generalAdd"/>
                </div>
            </form>
        </div>
        <div class="ibox-content clearPadding">
            <div class="jqGrid_wrapper">
                <table id="grid-table"></table>
                <div id="grid-pager"></div>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro business_js>
<script type="text/javascript" src="/static/js/sso/ssouser/ssoUserList.js"></script>
</#macro>
<@frame_html/>