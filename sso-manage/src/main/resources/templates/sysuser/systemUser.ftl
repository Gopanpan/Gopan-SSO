<#include "frame.ftl">

<#macro title_info>系统管理员详情页</#macro>

<#macro frame_content>
<div id="generalForm" class="ibox-content form-horizontal" >
    <input type="hidden" value="${id}" name="id" id="id"/>

    <div class="form-group">
        <label class="col-sm-4 control-label ">登陆名
            <label class="text-danger">*</label>
        </label>
        <div class="col-sm-5">
            <input type="text" class="form-control required" name="loginName" id="loginName" placeholder="请输入登陆名">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">真实名称
            <label class="text-danger">*</label>
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="请输入真实名称" class="form-control" id="realName" name="realName">
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-4 control-label">性别
            <label class="text-danger">*</label>
        </label>
        <div class="col-sm-5">
           <select class="selectpicker" data-live-search="true" title="--请选择--" id="sex" name="sex">
               <option value="0">女</option>
               <option value="1">男</option>
           </select>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">电话号码
            <label class="text-danger">*</label>
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="请输入电话号码" class="form-control" value="" id="phone" name="phone">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">电子邮件
            <label class="text-danger">*</label>
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="" class="form-control" value="" id="email" name="email">
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-4 control-label">生日：</label>
        <div class="col-sm-5">
            <input name="birthday" id="birthday"  type="text" placeholder="请输入生日" class="form-control"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});"/>

        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-15 text-center">
            <button type="submit" class="btn btn-primary" id="btnUpdate">提交</button>
            <button type="button" class="btn btn-primary" id="btnCancel">取消</button>
        </div>
    </div>
</div>
</#macro>

<#macro business_js>
<script type="text/javascript" src="/static/js/sso/sysuser/systemUserList.js"></script>
<script type="text/javascript" src="/static/js/sso/sysuser/systemUser.js"></script>
</#macro>
<@frame_html/>
