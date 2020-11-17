
//加载页面
$(document).ready(function () {
    var id = $("#id").val();
    if(id){
        $.ajax({
            url: ssoSystem_findById_url,
            type: 'POST',
            dataType: 'json',
            data: {id: id}
        })
            .done(function (result) {
                serviceValidateErrorTipsMessage(result);

                if (result.code == "20000" && result.data) {
                    $("#name").val(result.data.name).attr("disabled",true);
                    $("#systemCode").val(result.data.systemCode);
                    $("#available").val(result.data.available);
                    $("#detailExplain").val(result.data.detailExplain);
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
        name : $("#name").val(),
        systemCode : $("#systemCode").val(),
        available : $("#available").val(),
        detailExplain: $("#detailExplain").val()
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
            .done(function (result) {
                serviceValidateHandleCurrent(result,true,false);
                reload();
            });
        }
        else return false;
    });


//取消
$('#btnCancel').click(function () {
    parent.layer.closeAll('iframe');
});
