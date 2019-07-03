//系统静态资源
//调用方法：$.sysStaticData().urlExt
$.sysStaticData = function () {
    var staticData = {
        urlExt: "/dancer"//系统扩展路径
    };
    return staticData;
}

//加载select模板
$.loadlist=function(id,cid,cname,url){
	$("#"+id+"").bindSelect_work({
		    url: $.sysStaticData().urlExt +url,
		    param: {"rows":9999,"page":1},
			id: cid,
            text:cname
	});
}

//加载select模板
$.loadlist_rows=function(id,cid,cname,url){
	$("#"+id+"").bindSelect_works({
		    url: $.sysStaticData().urlExt +url,
		    param: {"rows":9999,"page":1},
			id: cid,
            text:cname
	});
}
//加载select模板
$.loadlist_rowd=function(id,cid,cname,url){
	$("#"+id+"").bindSelect_workd({
		    url: $.sysStaticData().urlExt +url,
		    param: {"rows":9999,"page":1},
			id: cid,
            text:cname
	});
}
$.configlist=function(){
    $.ajax({
            url: $.sysStaticData().urlExt +"/lvApprovalFlow/queryOneLvLeaveType",
            data:"",
            type: "post",
            dataType: "json",
            async: false,
            success: function (datas) {
           	 $.each(datas.data,function(i,obj) {
           		 var arr = obj['flowStr'].split('前台 ');
           		 var ids=obj['id'];
           		 $("#config").append($("<li id='list'></li>"));
           		 var $one=$("<span></span>").text(arr[0]).append($("<font class='clo_lan'></font>").text(arr[1]));
           		 var $two=$("<span></span>").append($("<a class='btn_sc_x' href='#' onclick=\"deletes('"+ids+"')\" title='删除'></a>"));
           		 $("#list").append($one).append($two);
           		});   	 
         }
           });
}


$.modalAlert = function (content, type) {
    var icon = "";
    if (type == 'success') {
        icon = "fa-check-circle";
    }
    if (type == 'error') {
        icon = "fa-times-circle";
    }
    if (type == 'warning') {
        icon = "fa-exclamation-circle";
    }
    top.layer.alert(content, {
        icon: icon,
        title: "系统提示",
        btn: ['确认'],
        btnclass: ['btn btn-primary'],
    });
}
$.fn.bindSelect_work = function (options) {
    var defaults = {
        id: "id",
        text: "text",
        search: false,
        url: "",
        param: [],
        change: null
    };
    var options = $.extend(defaults, options);
    var $element = $(this);
    if (options.url != "") {
        $.ajax({
            url: options.url,
            data: options.param,
            dataType: "json",
            async: false,
            success: function (data) {
                $.each(data, function (i) {
                	if(data[i].fEnabledmark)
                    $element.append($("<option></option>").val(data[i][options.id]).html(data[i][options.text]));
                });
                $element.on("change", function (e) {
                    if (options.change != null) {
                        options.change(data[$(this).find("option:selected").index()]);
                    }
                    $("#select2-" + $element.attr('id') + "-container").html($(this).find("option:selected").text().replace(/　　/g, ''));
                });
            }
        });
    } else {
        $element.select2({
            minimumResultsForSearch: -1
        });
    }
}

$.fn.bindSelect_works = function (options) {

    var defaults = {
        id: "id",
        text: "text",
        search: false,
        url: "",
        param: [],
        change: null
    };
    var options = $.extend(defaults, options);
    var $element = $(this);
    if (options.url != "") {
        $.ajax({
            url: options.url,
            data: options.param,
            dataType: "json",
            async: false,
            success: function (datas) {
            	var data=datas.rows;
                $.each(data, function (i) {
                	
                    $element.append($("<option></option>").val(data[i][options.id]).html(data[i][options.text]));
                });
                $element.on("change", function (e) {
                    if (options.change != null) {
                        options.change(data[$(this).find("option:selected").index()]);
                    }
                    $("#select2-" + $element.attr('id') + "-container").html($(this).find("option:selected").text().replace(/　　/g, ''));
                });
            }
        });
    } else {
        $element.select2({
            minimumResultsForSearch: -1
        });
    }
}

$.fn.bindSelect_workd = function (options) {

    var defaults = {
        id: "id",
        text: "text",
        search: false,
        url: "",
        param: [],
        change: null
    };
    var options = $.extend(defaults, options);
    var $element = $(this);
    if (options.url != "") {
        $.ajax({
            url: options.url,
            data: options.param,
            dataType: "json",
            async: false,
            success: function (datas) {
            	var data=datas.data;
                $.each(data, function (i) {
                    $element.append($("<option></option>").val(data[i][options.id]).html(data[i][options.text]));
                });
                $element.on("change", function (e) {
                    if (options.change != null) {
                        options.change(data[$(this).find("option:selected").index()]);
                    }
                    $("#select2-" + $element.attr('id') + "-container").html($(this).find("option:selected").text().replace(/　　/g, ''));
                });
            }
        });
    } else {
        $element.select2({
            minimumResultsForSearch: -1
        });
    }
}