
//是否移动端
function is_mobile() {
    var regex_match = /(nokia|iphone|android|motorola|^mot-|softbank|foma|docomo|kddi|up.browser|up.link|htc|dopod|blazer|netfront|helio|hosin|huawei|novarra|CoolPad|webos|techfaith|palmsource|blackberry|alcatel|amoi|ktouch|nexian|samsung|^sam-|s[cg]h|^lge|ericsson|philips|sagem|wellcom|bunjalloo|maui|symbian|smartphone|midp|wap|phone|windows ce|iemobile|^spice|^bird|^zte-|longcos|pantech|gionee|^sie-|portalmmm|jigs browser|hiptop|^benq|haier|^lct|operas*mobi|opera*mini|320x320|240x320|176x220)/i;
    var u = navigator.userAgent;
    if (null == u) {
        return true;
    }
    var result = regex_match.exec(u);
    if (null == result) {
        return false
    } else {
        return true
    }
}
//阅读页键盘操作事件
function ReadKeyEvent() {
    var index_page = $("#linkIndex").attr("href");
    var prev_page =  $("#linkPrev").attr("href");
    var next_page = $("#linkNext").attr("href");

    function jumpPage() {
        var event = document.all ? window.event : arguments[0];
        if (event.keyCode == 37) document.location = prev_page;
        if (event.keyCode == 39) document.location = next_page;
        if (event.keyCode == 13) document.location = index_page;
    }

    document.onkeydown = jumpPage;
}



//推荐书籍
function BookVote(article_id) {
    $.get('/modules/article/uservote.php?id=' + article_id + '&ajax_request=1', function (data) {showMsg(data);});
}

//加入书架
function BookCaseAdd(article_id) {
    var url = '/modules/article/addbookcase.php?bid=' + article_id + '&ajax_request=1';
    $.get(url,function(res){showMsg(res);});
}

//加入书签
function BookCaseMark(article_id, chapter_id) {
    var url = '/modules/article/addbookcase.php?bid=' + article_id + '&cid=' + chapter_id + '&ajax_request=1';
    $.get(url,function(res){showMsg(res);});
}
// 底部展示内容
function foot() {
    var date=new Date();
    var year=date.getFullYear();

    document.writeln("<footer>");
    if (!is_mobile()) {
        document.writeln("<p>本站为毕业设计作品，所有章节均由爬虫在免费网站爬取，转载至本站只是为了学习使用，谢谢合作。</p>");
    }
    document.writeln("<p>Copyright &copy; "+year+" <a href=\"https://www.xiaodongbaobao.cn/\">栋阁</a>("+location.host+") All Rights Reserved. 辽ICP备18010061号</p>");
    document.writeln("</footer>");

    // Tool Tip initial
    $("[data-toggle=tooltip]").tooltip();

    // popover initial
    $("[data-toggle=popover]").popover();

    //书籍简介展开收缩
    $("#bookIntroMore").off("click").on("click", function () {
        var that = $(this);
        var isExpand = that.data("isExpand");
        var expandHtml = '展开<i class="fa fa-angle-double-down fa-fw"></i>';
        var shrinkHtml = '收起<i class="fa fa-angle-double-up fa-fw"></i>';
        var normalHeight = 200;

        function setNormal() {
            that.html(expandHtml);
            $("#bookIntro").height(normalHeight);
            that.data("isExpand", "no");
        }

        function setExpand() {
            that.html(shrinkHtml);
            $("#bookIntro").height("auto");
            that.data("isExpand", "yes");
        }

        isExpand == "yes" ? setNormal() : setExpand();
    });
    backToTop();

 
}
//back to top
function backToTop() {
    document.writeln("<div class=\"back-to-top\" id=\"back-to-top\" title='返回顶部'><i class='fa fa-chevron-up'></i></div>");

    var left = ($(document).width() - $(".body-content").width()) / 2 + $(".body-content").width() + 10;
    if(is_mobile()){
        $("#back-to-top").css({right:10,bottom:"30%"});
    }else{
        $("#back-to-top").css({left: left});
    }

    $(window).resize(function () {
        if ($(document).width() - $(".body-content").width() < 100) {
            $("#back-to-top").hide();
			
        } else {
            $("#back-to-top").show();
            var left = ($(document).width() - $(".body-content").width()) / 2 + $(".body-content").width() + 10;

            if(is_mobile()){
                $("#back-to-top").css({right:10,bottom:"30%"});
            }else{
                $("#back-to-top").css({left: left});
            }
        }
    });
	
    var isie6 = window.XMLHttpRequest ? false : true;

    function newtoponload() {
        var c = $("#back-to-top");
		
        function b() {
            var a = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
            if (a > 100) {
                if (isie6) {
                    c.hide();
                    clearTimeout(window.show);
                    window.show = setTimeout(function () {
                        var d = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop;
                        if (d > 0) {
                            c.fadeIn(100);
                        }
                    }, 300)
                } else {
                    c.fadeIn(100);
                }
            } else {
                c.fadeOut(100);
            }
        }

        if (isie6) {
            c.style.position = "absolute"
        }
        window.onscroll = b;
        b()
    }

    if (window.attachEvent) {
        window.attachEvent("onload", newtoponload)
    } else {
        window.addEventListener("load", newtoponload, false)
    }
    document.getElementById("back-to-top").onclick = function () {
        window.scrollTo(0, 0)
    };
}

/*************调试后**************/
//参数获取
function getParam(name) { 
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); 
        var r = window.location.search.substr(1).match(reg); 
        if (r != null) return unescape(r[2]); 
        return null; 
} 


//重定向页面 
function redirectPage(page){
	if(page == null){
		window.location.href="https://www.xiaodongbaobao.cn";//无参数回首页
		return;
	}
	if(page.length>0){
		window.location.href=page;
		return;
	}
}

