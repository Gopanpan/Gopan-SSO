<#include "template/frame.ftl">
<#macro frame_content>
<div class="ibox">
    <div class="ibox-heading">
        <div class="ibox-title">
            <h5>资源信息</h5>
        </div>
        <div class="ibox-oper">
            <form class="form-inline" role="form">
                <div class="form-group">
                    <label class="control-label" for="sysInfoId">平台选择：</label>
                    <select class="form-control input-sm" id="sysInfoId" style="width: 150px"
                            onchange="findResource()">
                    </select>
                </div>

            </form>
        </div>
        <div class="ibox-content clearPadding">
            <!--功能查看树形结构-->
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
</div>
</#macro>
<#macro business_js>
<script type="text/javascript" src="/static/js/custom/sysResource.js"></script>
</#macro>
<@frame_html/>