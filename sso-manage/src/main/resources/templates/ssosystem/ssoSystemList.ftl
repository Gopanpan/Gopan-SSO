<#include "frame.ftl">

<#macro title_info>系统列表页</#macro>

<#macro frame_content>
<div class="ibox">
    <div class="ibox-heading">
        <div class="ibox-title">
            <h5>应用系统列表</h5>
        </div>
        <div class="ibox-oper">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="control-label" >应用系统名称：</label>
                    <input id="name" name="name" value="" maxlength="20"/>
                    &nbsp;&nbsp;
                    <label class="control-label">应用系统编码：</label>
                    <input id="systemCode" name="systemCode" value="" maxlength="20"/>
                    &nbsp;&nbsp;
                    <label class="control-label" >是否启用：</label>
                    <select class="selectpicker" data-live-search="true" title="--请选择--" id="available" name="available">
                        <option value='true' >可用</option>
                        <option value='false' >禁用</option>
                    </select>
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
<script type="text/javascript" src="/static/js/sso/ssosystem/ssoSystemList.js"></script>
</#macro>
<@frame_html/>
