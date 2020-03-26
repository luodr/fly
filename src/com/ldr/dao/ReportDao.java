package com.ldr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.ldr.bean.Article;
import com.ldr.bean.Collect;
import com.ldr.bean.CommentLike;
import com.ldr.bean.Report;
import com.ldr.bean.User;

import com.ldr.util.ConnectManager;

public class ReportDao {
	private static ReportDao userDao = null;
	   private ReportDao() {
	   }
	  
	 public static ReportDao getInstance() {
	      if (userDao == null) {
	    	 userDao = new ReportDao();
	     }
	       return userDao;
	   }
	    

	/**
	 * 关闭连接
	 * @param con 需要关闭的连接
	 */
	public void closeConnection(Connection con){
		try {
			if(con!=null)
			{
				con.close();
			}
		} catch (Exception e2) {
			
		}
	}
  /**
   *  点赞评论
   * @param collect
   * @return
   */
	public  boolean insertReport(Report report ){
		Connection con=ConnectManager.getConnection();
	
		 ResultSet re= null;
		try{
			String sql ="INSERT into report(user,content,articleID)  values(?,?,?)";
				 PreparedStatement	pst = con.prepareStatement(sql);
			     pst.setString(1, report.getUser());
			     pst.setString(2, report.getContent());
			     pst.setInt(3, report.getArticleID());
				if (pst.executeUpdate()>0) {
					return true;
				}
		}catch (Exception e) {
		
		}finally{
			closeConnection(con);
		}
		return false;
    }  
}
	
