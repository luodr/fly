

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
			 //核心Api
	        FileItemFactory factory = new DiskFileItemFactory();
	        ServletFileUpload fileUpload = new ServletFileUpload(factory);
	        
	        //判断是否是muitipart/form-data类型
	        if(!ServletFileUpload.isMultipartContent(req)) {
	            resp.getWriter().println("表单的enctype属性不是multipart/form-data类型");
	        }
	        //设置单个文件上传大小 5M
	        fileUpload.setFileSizeMax(5*1024*1024);
	        
	        //设置总上传文件大小(有时候一次性上传多个文件，需要有一个上限,此处为10M)
	        fileUpload.setSizeMax(10*1024*1024);
	        
	        //设置上传监听器[参数为自定义的监听器]
	        fileUpload.setProgressListener(new ListenerUploadProgress());
	        String realFileName =null;
	        String fileName=null;
	        String path="./static/img/";
	        Map param = new HashMap();   
	        //解析请求
	        try {
	            List<FileItem> parseRequest = fileUpload.parseRequest(req);
	            //获取数据
	            for (FileItem fileItem : parseRequest) {
	                //判断数据类型是不是普通的form表单字段
	                if(!fileItem.isFormField()) {
	                    //上传文件
	                     fileName = fileItem.getName();
	                    InputStream fileStream = fileItem.getInputStream();
	                    //定义保存的父路径
	                    String parentDir = this.getServletContext().getRealPath(path);
	                 
	                    //使用UUID+文件名的方式，避免文件重名
	                     realFileName = UUID.randomUUID().toString()+"-"+fileName;
	                    //创建要保存的文件
	                    File file = new File(parentDir,realFileName);
	                    //判断文件夹是否存在
	                    if(!file.getParentFile().exists()) {
	                        //创建文件夹[多级文件夹]file.madir是创建单一文件夹
	                        file.getParentFile().mkdirs();
	                    }
	                    //创建输出流
	                    OutputStream out = new FileOutputStream(file);
	                    //创建字节缓存
	                    byte[] buffer = new byte[1024];
	                    int len = -1;
	                    //一次读取1kb(1024byte),返回-1表明读取完毕
	                    while((len = fileStream.read(buffer))!=-1) {
	                        //一次写入1kb(1024byte)
	                        out.write(buffer,0, len);
	                    }
	                   
	                    //冲刷流资源
	                    out.flush();
	                    //关闭流
	                    out.close();
	                    fileStream.close();
	                    
	                }else {
	                    //普通字段
	                    
	                    //字段名
	                    String fieldName = fileItem.getFieldName();
	                    //字段值
	                    String fieldValue = fileItem.getString();
	                    fieldValue=new String(fieldValue.getBytes("ISO-8859-1"),"UTF-8");
	                    System.out.println(fieldName+":"+fieldValue);
	                    param.put(fieldName, fieldValue);
	                    
	                }
	               
	          	
	          	
	        
	                	
	             
	                	
	                }
	            System.out.println("文件名"+path+"/"+fileName);
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
