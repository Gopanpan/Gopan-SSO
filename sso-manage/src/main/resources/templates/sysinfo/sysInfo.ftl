<#include "frame.ftl">

<#macro frame_content>

<div class="ibox-content form-horizontal">
    <input type="hidden" value="${sysInfoId}" id="sysInfoId">

    <div class="form-group">
        <label class="col-sm-4 control-label">系统名称：</label>

        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="sysInfoName">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-4 control-label">系统编码：</label>
        <div class="col-sm-6">
            <input type="text" placeholder="" class="form-control" value="" id="sysInfoName">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">是否可用：</label>
        <div class="col-sm-6">
            <input type="radio" name="subStationStatus" value="1" checked='checked'> 可用
            <input type="radio" name="subStationStatus" value="0"> 不可用
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-4 control-label">系统描述：</label>
        <div class="col-sm-6 ckeditor">
            <textarea id="TextArea1" cols="20" rows="2" class="ckeditor"></textarea>
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
    <script type="text/javascript" src="static/js/custom/sysinfo/sysInfo.js"></script>
</#macro>
<@frame_html/>
