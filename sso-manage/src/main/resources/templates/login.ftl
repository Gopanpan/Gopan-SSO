<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>sso - 管理中心</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="static/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="static/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="static/css/animate.css" rel="stylesheet">
    <link href="static/css/style.css?v=b74376f" rel="stylesheet">

    <script>if (window.top !== window.self) {
        window.top.location = window.location;
    }</script>

    <style type="text/css">
        .name {
            font-size: 20px;
            font-weight: 700;
            color: RGB(0, 144, 218);
            font-family: "Microsoft YaHei", arial, courier new, courier, "\5b8b\4f53", monospace;
        }
    </style>

</head>

<body class="bt_login_bgcolor">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div class="bt_login_boxs">
        <div>
            <img src="static/img/logo.png">
        </div>
        <form class="m-t my-form">
            <div class="form-group bt_login_inputs">
                <input id="J-username" type="text" class="form-control" placeholder="用户名" required>
            </div>
            <div class="form-group bt_login_inputs">
                <input id="J-password" type="password" class="form-control" placeholder="密码"
                       required>
            </div>
            <a id="J-submit" type="submit" class="btn btn-primary block full-width bt_login_bnt">登
                录</a>
            <p id="J-error" class="text-muted bt_text_center"></p>
        </form>
    </div>
</div>
<!-- 全局js -->
<script src="static/js/jquery.min.js?v=2.1.4"></script>
<script src="static/js/bootstrap.min.js?v=3.3.6"></script>

<script src="static/js/plugins/layer/layer.min.js?v=9a34e40"></script>

<script type="text/javascript" src="static/js/custom/config.js"></script>
<script type="text/javascript" src="static/js/custom/mylib.js"></script>

<script type="text/javascript" src="static/js/custom/login.js"></script>
</body>
</html>