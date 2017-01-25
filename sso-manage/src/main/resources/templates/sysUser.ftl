<#include "template/frame.ftl">

<#macro title_info>系统用户详情页</#macro>

<#macro frame_content>
<div class="ibox-content form-horizontal">
    <input type="hidden" value="${sysUserId}" id="sysUserId">

    <div class="form-group">
        <label class="col-sm-4 control-label">用户名：</label>

        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="sysUserName">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">密码：</label>

        <div class="col-sm-6">
            <input type="password" placeholder="" class="form-control" value="" id="sysUserPassword">
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
<script type="text/javascript" src="/static/js/custom/sysUser.js"></script>
</#macro>
<@frame_html/>
