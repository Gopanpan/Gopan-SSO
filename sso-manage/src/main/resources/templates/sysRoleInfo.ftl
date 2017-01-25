<#include "template/frame.ftl">

<#macro title_info>角色列表页</#macro>

<#macro frame_content>
<div class="ibox-content form-horizontal">
    <input type="hidden" value="${sysInfoId}" id="sysInfoId">
    <input type="hidden" value="${sysRoleId}" id="sysRoleId">

    <div class="form-group">
        <label class="col-sm-4 control-label">角色名称：</label>

        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="sysRoleName">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-10 text-center">
            <button type="button" class="btn btn-primary" id="btnUpdate">提交</button>
            <button type="button" class="btn btn-primary" id="btnCancel">取消</button>
        </div>
    </div>
</div>
</#macro>

<#macro business_js>
<script type="text/javascript" src="/static/js/custom/sysRoleInfo.js"></script>
</#macro>
<@frame_html/>
