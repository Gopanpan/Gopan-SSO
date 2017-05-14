<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <title>SSO - 主页</title>

    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="static/img/favicon.ico">
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">

<div id="wrapper">
    <#include "navigation.ftl">
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">

        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header"><a
                        class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post"
                          action="">
                        <div class="form-group">
                            <input type="text" placeholder="" class="form-control"
                                   name="top-search" id="top-search">
                        </div>
                    </form>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <!-- /.dropdown -->
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"
                           aria-expanded="false">
                            <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                        </a>
                        <ul class="dropdown-menu dropdown-user">

                            <li class="divider"></li>
                            <li>
                                <a href="#" onclick="logOut()"><iclass="fa fa-sign-out fa-fw"></i> 退出</a>
                            </li>
                        </ul>
                        <!-- /.dropdown-user -->
                    </li>
                    <!-- /.dropdown -->
                </ul>
            </nav>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe id="J_iframe" width="100%" height="100%" src="/manage/welcome"frameborder="0" >
            </iframe>
        </div>
    </div>
    <!--右侧部分结束-->
</div>
<script src="static/js/jquery/jquery.min.js"></script>
<script src="static/js/bootstrap/bootstrap.min.js"></script>
<script src="static/js/jquery/metisMenu/jquery.metisMenu.js"></script>
<script src="static/js/jquery/slimscroll/jquery.slimscroll.min.js"></script>
<script src="static/js/layer/layer.js"></script>
<script src="static/js/hAdmin/hAdmin.js"></script>
<script src="static/js/sso/config.js"></script>
<script src="static/js/index.js"></script>
</body>
</html>
