<#include "template/frame.ftl">

<#macro title_info>用户角色批量分配</#macro>

<#macro frame_content>
<div class="ibox-content form-horizontal">
    <input type="hidden" value="${userIds}" id="userIds">
    <input type="hidden" value="${sysInfoId}" id="sysInfoId">
    <input type="hidden" value="${subStationStatus}" id="subStationStatus">
    <div class="form-group">

        <div align="center"></br>
            系统角色：
            <select name="roleSelect" id="roleSelect" style="width:100px;">
            </select>
        </div>

        <div align="center" id="subStationSelect" style="display:none"></br>
            选择子站：
            <select name="subStationId" id="subStationId" style="width:100px;">
            </select>
        </div>

    </div>
    </br></br>
    <div class="form-group">
        <div class="col-sm-10 text-center">
            <button type="button" class="btn btn-primary" id="btnUpdate">提交</button>
            <button type="button" class="btn btn-primary" id="btnCancel">取消</button>
        </div>
    </div>
</div>
</#macro>

<#macro business_js>
<script type="text/javascript" src="/static/js/custom/sysUserRoleBatchAssign.js"></script>
</#macro>
<@frame_html/>