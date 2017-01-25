<#include "template/frame.ftl">
<#macro title_info>资源详情页面</#macro>

<#macro frame_content>

<div class="ibox-content form-horizontal">

    <input type="hidden" value="${id}" id="id">
    <input type="hidden" value="${pid}" id="pid">
    <input type="hidden" value="${sysInfoId}" id="sysInfoId">
    <div class="form-group">
        <label class="col-sm-4 control-label">资源编码：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="资源编码" class="form-control" value="" id="code">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">资源类型：</label>
        <div class="col-sm-6">
            <select name="type" id="type" class="form-control">
                <option value="">=未选择=</option>
                <option value="menu">菜单</option>
                <option value="page">页面</option>
                <option value="button">按钮</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label"><span style="color: red">★ </span>资源名称：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="资源名称" class="form-control" required="" value=""
                   id="name">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">资源URL：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="资源URL" class="form-control" value="" id="url">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">显示位置：</label>
        <div class="col-sm-6">
            <select name="location" id="location" class="form-control">
                <option value="">=未选择=</option>
                <option value="top">top</option>
                <option value="left">left</option>
                <option value="right">right</option>
                <option value="bottom">bottom</option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">列操作：</label>
        <div class="col-sm-3">
            <input type="text" placeholder="列字段" class="form-control" value="" id="columnEn">
        </div>
        <div class="col-sm-3">
            <input type="text" placeholder="列描述" class="form-control" value="" id="columnCn">
        </div>

    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label"></label>
        <div class="col-sm-2">
            <button type="button" class="btn btn-default btn-rounded" id="addColumn">添加</button>
        </div>
        <div class="col-sm-2">
            <button type="button" class="btn btn-default btn-rounded" id="deleteColumn">删除</button>
        </div>
        <div class="col-sm-2">
            <button type="button" class="btn btn-default btn-rounded" id="deleteColumns">清空</button>
        </div>
    <#--<div class="col-sm-2">-->
    <#--<button type="button" class="btn btn-primary" id="editColumns">编辑</button>-->
    <#--</div>-->
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">列信息：</label>
        <div class="col-sm-6">
            <textarea placeholder="列信息" disabled="disabled" class="form-control" value=""
                      id="columns" maxlength="1000"></textarea>
            (英文半角逗号分割，不能包含换行等非法字符)
        <#--<input type="text" placeholder="列信息" class="form-control" value="" id="columns">-->
        </div>
        <div class="col-sm-2">
            <button type="button" class="btn btn-default btn-rounded" id="editColumns">编辑</button>
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
<script type="text/javascript" src="/static/js/custom/sysResourceDetail.js"></script>
</#macro>

<@frame_html/>