

package com.ldr.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import  java.util.*;
import  java.io.*;
import java.sql.Date;
import  java.text.SimpleDateFormat;
import  org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.* ;
import org.apache.commons.fileupload.servlet.*;
import org.json.simple.*;


import com.ldr.dao.*;
import com.ldr.bean.*;
import com.ldr.util.*;


public class ImgUpLoadServlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	         
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		User user=(User)session.getAttribute("user");
		if(user!=null){
			 //����Api
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload fileUpload = new ServletFileUpload(factory);
	        
	        //�ж��Ƿ���muitipart/form-data����
	        if(!ServletFileUpload.isMultipartContent(req)) {
	            resp.getWriter().println("����enctype���Բ���multipart/form-data����");
	        }
	        //���õ����ļ��ϴ���С 5M
	        fileUpload.setFileSizeMax(5*1024*1024);
	        
	        //�������ϴ��ļ���С(��ʱ��һ�����ϴ�����ļ�����Ҫ��һ������,�˴�Ϊ10M)
	        fileUpload.setSizeMax(10*1024*1024);
	        
	        //�����ϴ�������[����Ϊ�Զ���ļ�����]
	        fileUpload.setProgressListener(new ListenerUploadProgress());
	        String realFileName =null;
	        String fileName=null;
	        String path="./static/img/";
	        Map param = new HashMap();   
	        //��������
	        try {
	            List<FileItem> parseRequest = fileUpload.parseRequest(req);
	            //��ȡ����
	            for (FileItem fileItem : parseRequest) {
	                //�ж����������ǲ�����ͨ��form���ֶ�
	                if(!fileItem.isFormField()) {
	                    //�ϴ��ļ�
	                     fileName = fileItem.getName();
	                    InputStream fileStream = fileItem.getInputStream();
	                    //���屣��ĸ�·��
	                    String parentDir = this.getServletContext().getRealPath(path);
	                 
	                    //ʹ��UUID+�ļ����ķ�ʽ�������ļ�����
	                     realFileName = UUID.randomUUID().toString()+"-"+fileName;
	                    //����Ҫ������ļ�
	                    File file = new File(parentDir,realFileName);
	                    //�ж��ļ����Ƿ����
	                    if(!file.getParentFile().exists()) {
	                        //�����ļ���[�༶�ļ���]file.madir�Ǵ�����һ�ļ���
	                        file.getParentFile().mkdirs();
	                    }
	                    //���������
	                    OutputStream out = new FileOutputStream(file);
	                    //�����ֽڻ���
	                    byte[] buffer = new byte[1024];
	                    int len = -1;
	                    //һ�ζ�ȡ1kb(1024byte),����-1������ȡ���
	                    while((len = fileStream.read(buffer))!=-1) {
	                        //һ��д��1kb(1024byte)
	                        out.write(buffer,0, len);
	                    }
	                   
	                    //��ˢ����Դ
	                    out.flush();
	                    //�ر���
	                    out.close();
	                    fileStream.close();
	                    
	                }else {
	                    //��ͨ�ֶ�
	                    
	                    //�ֶ���
	                    String fieldName = fileItem.getFieldName();
	                    //�ֶ�ֵ
	                    String fieldValue = fileItem.getString();
	                    fieldValue=new String(fieldValue.getBytes("ISO-8859-1"),"UTF-8");
	                    System.out.println(fieldName+":"+fieldValue);
	                    param.put(fieldName, fieldValue);
	                    
	                }
	               
	          	
	          	
	        
	                	
	             
	                	
	                }
	            System.out.println("�ļ���"+path+"/"+fileName);
               UserDao.getInstance().updateUserImg(user.getUser(), path+"/"+realFileName);
               user.setImg(path+"/"+realFileName);
	            JSONObject obj = new JSONObject();
	    		obj.put("status", 0);
	    		obj.put("url", path+realFileName);
	         	Writer out=resp.getWriter();
	     		out.write(obj.toJSONString());
	        } catch (FileUploadException e) {
	            e.printStackTrace();
	        }  
			
		}
        
        
        
     
		

	}
	
}
