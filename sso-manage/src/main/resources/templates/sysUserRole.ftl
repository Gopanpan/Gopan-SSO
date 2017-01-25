<#include "template/frame.ftl">

<#macro title_info>用户角色管理</#macro>

<#macro frame_content>
<div class="ibox">
    <div class="ibox-heading">
        <div class="ibox-title">
            <h5>用户角色管理</h5>
        </div>
        <div class="ibox-oper">
            <form class="form-inline" role="form">
                <input type="hidden"  id="subStationStatus">
                <div class="form-group">
                    <label class="control-label" for="sysInfoId">平台选择：</label>
                    <select class="form-control input-sm" onchange="query()" id="sysInfoId" name="sysInfoId">
                    </select>
                    <label class="control-label">用户名称:</label>
                    <input type="text" value="" id="userName">
                    <input class="btn btn-primary false btn-xs" type="button" value="查询" id="findUserRole"/>
                    <input class="btn btn-primary false btn-xs" type="button" value="批量分配角色"
                           id="batchAssignRole"/>
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
<script type="text/javascript" src="/static/js/custom/sysUserRole.js"></script>
</#macro>
<@frame_html/>