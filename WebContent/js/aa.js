$(function () {
    $(".modelname").click(function () {//给页面中的modelname的标签加上click事件
        var objTD = $(this);
        //alert("heheh");
        //点击后，内容变成文本框
        var oldText = $(this).text();  //保存原来的文本
        var input =$( "<input type='text'  value='" + oldText + "'/>");//文本框的html代码
        objTD.html(input);  //td变为文本
        //设置文本框的点击事件失效
        input.click(function () {
            return false;
        });
        //设置文本框的样式
        input.css("border-width", 0);  //边框为0
        input.css("margin", 0);
        input.css("padding", 0);
        input.css("color","black");
        //input.height(objTD.height);//文本框的高度为当前td的高度
        //input.width(objTD.width);
        input.trigger("focus").trigger("select");//全选
        //文本框失去焦点的时候变为文本
        input.blur(function () {
            var newText = $(this).val();
            var input_blur = $(this);
            //objTD.html(newText);
            //当原来的名称与修改后的名称不同时才进行数据库提交操作
            if (oldText != newText) {

                //获取该模块名称对应的ID
                var modelID = $.trim(objTD.prev().text());
                // AJAX异步更改数据库
                var url = "../handler/changeModelName.ashx?modelname=" + encodeURI(encodeURI(newText)) + "&modelID=" + modelID + "&t=" + new Date();
                $.get(url, function (data) {
                    if (data == "false") {
                        $("#test").text("模块名称修改失败，请检查是否重复");
                        input_blur.trigger("focus").trigger("select");  //文本框全选
                    }
                    else {
                        $("#test").text("");
                        objTD.html(newText);
                    }
                });
            }
            else {
                //前后文本一样，将文本宽变成标签
                objTD.html(newText);
            }
        });
        //在文本框中按下键盘某建
        input.keydown(function () {
            var jianzhi = event.keyCode;
            var input_keydown = $(this);
            switch (jianzhi) {
                case 13:   //按下回车，保存修改
                    var newText = input_keydown.val();//修改后的名称
                    //当原来的名称与修改后的名称不同时才进行数据库提交操作
                    if (oldText != newText) {
                        //获取该模块名称对应的ID
                        var modelID = $.trim(objTD.prev().text());
                        // AJAX异步更改数据库
                        var url = "../handler/changeModelName.ashx?modelname=" + encodeURI(encodeURI(newText)) + "&modelID=" + modelID + "&t=" + new Date();
                        $.get(url, function (data) {
                            if (data == "false") {
                                $("#test").text("模块名称修改失败，请检查是否重复");
                                input_keydown.trigger("focus").trigger("select");  //文本框全选
                            }
                            else {
                                $("#test").text("");
                                objTD.html(newText);
                              
                            }
                        });
                    }
                    else {
                        //前后文本一样，将文本宽变成标签
                        objTD.html(newText);
                    }
                    break;
                case 27:    //按下Esc，取消修改，吧文本框变成文本
                    $("#test").text("");
                    objTD.html(oldText);
                    break;
            }
        });
    });
   
});
//屏蔽Enter按键
$(document).keydown(function (event) {
    switch (event.keyCode) {
        case 13: return false;
    }
});