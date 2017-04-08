<#include "frame.ftl">

<#macro title_info>系统管理员详情页</#macro>

<#macro frame_content>
<div class="ibox-content form-horizontal">
    <input type="hidden" value="${sysUserId}" id="sysUserId">

    <div class="form-group">
        <label class="col-sm-4 control-label">登陆名：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="loginName">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">真实名称：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="realName">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">登陆密码：</label>
        <div class="col-sm-6">
            <input type="password" placeholder="" class="form-control" value="" id="password">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">电话号码：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="phone">
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
<script type="text/javascript" src="static/js/custom/sysuser/systemUser.js"></script>
</#macro>
<@frame_html/>
