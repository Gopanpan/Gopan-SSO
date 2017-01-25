<#include "template/frame.ftl">

<#macro title_info>列信息配置页</#macro>

<#macro frame_content>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <!--功能模块查看-->
        <div class="ibox float-e-margins">
            <div class="ibox-title bt_min_h40">
                <h5>列信息配置</h5>
            </div>
        </div>
        <div class="col-sm-5">
            <input type="hidden" value="${roleId}" id="roleId">
            <input type="hidden" value="${sysInfoId}" id="sysId">
            <!--功能查看树形结构-->
            <div class="ibox-content clearPadding">
                <!--ztree-->
                <ul id="tree" class="ztree"></ul>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="ibox-content clearPadding">
                <input type="hidden" value="" id="roleColumnId">
            <span id="columns">
            </span>
            </div>
            <!--操作-->
            <div class="ibox float-e-margins">
                <div class="ibox-content clearfix" id="workChoose">
                    <button type="button" class="btn btn-primary" id="updateLimit">确定</button>
                    <button type="button" class="btn btn-primary" id="cancle">关闭</button>
                </div>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro business_js>
<script type="text/javascript" src="/static/js/custom/sysRoleColumns.js"></script>
</#macro>
<@frame_html/>