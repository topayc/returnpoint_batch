package com.returnp.batch.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

@Component
public class CommonUtil {

	public static ApplicationContext ctx;
	
	/**
     * Make Spring inject the application context
     * and save it on a static variable,
     * so that it can be accessed from any point in the application. 
     */
    @Autowired
    private void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;       
    }
    
	public static Calendar todayCalendar = Calendar.getInstance();		// 달력 관련	
							
	private static SimpleDateFormat formatter = new SimpleDateFormat ("yyyy.MM.dd");	//날짜 포멧	
	private static SimpleDateFormat tformatter = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");	//날짜 포멧
	
	
	/**
	 * 오늘 yyyy.MM.dd
	 */
	public static String getToday() throws Exception {
		
		// return formatter.format(todayCalendar.getTime());
		Calendar calNow = Calendar.getInstance();
		//calNow.set( todayCalendar.get( Calendar.YEAR ) , todayCalendar.get( Calendar.MONTH ), todayCalendar.get( Calendar.DATE ) );
				
		return formatter.format(calNow.getTime());		
	}
	
	/**
	 * 현재  yyyy.MM.dd hh:mm:ss
	 */
	public static String getTestToday() throws Exception {
		
		// return formatter.format(todayCalendar.getTime());
		Calendar calNow = Calendar.getInstance();
		//calNow.set( todayCalendar.get( Calendar.YEAR ) , todayCalendar.get( Calendar.MONTH ), todayCalendar.get( Calendar.DATE ) );
				
		return tformatter.format(calNow.getTime());		
	}
	
	/**
	 *  일주일 전
	 */
	public static String getDayMinus7() throws Exception {
		
		Calendar cal7 = Calendar.getInstance();
		cal7.set( cal7.get( Calendar.YEAR ) , cal7.get( Calendar.MONTH ), cal7.get( Calendar.DAY_OF_MONTH ) -7 );
		
		return formatter.format(cal7.getTime());
	}
	
	/**
	 *  한달 전
	 */
	public static String getMonthMinus1() throws Exception {
		
		Calendar calMonth1 = Calendar.getInstance();
		calMonth1.set( calMonth1.get( Calendar.YEAR ) , calMonth1.get( Calendar.MONTH ) -1 , calMonth1.get( Calendar.DAY_OF_MONTH ) );
		
		return formatter.format(calMonth1.getTime());
	}
	/**
	 *  해당 월의 1일
	 */
	public static String getMonth1() throws Exception {
		
		Calendar calMonth1 = Calendar.getInstance();
		calMonth1.set( calMonth1.get( Calendar.YEAR ) , calMonth1.get( Calendar.MONTH ), 1 );
		
		return formatter.format(calMonth1.getTime());
	}	
	
	/**
	 *  Current Request
	 */
	public static HttpServletRequest getCurrentRequest() throws Exception {
	    ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	     
	    HttpServletRequest servletRequest = sra.getRequest();
	    return servletRequest;
	}
	
	/**
	 *  Push Message
	 */	
	
	public static void sendMessage(String message) throws Exception {
		
		WebSocketClient client = new StandardWebSocketClient();
		
		List<Transport> transponts = new ArrayList<Transport>();
		transponts.add(new WebSocketTransport(client));		
		SockJsClient sockJsClient = new SockJsClient(transponts);
		
		TextWebSocketHandler handler = new TextWebSocketHandler();
		WebSocketSession session = sockJsClient.doHandshake(handler, "ws://localhost/echo.returnp").get();
		session.sendMessage(new TextMessage(message));
		
	}
	
}
