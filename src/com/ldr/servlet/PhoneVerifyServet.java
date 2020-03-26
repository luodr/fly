package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONObject;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneVerifyServet  extends HttpServlet{
  @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

	  req.getRequestDispatcher("/static/phoneReg.jsp").forward(req, resp);

}
  
  @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	  JSONObject obj = new JSONObject();
	  resp.setContentType("text/html; charset=UTF-8");
      String PhoneNumbers=req.getParameter("PhoneNumbers");
      
        if(!UserDao.getInstance().verifyUser(PhoneNumbers)){
        	 obj.put("code",1);
      	   obj.put("msg", "�ֻ����ѱ�ע��!");
        }else{
        	
        	  DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "accessKeyId", "accessKeySecret");
              IAcsClient client = new DefaultAcsClient(profile);
              CommonRequest request = new CommonRequest();
              request.setMethod(MethodType.POST);
              request.setDomain("dysmsapi.aliyuncs.com");
              request.setVersion("2017-05-25");
              request.setAction("SendSms");
              request.putQueryParameter("RegionId", "cn-hangzhou");
              request.putQueryParameter("PhoneNumbers", PhoneNumbers);
              request.putQueryParameter("SignName", "sinlo��̳");
              request.putQueryParameter("TemplateCode", "SMS_180946149");
              String vcode="";
              //�������6λ��֤�� 
              for (int i = 0; i < 6; i++) {
                  vcode = vcode + (int)(Math.random() * 9);
              }
              System.out.println("���ɵ���֤��"+vcode);
              request.putQueryParameter("TemplateParam", "{'code':'"+vcode+"\'}");
            
              try {
                  CommonResponse response = client.getCommonResponse(request);
               //   System.out.println(response.getData());
                  Gson gson2=new Gson();
                  //jsonת����
                  YZM yzm= gson2.fromJson(response.getData(), YZM.class);
             
                   if(yzm!=null&&yzm.getCode().equals("OK")){
                	   obj.put("code",0);
                	   obj.put("msg", "��֤���ѷ���");
                	   //����֤��浽seesion  -- ��ʱ����
                	   req.getSession().setAttribute("yzm", vcode);
                	   req.getSession().setAttribute("PhoneNumbers", PhoneNumbers);
                   }else{
                	   obj.put("code",1);
                	   obj.put("msg", "��֤�뷢��ʧ��!");
                   }
             	  
              } catch (ServerException e) {
                  e.printStackTrace();
              } catch (ClientException e) {
                  e.printStackTrace();
              }
              
        	
        }
      
      
	
      try {
			resp.getWriter().println(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
