<#include "template/frame.ftl">

<#macro title_info>角色列表页</#macro>
<#macro frame_content>
<div class="ibox">
    <div class="ibox-heading">
        <div class="ibox-title">
            <h5>系统角色管理</h5>
        </div>
        <div class="ibox-oper">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="control-label" for="sysInfoId">平台选择：</label>
                    <select class="form-control input-sm" onchange="query()" id="sysInfoId" name="sysInfoId">
                    </select>
                    <input class="btn btn-primary false btn-xs" type="button" value="查询" id="findRole"/>
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
<script type="text/javascript" src="/static/js/custom/sysRole.js"></script>
</#macro>
<@frame_html/>
