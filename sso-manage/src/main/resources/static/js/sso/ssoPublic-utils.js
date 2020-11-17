/**
 * 左侧导航页面加载
 */
$(function () {
    //菜单点击
    // J_iframe
    $(".J_menuItem").on('click', function () {
        var url = $(this).attr('href');
        $("#J_iframe").attr('src', url);
        return false;
    });
});







/*
* 
* 根据名称获取url参数
* 
*/
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURIComponent(r[2]); return null;
}


/*
*
* 对String对象添加toJson方法
* 
*/
String.prototype.toJson=function(){
	return (new Function("return " + this))();
};

// 日期格式转换
function changeDateFormat(val) {
    if (val) {
        var date = new Date(val);
        //如果是1900年，则代表数据库数据为空
        if (date.getFullYear() == 1900) {return ;}
        //月份为0-11，所以+1，月份小于10时补个0
        var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
        var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var min = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var s = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
        if ((date.getFullYear() + "-" + month + "-" + currentDate + " " + hour + ":" + min + ":" + s) == "1-01-01 08:00:00") {
            return '';
        } else {
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hour + ":" + min + ":" + s;
        }
    }
    return '';
}


/**
 * 性别格式化
 * 性别(0女,1男,2未知)
 */
function sexFormat(sex){

    if(sex == 0){return '女'}
    if(sex == 1){return '男'}
    else{return '未知'}
}

/**
 * boolean 格式化
 * @param avaliable
 * @returns {*}
 */
function availableFormat(avaliable){
    if(avaliable == true){return '是'}
    if(avaliable == false){return '否'}
    if(avaliable == undefined){return ''}
}


/**
 * 客户端校验
 * @param data
 */
function frontErrorValidate(data){
    layer.msg("页面校验失败<br/>异常内容："+data,{icon:5,time:3000});
    return false;
}


/**
 * 验证服务器端处理的返回结果
 * @param result           服务器端返回的处理结果数据
 * @param successOperate   服务器端处理结果为成功后的页面操作 为true是执行回调函数，false不执行
 * @param errorOperate     服务器端处理结果为失败后的页面操作 为true是执行回调函数，false不执行
 */
function topSuccessMessage (result,successOperate,errorOperate) {

    if (result) {
        if (result.code == "20000") {
            layer.msg(result.message, {icon: 1,title:'服务器返回成功',time:3000}, function () {
                if(successOperate ==true){
                    parent.layer.closeAll('iframe');
                }
            });
        } else {
            var message = "异常代码   "+result.code+"<br/>异常摘要   "+result.message+"<br/>详细信息   "+ result.detailMessage;
            layer.msg(message, {icon: 5,title:'服务器返回异常',time:3000}, function () {
                if(errorOperate ==true){
                    parent.layer.closeAll('iframe');
                }
            });

        }
    }

}




/**
 * 数组去重
 * @param  {Array} arr 需要去重的数组
 * @return {Array}    去重后的数组
 */
function unique(arr) {
    var result = [];
    if(arr instanceof Array){
        var hash = {},
            len = arr.length;
        for (var i = 0; i < len; i++){
            if (!hash[arr[i]]){
                hash[arr[i]] = true;
                result.push(arr[i]);
            } 
        }
    }else{
        console.log('请输入一个数组');
    }
    return result;
}

/**
 * 正则验证
 * @param  {string/number} val  要验证的值
 * @param  {string} type 要验证的类型；email/url/mobile/phone/qq/ip/chinese/idcard/number/
 * @return {bool}      true/false
 */
function validator(val,type){
    var regexStr= {
        //邮箱正则
        email: /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i,
        //密码
        loginpassword: /<[!/]/,
        registpassword: /^[~!@\#\$%\^&\*_\+\-=\da-z\.]*$/gi,
        //url 地址正则
        url: /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
        //移动手机号码
        mobile: /^(14[7]|13[0-9]|15[0-9]|18[0-9]|17[0-9])\d{8}$/,
        //座机电话号码
        phone: /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/,
        //QQ验证正则
        qq: /^[1-9]\d{4,9}$/,
        //ip地址验证
        ip: /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/,
        //汉字验证(有瑕疵，生僻字验证不全，努力更新中....)
        chinese: /^[\u4e00-\u9fa5]+$/,
        //身份证正则
        idcard: /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/gi,
        //数字
        nodigits: /^\d+$/,
        //电话
        tel: /^(17[0-9]|14[7]|13[0-9]|15[0-9]|18[0-9])\d{8}$/i,
        number: /(^[1-9]\d*$)|(^([1-9]\d*|0)(\.\d*)?$)/,
        isInt: /^([1-9][0-9]*)|0$/,
        date: /Invalid|NaN/
    };

    return regexStr[type].test(val);
}

(function ($, window) {
    $.fn.extend({
        /**
         * 模拟百度搜索下拉 通过获取selector的data-kay属性获取对应的真实值
         * @param  {object} options 参数对象
         * url:string 查询地址
         * type:string 类型 默认post 参看jQuery.ajax的对应参数
         * dataType:string 数据类型 默认json 参看jQuery.ajax的对应参数
         * count: 5 查询条数 默认5条 最多10条
         * @return 查询后处理的元素列表 
         */
        stickyNote:function(options){
            var defaultVal = {
                url:'',
                type:'post',
                dataType:'json',
                count:5,
                width:110
            },
            stinckNoteEle=document.getElementById('stickyNoteEle');
            $(this).on('input',function() {
                var _this=$(this),
                    showEle='';
                defaultVal = $.extend(defaultVal, options);
                if(!defaultVal.url){return;}
                var text=$(this).val();
                if(!text){return;}
                if(defaultVal.count<=0){return;}

                if(!stinckNoteEle){
                    $(_this).wrap('<div id="stickyNoteEle"></div>');
                }

                $.ajax({
                    url: defaultVal.url,
                    type: defaultVal.type,
                    dataType: defaultVal.dataType,
                    data: {taskName:$(_this).val()},
                    success:function(data){
                        if(data){
                             if(data.resList){
                                showEle='<ul style="top:'+($(_this).height()-1)+'px;width:'+(defaultVal.width || $(_this).width())+'px;">';
                                $('head').append('<style>#stickyNoteEle{position: relative;background-color:#fff;z-index:99;}#stickyNoteEle ul{position: absolute;top:10px;left:0;overflow:hidden;background-color:#fff;border:1px solid #19aa8d;}#stickyNoteEle ul li{padding:2px 5px;}#stickyNoteEle ul li:hover{background-color: #19aa8d;color:#fff;}</style>');

                                for(var i=0;i<arrObj.length;i++){
                                    if(defaultVal.count>10){
                                        break;
                                    }
                                    showEle+='<li data-key="'+arrObj[i]['key']+'" >'+arrObj[i]['value']+'</li>';
                                }
                                showEle+='</ul>';
                            }
                        }
                    },
                    error:function(data){
                        return data;
                    }
                });

               
                
                $(_this).after(showEle);
                $(stinckNoteEle).find('li').on('click',function(){
                    var _that=$(this),
                        _key=$(_that).attr('data-key');
                    $(_this).attr('data-key',_key);
                    $(_this).val($(this).html());

                    $(stinckNoteEle).children('ul').remove();

                });

                $(stinckNoteEle).on('mouseleave',function(){
                    $(stinckNoteEle).children('ul').remove();
                });

            });

            // $(this).on('blur',function(){
            //     $(stinckNoteEle).children('ul').remove();
            // });
        }
    });
})(jQuery, window);


//小tips
(function($) {
    function ToolTips(elem, options){
        this.elem = elem;
        this.set = options;
        //this.obj = null;
    }
    ToolTips.prototype = {
        addAnimation: function(){
            switch(this.set.animation){
                case 'none':
                    break;
                case 'fadeIn':
                    this.obj.addClass('animated fadeIn');
                    break;
                case 'flipIn':
                    switch(this.set.gravity){
                        case 'top':
                            this.obj.addClass('animated flipInTop');
                            break;
                        case 'bottom':
                            this.obj.addClass('animated flipInBottom');
                            break;
                        case 'left':
                            this.obj.addClass('animated flipInLeft');
                            break;
                        case 'right':
                            this.obj.addClass('animated flipInRight');
                            break;
                    }
                    break;
                case 'moveInLeft':
                    this.obj.addClass('animated moveLeft');
                    break;
                case 'moveInTop':
                    this.obj.addClass('animated moveTop');
                    break;
                case 'moveInBottom':
                    this.obj.addClass('animated moveBottom');
                    break;
                case 'moveInRight':
                    this.obj.addClass('animated moveRight');
                    break;
            }
        },
        close:function(){
            this.obj.remove();
        },
        setPosition:function(){
            var setPos = {};
            var pos = { x: this.elem.offset().left, y: this.elem.offset().top };
            var wh = { w: this.elem.outerWidth(), h: this.elem.outerHeight() };
            var rightTmp = ( pos.x + wh.w / 2 ) + this.obj.outerWidth() / 2 ;
            var leftTmp = ( pos.x + wh.w / 2 ) - this.obj.outerWidth() / 2 ;
            switch(this.set.gravity){
                case 'top':
                    if(rightTmp > $(window).width() ){
                        setPos = {
                            x: pos.x + wh.w - this.obj.outerWidth(),
                            y: pos.y - this.obj.outerHeight() - this.set.distance
                        };
                        this.obj.find(".tips-" + this.set.gravity).css("left", this.obj.outerWidth() - wh.w/2 + "px")
                    }else if( leftTmp < 0 ){
                        setPos = {
                            x: pos.x,
                            y: pos.y - this.obj.outerHeight() - this.set.distance
                        };
                        this.obj.find(".tips-" + this.set.gravity).css("left", wh.w/2 + "px")
                    }else{
                        setPos = {
                            x: pos.x - (this.obj.outerWidth() - wh.w)/2,
                            y: pos.y - this.obj.outerHeight() - this.set.distance
                        };
                    }
                    break;
                case 'bottom':
                    if(rightTmp > $(window).width() ){
                        setPos = {
                            x: pos.x + wh.w - this.obj.outerWidth(),
                            y: pos.y + wh.h + this.set.distance
                        };
                        this.obj.find(".tips-" + this.set.gravity).css("left", this.obj.outerWidth() - wh.w/2 + "px")
                    }else if( leftTmp < 0 ){
                        setPos = {
                            x: pos.x,
                            y: pos.y + wh.h + this.set.distance
                        };
                        this.obj.find(".tips-" + this.set.gravity).css("left", wh.w/2 + "px")
                    }else{
                        setPos = {
                            x: pos.x - (this.obj.outerWidth() - wh.w)/2,
                            y: pos.y + wh.h + this.set.distance
                        };
                    }
                    break;
                case 'left':
                    setPos = {
                        x: pos.x - this.obj.outerWidth() - this.set.distance,
                        y: pos.y - (this.obj.outerHeight() - wh.h)/2
                    };
                    break;
                case 'right':
                    setPos = {
                        x: pos.x + wh.w + this.set.distance,
                        y: pos.y - (this.obj.outerHeight() - wh.h)/2
                    };
                    break;
            }
            this.obj.css({"left": setPos.x + "px", "top": setPos.y + "px"});
        },
        setEvent:function(){
            var self = this;
            if(self.set.events =="click" || self.set.events =="onclick"){
                self.obj.on("click", function(e){
                    e.stopPropagation();
                })
                $(document).click(function(){
                    self.obj.remove();
                });
            }
            if(self.set.events =="mouseover" || self.set.events =="onmouseover" || self.set.events =="mouseenter"){
                this.elem.on("mouseout, mouseleave",function(){
                    self.close();
                });
            }
        },
        setConfirmEvents:function(){
            var self = this;
            var yes = this.obj.find(".tips-yes");
            var no = this.obj.find(".tips-no");
            yes.click(function(){
                if(self.set.onYes(self)==true){
                    self.close();
                };
            });
            no.click(function(){
                self.close();
                self.set.onNo(self);
            });
        },
        addConfirm:function(){
            this.obj.append("<div class='tips-confirm'><button type='button' class='tips-yes'>"
                + this.set.yes +"</button><button type='button' class='tips-no'>" + this.set.no +"</button></div>");
            this.setConfirmEvents();
        },
        setContent:function(){
            this.obj = $("<div id=" + this.set.id + " class='tips-tooltip " + this.set.theme + "'" +
                "style='width:" + this.set.width + "'><div class='tips-con'>" + this.set.contents + "</div>" + "<span class='tips-" + this.set.gravity + "'></span></div>");
            $("body").append(this.obj);
            this.setEvent();
            this.addAnimation();
            if(this.set.confirm==true){
                this.addConfirm();
            }
        },
        init:function(){
            var e = arguments.callee.caller.caller.caller.caller.caller.arguments[0] || $.event.fix(event || window.event)
            this.set.events = e.type;
            var justToolObj = $(".tips-tooltip");
            if(justToolObj){
                justToolObj.remove();
            }
            e.stopPropagation();
            this.setContent();
            this.setPosition();
            var self = this;
            $(window).resize(function(){
                self.setPosition();
            });
        }
    };
    $.fn.ToolTips = function(options){
        var defaults = {
            height:"auto",
            width:"auto",
            contents:'',
            gravity: 'top',  //top, left, bottom, right
            theme: '',//className
            distance:10,
            //events: 'mouseover',
            animation: 'none', //none, fadeIn, flipIn, moveInLeft, moveInTop, moveInBottom, moveInRight
            confirm: false,
            yes: 'OK',
            no: 'Cancel',
            //delay: 1000,
            onYes: function(){},
            onNo: function(){}
        }
        this.each(function(){
            options = $.extend(defaults, options);
            options.id = new Date().getTime();
            var tooltip = new ToolTips($(this), options);
            tooltip.init();
        });
    }
})(jQuery);




