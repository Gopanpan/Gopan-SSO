<#include "frame.ftl">

<#macro frame_content>

<div id="generalForm" class="ibox-content form-horizontal">
    <input type="hidden" value="${id}" id="id">

    <div class="form-group">
        <label class="col-sm-4 control-label">系统名称：</label>

        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" id="name" name="name">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">系统编码：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" id="systemCode" name="systemCode">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">是否可用：</label>
        <div class="col-sm-6">
            <select class="selectpicker" data-live-search="true" title="--请选择--" id="available" name="available">
                <option value='true' >可用</option>
                <option value='false' >禁用</option>
            </select>
        </div>

    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">系统描述：</label>
        <div class="col-sm-6 ckeditor">
            <textarea id="detailExplain" name="detailExplain" cols="20" rows="2" class="ckeditor"></textarea>
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
    <script type="text/javascript" src="/static/js/sso/ssosystem/ssoSystem.js"></script>
</#macro>
<@frame_html/>
