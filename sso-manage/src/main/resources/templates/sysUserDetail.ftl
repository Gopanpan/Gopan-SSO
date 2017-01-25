<#include "template/frame.ftl">

<#macro title_info>系统详情页</#macro>

<#macro frame_content>
<div class="ibox-content form-horizontal">
    <input type="hidden" value="${sysUserId}" id="sysUserId">
    <input type="hidden" value="" id="sysUserUid">

    <div class="form-group">
        <label class="col-sm-4 control-label">手机号：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="phone">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">地址：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="address">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">性别：</label>
        <div class="col-sm-6">
            <select name="sex" id="sex" class="form-control">
                <option selected="selected" value="0">请选择</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
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
<script type="text/javascript" src="/static/js/custom/sysUserDetail.js"></script>
</#macro>
<@frame_html/>
