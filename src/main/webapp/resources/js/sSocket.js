var sock;

function startSocket() {

    sock = new SockJS("echo.returnp");
    sock.onmessage = WebSocket.onMessage;
    sock.onclose = WebSocket.onClose;
    sock.onopen = WebSocket.onOpen;
    
}
var WebSocket = {

    send : {
    "message" : "no message",
    "email" : "",
    "target" : [ "*" ],// [1,4],//특정대상
    "userName" : "",
    "userSeq" : ""
    },
    onOpen : function() {
    
        __w('socket opened.');
    
        if ($.isFunction(window.callbackOnOpen))
            callbackOnOpen();
    
    },
    sendMessage : function() {
    
        __w("message ready.and send...");
        sock.send(this.send);
    },
    // evt 파라미터는 웹소켓을 보내준 데이터다.(자동으로 들어옴)
    onMessage : function(evt) {
    
        var s = evt.data;
        callbackOnMessage(s);
        /*var data = JSON.parse(s);
    
        __w(data.message);
        __w(data.email);
        __w(data.target);
        __w(data.userName);
        __w(data.userSeq);
    
        var target = data.target;
    
        // 타겟이 모든 사용자 이거나 배열에 해당 유저의 키가 있는 경우
    
        if ($.inArray("*", target) != -1 || $.inArray($(".page-info").data("seq"), target) != -1) {
    
            if ($.isFunction(window.callbackOnMessage))
                
        }*/
    
    },
    onClose : function(evt) {
    	
        var s = evt.data;
        __w(evt.data);
        $("#data").append("socket closed.");
    
        // 재시도
        // sock = new SockJS("/echo.asap");
    
        if ($.isFunction(window.callbackOnClose))
            callbackOnClose();
    
    }
};

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
       return null;
    }
    else{
       return results[1] || 0;
    }
}



