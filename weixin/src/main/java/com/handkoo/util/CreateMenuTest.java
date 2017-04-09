package com.handkoo.util;
 
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import com.handkoo.weixin.menu.entity.Button;
import com.handkoo.weixin.menu.entity.ClickButton;
import com.handkoo.weixin.menu.entity.ComplexButton;
import com.handkoo.weixin.menu.entity.Menu;
import com.handkoo.weixin.menu.entity.ViewButton;

import net.sf.json.JSONObject;

public class CreateMenuTest {
	public static void main(String[] args) throws Exception{
		ClickButton btn1 = new ClickButton();
		btn1.setName("���ո���");
		btn1.setType("click");
		btn1.setKey("V1001_TODAY_MUSIC");
		
		ViewButton btn2 = new ViewButton();
		btn2.setName("���ּ��");
		btn2.setType("view");
		btn2.setUrl("http://www.qq.com/");
		
		ClickButton btn31 = new ClickButton();
		btn31.setName("hello word");
		btn31.setType("click");
		btn31.setKey("V1001_HELLO_WORLD");
		
		ClickButton btn32 = new ClickButton();
		btn32.setName("��һ������");
		btn32.setType("click");
		btn32.setKey("v1001_GOOD");
		
		// ���ϰ�ť
		ComplexButton btn3 = new ComplexButton();
		btn3.setName("�˵�");
		btn3.setSub_button(new Button[]{btn31,btn32});
		
		// �����˵�����
		Menu menu = new Menu();
		menu.setButton(new Button[]{btn1,btn2,btn3});
		
		// ������ת��JSON�ַ���
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		
		// ���濪ʼ���Դ���
		String accessToken = "jw9-F9VD0lCg_O5NUVDtpHhxjnX2e310v4epsBAUMLwWVb7EGtbO41I7kYq2rpdA4Fj_LCb0JLSES_zJ5oMuTDZsuHpYQ0F3W9pNLP2vjutDLc3lOu1kkLRY2B8BMtNqMDGaADAXOR";
		String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		// ��������
		URL url = new URL(menuCreateUrl);
		HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
		// ʹ���Զ�������ι�����
		TrustManager[] tm = {new MyX509TrustManager()};
		SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
		sslContext.init(null, tm, new SecureRandom());
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		httpUrlConn.setSSLSocketFactory(ssf);
		httpUrlConn.setDoInput(true);
		httpUrlConn.setDoOutput(true);
		// ��������ʽ
		httpUrlConn.setRequestMethod("POST");
		
		// �������д�˵�����
		OutputStream outputStream = httpUrlConn.getOutputStream();
		outputStream.write(jsonMenu.getBytes("UTF-8"));
		outputStream.close();
		
		// ȡ��������
		InputStream inputStream = httpUrlConn.getInputStream();
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		// ��ȡ��Ӧ������
		StringBuffer buffer = new StringBuffer();
		String str = null;
		while((str = bufferedReader.readLine())!=null){
			buffer.append(str);
		}
		// �ر���Դ
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		httpUrlConn.disconnect();
		// ����˵������Ľ��
		System.out.println(buffer);
	}
}
