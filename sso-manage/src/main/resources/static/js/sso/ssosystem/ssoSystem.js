
//加载页面
$(document).ready(function () {
    var id = $("#id").val();
    if(id){
        $.ajax({
            url: ssoUser_findById_url,
            type: 'POST',
            dataType: 'json',
            data: {id: id}
        })
            .done(function (data) {
                serviceValidateErrorTipsMessage(data);

                if (data.code == "20000" && data.result) {
                    $("#name").val(data.result.name).attr("disabled",true);
                    $("#systemCode").val(data.result.systemCode);
                    $("#available").val(data.result.available);
                    $("#detailExplain").val(data.result.detailExplain);
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
            name:{
                validators: {
                    notEmpty: {
                        message: '系统名称不能为空!'
                    },
                    stringLength: {
                        min: 6,
                        max: 30,
                        message: '系统名称长度必须在6到30之间!'
                    }
                }
            },
            systemCode:{
                validators: {
                    notEmpty: {
                        message: '系统编码不能为空!'
                    }
                }
            },
            available:{
                validators: {
                    notEmpty: {
                        message: '可用状态不能为空!'
                    }
                }
            },
            detailExplain:{
                message: 'The phone is not valid',
                validators: {
                    notEmpty: {
                        message: '系统描述不能为空!'
                    }
                }
            }
        }
    });

});


function setPostData(){
    return {
        id  : $("#id").val(),
        loginName : $("#loginName").val(),
        realName : $("#realName").val(),
        sex : $("#sex").val(),
        phone: $("#phone").val(),
        email : $("#email").val(),
        birthday : $("#birthday").val()
    }
}



//提交
$('#btnUpdate').click(function () {
        //提交时validate数据是否通过bootstrapValidator的校验
        var bootstrapValidator = $("#generalForm").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){

            $.ajax({
                url: ssoUser_addAUpdate_url,
                type: 'POST',
                dataType: 'json',
                data: setPostData()
            })
            .done(function (data) {
                serviceValidateHandleCurrent(data,true,false);
                reload();
            });
        }
        else return false;
    });


//取消
$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});
