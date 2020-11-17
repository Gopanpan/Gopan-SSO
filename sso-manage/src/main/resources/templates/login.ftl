<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>sso - 管理中心</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link href="/static/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/font-awesome.css" rel="stylesheet">
    <link href="/static/css/animate.css" rel="stylesheet">
    <link href="/static/css/style.css" rel="stylesheet">

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
            <img src="/static/img/logo.png">
            <div>Copyright &copy;2017 sso All Rights Reserved.</div>
        </div>
        <form class="m-t my-form">
            <div class="form-group bt_login_inputs">
                <input id="J-username" type="text" class="form-control" placeholder="用户名" required>
            </div>
            <div class="form-group bt_login_inputs">
                <input id="J-password" type="password" class="form-control" placeholder="密码" required>
            </div>
            <a id="J-submit" type="submit" class="btn btn-primary block full-width bt_login_bnt">登录</a>
            <p id="J-message" class="text-muted bt_text_center"></p>
        </form>
    </div>
</div>
<!-- 全局js -->
<script src="/static/js/jquery/jquery.min.js"></script>
<script src="/static/js/bootstrap/bootstrap.min.js"></script>
<script src="/static/js/md5.js"></script>
<script src="/static/js/layer/layer.js"></script>

<script src="/static/js/sso/ssoUrlConfig.js"></script>
<script src="/static/js/sso/login.js"></script>
</body>
</html>