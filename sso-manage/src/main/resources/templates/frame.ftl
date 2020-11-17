<#macro frame_html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gopan</title>
    <link rel="shortcut icon" href="/static/img/favicon.ico">

    <link href="/static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap/bootstrap-select.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap/bootstrapValidator.min.css" rel="stylesheet">


    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/jqgrid/ui.jqgrid.css" rel="stylesheet">
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">

    <link href="/static/css/zTree/zTreeStyle.css" rel="stylesheet" type="text/css">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <@frame_content/>
</div>
<script src="/static/js/jquery/jquery.min.js"></script>

<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/bootstrap/bootstrap-select.js"></script>
<script src="/static/js/bootstrap/bootstrapValidator.min.js"></script>


<script src="/static/js/jquery/metisMenu/jquery.metisMenu.js"></script>
<script src="/static/js/jquery/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/static/js/jquery/jqgrid/jquery.jqGrid.min.js"></script>
<script src="/static/js/jquery/jqgrid/i18n/grid.locale-cn.js"></script>

<script src="/static/js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/js/zTree/jquery.ztree.all-3.5.min.js"></script>

<script src="/static/js/ckeditor/ckeditor.js"></script>

<script src="/static/js/layer/layer.js"></script>

<script src="/static/js/hAdmin/hAdmin.js"></script>

<script src="/static/js/My97DatePicker/WdatePicker.js"></script>



<script src="/static/js/sso/jqgrid-utils.js"></script>
<script src="/static/js/sso/layer-utils.js"></script>
<script src="/static/js/sso/ssoUrlConfig.js"></script>
<script src="/static/js/sso/ssoPublic-utils.js"></script>
    <@business_js/>
</body>

</html>
</#macro>

<#macro frame_content></#macro>
<#macro business_js></#macro>

