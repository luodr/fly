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
import com.ldr.bean.User;

import com.ldr.util.ConnectManager;

public class LikeDao {
	private static LikeDao userDao = null;
	   private LikeDao() {
	   }
	  
	 public static LikeDao getInstance() {
	      if (userDao == null) {
	    	 userDao = new LikeDao();
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
	public  boolean insertLike(CommentLike like ){
		Connection con=ConnectManager.getConnection();
	
		 ResultSet re= null;
		try{
			String sql ="insert into commentLike(articleID,user,commentID) values(?,?,?)";
				 PreparedStatement	pst = con.prepareStatement(sql);
				pst.setInt(1,like.getArticleID());
				pst.setString(2, like.getUser());
				pst.setInt(3,like.getCommentID());
			
				if (pst.executeUpdate()>0) {
					return true;
				}
		}catch (Exception e) {
		
		}finally{
			closeConnection(con);
		}
		return false;
    }    
	/**
	 * 获取用户在文章的点赞
	 * @param user
	 * @param ArticleID
	 */
  public ArrayList<Integer> findByUserAndArticle(String user,int ArticleID){
    String sql="select commentID from commentlike WHERE user=? and articleID=?";
	Connection con=ConnectManager.getConnection();
	ArrayList<Integer> list=new ArrayList<Integer>();
	 ResultSet re= null;
	try{
	
			 PreparedStatement	pst = con.prepareStatement(sql);
			 pst.setString(1, user);
			pst.setInt(2,ArticleID);
			re=pst.executeQuery();
			while (re.next()) {
				list.add(re.getInt("commentID"));
			}
		
	}catch (Exception e) {
	
	}finally{
		closeConnection(con);
	}
	  return list;
  }
}
