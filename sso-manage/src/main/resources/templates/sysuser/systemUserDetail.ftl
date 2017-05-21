<#include "frame.ftl">

<#macro title_info>系统管理员详情页</#macro>

<#macro frame_content>
<div id="generalForm" class="ibox-content form-horizontal" >
    <div class="form-group">
        <label class="col-sm-4 control-label ">ID
            <label class="text-danger"></label>
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="" class="form-control required" value="${id}" name="id" id="id" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label ">登陆名
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="" class="form-control required" name="loginName" id="loginName"/>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">真实名称
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="" class="form-control" id="realName" name="realName">
        </div>
    </div>


    <div class="form-group">
        <label class="col-sm-4 control-label">性别
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
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="phone" name="phone">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">电子邮件
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder="" class="form-control" id="email" name="email">
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
        <label class="col-sm-4 control-label">最后登录时间
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="lastLogin" name="lastLogin">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">最后登录IP
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="lastIp" name="lastIp">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">创建人
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="createUserName" name="createUserName">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">创建时间
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="createTime" name="createTime">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">修改人
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="updateUserName" name="updateUserName">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">修改时间
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="updateTime" name="updateTime">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">启用状态
        </label>
        <div class="col-sm-5">
            <input type="text" placeholder=""  class="form-control"  id="available" name="available">
        </div>
    </div>


    <div class="form-group">
        <div class="col-sm-15 text-center">
            <button type="button" class="btn btn-primary" id="btnCancel">确定</button>
        </div>
    </div>
</div>
</#macro>

<#macro business_js>
<script type="text/javascript" src="/static/js/sso/sysuser/systemUserDetail.js"></script>
</#macro>
<@frame_html/>
