<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ReturnP</title>
<script type="text/javascript" src="resources/js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>
<script type="text/javascript" src="resources/js/common.js"></script>
<script type="text/javascript" src="resources/js/sSocket.js"></script>
<script language="javascript">
/* var page = {
	init : function() {
	    startSocket();
        
	    $("#sendBtn").click(function(){
            WebSocket.send.message=$("#message").val();
            WebSocket.send.target=["*"];            
            WebSocket.sendMessage();
        });
	}
};
$(document).ready(function() {
	page.init();
});

function callbackOnMessage(s){
	//console.log(s);
	$("#tdMessage").prepend(s + "<br>");
} */
</script>
</head>

<body>

<!-- <input type="text" id="message" value="메시지 보내는 샘플입니다." placeholder="전송할 메시지를 입력하세요..."/> -->
<!-- <input type="button" id="sendBtn" value="전송" /> -->
<div id="data"></div>
<table>
  <tr>
    <td id="tdMessage">
    ${message}
    </td>
  </tr>
</table>


    
</body>
</html>