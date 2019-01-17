package com.returnp.batch.common;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler implements InitializingBean {
    private final Logger logger = LoggerFactory.getLogger(EchoHandler.class);
    private Set<WebSocketSession> sessionSet = new HashSet<WebSocketSession>();
   
    
    public EchoHandler() {
        super();
        this.logger.info("create SocketHandler instance!");
    }
    
    // 닫았을때 세션 없앰
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        
        sessionSet.remove(session);
        this.logger.info("remove session!");
    }
    
    // 열었을때 세션 줌
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessionSet.add(session);
       
        this.logger.info("{} add session!", session.getId());        
        super.afterConnectionEstablished(session);
    }
    
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
        
        this.logger.info("receive message:" + message.toString());
    }
    
    /**
     * 클라이언트가 웹소켓서버로 메시지를 전송했을 때 실행되는 메소드
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        
        System.out.println(session.getId() + "로 부터 "+message.getPayload()+" 받음");
        
        for (WebSocketSession ses : this.sessionSet) {
            if (session.isOpen()) {
                try {
                    ses.sendMessage(new TextMessage(/*session.getId().toString()+"->" +*/ message.getPayload()));
                } catch (Exception ignored) {
                    this.logger.error("fail to send message!", ignored);
                }
            }
        }
        
        session.close();
        //session.sendMessage(new TextMessage(session.getId().toString() +":"+ message.getPayload()));        
        
    }
    
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        this.logger.info("web socket was finished by system!");
    }
    
    @Override
    public boolean supportsPartialMessages() {
        this.logger.info("call method!");
        
        return super.supportsPartialMessages();
    }
    
    public void sendMessage(String message) {
        for (WebSocketSession session : this.sessionSet) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage("sessionId:"+session.getId()+"::::::::::::::" + message));
                } catch (Exception ignored) {
                    this.logger.error("fail to send message!", ignored);
                }
            }
        }
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        
    	/*Thread thread = new Thread() {
			int i = 0;
			@Override
			public void run() {
				while(true) {
					try {
						sendMessage("실시간 서버 송신 메시지 : " + i++);
						Thread.sleep(1000);
					} catch(InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}
		};
		
		thread.start();*/
    }
   
}