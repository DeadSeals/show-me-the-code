
Page = {

    initPageNav : function (nowpage,totalpage) {
        var preTplEn = `<li>
                            <a href="javascript:void(0)" aria-label="Previous" onclick="Page.initPage(${nowpage-1});return false;">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>`;

        var preTplDis = `<li class="disabled">
                          <span>
                            <span aria-hidden="true">&laquo;</span>
                          </span>
                         </li>`;

        var nextTplEn = `<li>
                          <a href="javascript:void(0)" aria-label="Next" onclick="Page.initPage(${nowpage+1});return false;">
                            <span aria-hidden="true">&raquo;</span>
                          </a>
                         </li>`;

        var nextTplDis = `<li class="disabled">
                           <span>
                            <span aria-hidden="true">&raquo;</span>
                           </span>
                         </li>`;

        var pageNavHtml = "";
        if(nowpage == 1){
            pageNavHtml += preTplDis;
        } else {
            pageNavHtml += preTplEn;
        }
        for(var i=1;i<=totalpage;i++){
            var pageTpl = `<li><a href="javascript:void(0)" onclick="Page.initPage(${i});return false;">${i}</a></li>`;
            var pageTplAct = `<li class="active"><span>${i}</span></li>`;
            if(i == nowpage){
                pageNavHtml += pageTplAct;
            } else {
                pageNavHtml += pageTpl;
            }
        }
        if(nowpage == totalpage){
            pageNavHtml += nextTplDis;
        } else {
            pageNavHtml += nextTplEn;
        }
        $("#page-ul").html(pageNavHtml);
    },

    leftPlusZero : function(str,len){
        if(len === undefined){
            len = 2;
        }
        var strlen = str.toString().length;
        var targetlen = len;
        if(targetlen <= strlen){
            return str;
        }
        var plusCount = targetlen-strlen;
        var zerostr = "";
        for(var i=0;i<plusCount;i++){
            zerostr += 0;
        }
        return zerostr+str;
    },

    formatDate : function (date) {
        var year = Page.leftPlusZero(date.getFullYear(),4);
        var month = Page.leftPlusZero(date.getMonth()+1);
        var day = Page.leftPlusZero(date.getDate());
        var hour = Page.leftPlusZero(date.getHours());
        var minute = Page.leftPlusZero(date.getMinutes());
        var second = Page.leftPlusZero(date.getSeconds());
        var dateStr = `${year}-${month}-${day} ${hour}:${minute}:${second}`;
        return dateStr;
    },

    encodeHtml : function (str) {
        return $('<span/>').text(str).html();
    },

    initEvent : function () {
        $("body").off();
        $("#submit-btn").on("click",Page.submit);
    },

    submit : function () {
        $("#submit-error-area").hide();
        $("#sender-group").removeClass("has-error");
        $("#content-group").removeClass("has-error");
        $("#sender-err1").hide();
        $("#sender-err2").hide();
        $("#content-err1").hide();
        $("#content-err2").hide();

        var sender = $("#sender").val();
        var content = $("#content").val();
        if(sender == null || sender.trim() == ""){
            $("#sender-group").addClass("has-error");
            $("#sender-err1").slideDown();
            return;
        }
        if(content == null || content.trim() == ""){
            $("#content-group").addClass("has-error");
            $("#content-err1").slideDown();
            return;
        }
        if(sender.trim().length > 20){
            $("#sender-group").addClass("has-error");
            $("#sender-err2").slideDown();
            return;
        }
        if(content.trim().length > 500){
            $("#content-group").addClass("has-error");
            $("#content-err2").slideDown();
            return;
        }
        var params = {
            sender : sender,
            content : content
        };
        $.ajax({
            type : 'POST',
            url : '/msgboard/msglist',
            data : params,
            success : function (rsp) {
                if(rsp.status === 1){
                    Page.initPage();
                    $("#sender").val("");
                    $("#content").val("");
                } else {
                    $("#submit-error-msg").html(rsp.msg);
                    $("#submit-error-area").slideDown();
                }
            },
            error : function (rsp) {
                $("#submit-error-msg").html("网络连接失败，请稍候重试");
                $("#submit-error-area").slideDown();
            }
        });
    },

    initPage : function (page,size) {
        if(page == undefined){
            page = 1;
        }
        if(size == undefined){
            size = 10;
        }
        // $("#msgdiv").html("<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>");
        var params = {
            size : size,
            page : page
        };
        $.ajax({
            type : 'GET',
            url : "/msgboard/msglist",
            data : params,
            success : function (rsp) {
                if(rsp.status === 1){
                    var msglist = rsp.data.msgList;
                    var msgdivhtml = "";
                    for(msgdata of msglist){
                        var msgSender = Page.encodeHtml(msgdata.msgSender);
                        var msgContent = Page.encodeHtml(msgdata.msgContent);
                        var date = Page.formatDate(new Date(msgdata.msgSendtime));
                        var listTep = `<div class="panel panel-default">
                                            <div class="panel-heading">
                                                <strong>${msgSender}</strong> 于 ${date} 留言
                                            </div>
                                            <div class="panel-body">
                                                ${msgContent}
                                            </div>
                                       </div>`;
                        msgdivhtml += listTep;
                    }
                    Page.initPageNav(rsp.data.pageNum,rsp.data.totalPageNum);
                    $("#msgdiv").html(msgdivhtml);
                } else {
                    $("#list-error-msg").html(rsp.msg);
                    $("#list-error-area").slideDown();
                }
            },
            error : function () {
                $("#list-error-msg").html("网络连接失败，请稍候重试");
                $("#list-error-area").slideDown();
            }
        });
    }
};


$(function () {
    Page.initPage();
    Page.initEvent();
});
