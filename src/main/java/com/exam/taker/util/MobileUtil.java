package com.exam.taker.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MobileUtil {
	private static String username="jyjggfwpt";
	private static String pwd="89291faaec9387390c19ee7acc2a0ab0ca2f8faf";
	//private static String wsdldxmethod="http://sms.xjbt.cn:8060/SmsWebService/dorado/webservice/SmsMT";
	private static String wsdldxmethod="http://49.119.98.25:8060/SmsWebService/dorado/webservice/SmsMT";
	private static final Logger LOGGER = LoggerFactory.getLogger(MobileUtil.class);
	
	public static void main(String[] args) throws IOException {
		sendMobileMsg("18086017966","兵团中考考生信息管理系统，测试");
	}
	
	public static void sendMobileMsg(String mobile,String content) throws IOException {
		String xmlDx=buildXmlDx(mobile,content);
		callWebService(wsdldxmethod,xmlDx);
	}
	
	
	public static  String  callWebService(String wsdl,String xml) throws IOException
	{
		System.setProperty("sun.net.client.defaultConnectTimeout","20000");
        System.setProperty("sun.net.client.defaultReadTimeout","20000");
        
        // URL连接 公共方法构建http请求头
        URL url = new URL(wsdl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
        conn.setRequestProperty("Content-Type","text/xml; charset=UTF-8");
        conn.setRequestProperty("SOAPAction", "");
           
        conn.setRequestProperty("Content-Length", String.valueOf(xml.getBytes().length));
        conn.setRequestProperty("Host", "sms.xjbt.cn:8060");
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
        conn.setDoOutput(true);
        conn.setDoInput(true); 
        conn.setConnectTimeout(20000);
        // 请求输入内容 将输入的xml写入流
        OutputStream output = conn.getOutputStream();
        output.write(xml.getBytes());
        output.flush();
        output.close();
        // 请求返回内容 接收请求返回的数据流
        InputStream is=conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String str = null;
        while((str = br.readLine())!=null){  
            sb.append(str + "\n");
        }
        LOGGER.info("result is="+sb.toString());
        br.close();
        isr.close();
        return sb.toString();
	}
	
	public static String buildSecurityHead()
	{
		//构建请求头 （适用于发短息方法）以满足usertoken 认证
		String xml = "";
		xml += "<soapenv:Header>\n";
		xml += "<wsse:Security soapenv:actor=\"\" soapenv:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n";
		xml += "<wsse:UsernameToken xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">\n";
		xml += "<wsse:Username xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"+username+"</wsse:Username>\n";
		xml += "<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\">"+pwd+"</wsse:Password>\n";
		xml += "</wsse:UsernameToken>\n";
		xml += "</wsse:Security>\n";
		xml += "</soapenv:Header> \n";
		return xml;
	}
	
	public static String buildXmlDx(String mobile,String content)
	{
		
		
		String xml = "";
		 
		xml += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://www.xj9gg.com/ws\"> \n";
		xml +=buildSecurityHead();

		xml += "<soapenv:Body>\n";
		xml += "<ws:SmsRequest> \n";
		
		xml +="<ws:Sn>"+username+"</ws:Sn>\n";
		xml +="<ws:Pwd>"+pwd+"</ws:Pwd>\n";
		xml +="<ws:Mobile>"+mobile+"</ws:Mobile>\n";
		xml +="<ws:Content>"+content+"</ws:Content>\n";
		xml += "<ws:ext>1</ws:ext>\n";
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		xml += "<ws:stime>"+sdf.format(new Date())+"</ws:stime>\n";
		xml += "<ws:Rrid>1</ws:Rrid>\n";
		
		
		xml += "</ws:SmsRequest>\n";
		xml += "</soapenv:Body>\n";
		xml += "</soapenv:Envelope>\n";
		LOGGER.info(xml);
		return xml;
		
	}
	
	
	
	public static String buildXml(String username,String password)
	{
		
		String xml = "";
		 
		xml += "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://www.xj9gg.com/ws\" >\n";//xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" 
		xml += "<soapenv:Header/>\n";
		xml += "<soapenv:Body>\n";
		xml += "<ws:UserRequest>\n";
		xml += "<ws:username>"+username+"</ws:username>\n";
		xml += "<ws:password>" + password + "</ws:password>\n";
		xml += "</ws:UserRequest>\n";
		xml += "</soapenv:Body>\n";
		xml += "</soapenv:Envelope>\n";
		LOGGER.info(xml);
		return xml;
		
	}
	
	
	
	
	
	public static String getRandomPasswordNum(int length) {
		StringBuilder buffer = new StringBuilder("0123456789");
		StringBuilder sb = new StringBuilder();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
	
	public static String generateInvitation(int tactivityId, int userId,
			String ucName, String sponsor, String activityName,
			String beginDate, String endDate, String evalBeginDate,
			String evalEndDate, String username, String password, int resCount,
			String sponsorUcName,String cyberRoot) {

		StringBuilder sbuff = new StringBuilder();
		sbuff.append("尊敬的：" + ucName + "专家，您好!");
		sbuff.append(sponsorUcName + " 举办的活动“" + activityName + "” 诚邀您评审活动资源!");
		sbuff.append("活动时间：" + beginDate + "~" + endDate+",");
		sbuff.append("应邀截止时间：" + evalBeginDate +",");
		sbuff.append("评审截止时间：" + evalEndDate+",");
		sbuff.append("您的专家账号/默认密码：" + username + "/"+",");
		sbuff.append("访问地址："+cyberRoot+"dist/index.html#/reviewList/"+ tactivityId+",");
		sbuff.append("如无法登录，请联系管理员为您重置密码。");
		return sbuff.toString();
	}
	
	public static String generateRecommendUser(String ucName, String resName) {

		StringBuilder sbuff = new StringBuilder();
		sbuff.append("尊敬的：" + ucName + "教师，您好!");
		sbuff.append("恭喜您参与活动作品：" + resName + "已被推优!");
		return sbuff.toString();
	}
	
	public static String generateRegisterUser(String ucName, String inviteName) {

		StringBuilder sbuff = new StringBuilder();
		sbuff.append("尊敬的：" + inviteName + "教师，您好!");
		sbuff.append(ucName+"通过您的邀请成功注册到平台");
		return sbuff.toString();
	}
}
