package com.ldr.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

public class PlayServelt extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	
	this.doPost(req, resp);
}
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String das=req.getParameter("das");
	AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do","APP_ID","APP_PRIVATE_KEY","RSA2");
	    AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//����API��Ӧ��request
	    alipayRequest.setReturnUrl("http://sinlo.lsun.net:8080");
	    alipayRequest.setNotifyUrl("http://sinlo.lsun.net:8080");//�ڹ������������û�����֪ͨ��ַ
	     //���ɶ�����
	    String vcode="";
        //�������6λ��֤�� 
        for (int i = 0; i < 6; i++) {
            vcode = vcode + (int)(Math.random() * 9);
        }
	    String out_trade_no=new Date().getTime()+vcode;
	    //���涩����
	    req.getSession().setAttribute("out_trade_no", out_trade_no);
	    alipayRequest.setBizContent("{" +
	        "    \"out_trade_no\":\""+vcode+"\"," +
	        "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
	        "    \"total_amount\":"+das+"," +
	        "    \"subject\":\"������̳\"," +
	        "    \"body\":\"������̳\"," +
	        "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
	        "    \"extend_params\":{" +
	        "    \"sys_service_provider_id\":\"xxxxxxxxx\"" +
	        "    }"+
	        "  }");//���ҵ�����
	    String form="";
	    try {
	        form = alipayClient.pageExecute(alipayRequest).getBody(); //����SDK���ɱ���
	    } catch (AlipayApiException e) {
	        e.printStackTrace();
	    }
	    resp.setContentType("text/html;charset=UTF-8" );
	    resp.getWriter().write(form);//ֱ�ӽ������ı���html�����ҳ��
	    resp.getWriter().flush();
	    resp.getWriter().close();
	}
}
