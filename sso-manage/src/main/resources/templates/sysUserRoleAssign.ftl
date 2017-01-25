<#include "template/frame.ftl">

<#macro title_info>用户角色分配页</#macro>

<#macro frame_content>
<div class="ibox-content form-horizontal">
    <input type="hidden" value="${userId}" id="userId">
    <input type="hidden" value="${sysInfoId}" id="sysInfoId">
    <input type="hidden" value="${subStationStatus}" id="subStationStatus">
    <input type="hidden" value="${userRoleId}" id="userRoleId">
    <input type="hidden" value="${roleId}" id="roleId">
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
<script type="text/javascript" src="/static/js/custom/sysUserRoleAssign.js"></script>
</#macro>
<@frame_html/>