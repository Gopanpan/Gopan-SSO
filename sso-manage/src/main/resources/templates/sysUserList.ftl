<#include "template/frame.ftl">

<#macro title_info>用户列表页</#macro>

<#macro frame_content>
<div class="ibox">
    <div class="ibox-heading">
        <div class="ibox-title">
            <h5>用户列表</h5>
        </div>
        <div class="ibox-oper">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="control-label" for="sysInfoId">用户名：</label>
                    <input id="userName" name="userName" value="" maxlength="20"/>
                    <input class="btn btn-primary false btn-xs" type="button" value="查询" id="querySysUsers"/>
                    <input class="btn btn-primary false btn-xs" type="button" value="新增" id="addSysUser"/>
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
<script type="text/javascript" src="/static/js/custom/sysUserList.js"></script>
</#macro>
<@frame_html/>
