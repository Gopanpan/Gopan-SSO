
//加载页面
$(document).ready(function () {

    var sysUserId = $("#sysUserId").val();
    if(sysUserId){
        $.ajax({
            url: sysUser_findById_url,
            type: 'POST',
            dataType: 'json',
            data: {sysUserId: sysUserId}
        })
            .done(function (data) {
                serviceValidateErrorTipsMessage(data);

                if (data.code == "20000" && data.result) {
                    $("#loginName").val(data.result.loginName);
                    $("#realName").val(data.result.realName);
                    $("#sex").val(data.result.sex);
                    $("#phone").val(data.result.phone);
                    $("#email").val(data.result.email);
                    $("#birthday").val(data.result.birthday);
                }

            });

    }




    $("#generalForm").bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            loginName:{
                validators: {
                    notEmpty: {
                        message: '登陆名不能为空!'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '登陆名长度必须在6到30之间!'
                    },
                    remote:{
                        message: '该登陆名太抢手了,请换一个试试!',//提示消息
                        delay :  1000,//每输入一个字符，就发ajax请求，服务器压力还是太大，设置2秒发送一次ajax（默认输入一个字符，提交一次，服务器压力太大）
                        type:"POST",
                        url: sysUsr_check_unique_loginName
                    }
                }
            },
            realName:{
                validators: {
                    notEmpty: {
                        message: '真实名不能为空!'
                    }
                }
            },
            sex:{
                validators: {
                    notEmpty: {
                        message: '性别不能为空!'
                    }
                }
            },
            phone:{
                message: 'The phone is not valid',
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    regexp: {
                        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    }
                }
            },
            email:{
                validators: {
                    notEmpty: {
                        message: 'email不能为空!'
                    },
                    emailAddress: {
                        message: 'The input is not a valid email address'
                    }
                }
            }
        }
    });

});


//提交
$('#btnUpdate').click(function () {
        //提交时validate数据是否通过bootstrapValidator的校验
        var bootstrapValidator = $("#generalForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){

            var sysUser = {},url = sysUser_addAUpdate_url;

            sysUser. loginName = $("#loginName").val();
            sysUser.realName = $("#realName").val();
            sysUser.sex = $("#sex").val();
            sysUser.phone = $("#phone").val();
            sysUser.email = $("#email").val();
            sysUser.birthday = $("#birthday").val();

            $.ajax({
                url: url,
                type: 'POST',
                dataType: 'json',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sysUser)
            })
            .done(function (data) {
                serviceValidateHandleCurrent(data,true,false);
            });
        }
        else return false;
    });


//取消
$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});
