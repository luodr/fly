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
import com.ldr.bean.User;



public class CollectDao {
	private static CollectDao userDao = null;
	   private CollectDao() {
	   }
	  
	 public static CollectDao getInstance() {
	      if (userDao == null) {
	    	 userDao = new CollectDao();
	     }
	       return userDao;
	   }
	    
	public  Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8";
			Connection dbCon=DriverManager.getConnection(url,"root","123456");
			return dbCon;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
   *  收藏文章
   * @param collect
   * @return
   */
	public  boolean insertCollect(Collect collect){
		Connection con=this.getConnection();
		String sqlSelect="select id from collect where userName =? and articleID=?";
		 ResultSet re= null;
		try{
			String sql ="insert into collect(userName,articleID) values(?,?)";
			
			 
				 PreparedStatement	pst = con.prepareStatement(sqlSelect);
				pst.setString(1,collect.getUserName());
				pst.setInt(2, collect.getArticleID());
				re=pst.executeQuery();
				if(re.next()){
					 pst = con.prepareStatement(sql);
						pst.setString(1,collect.getUserName());
						pst.setInt(2, collect.getArticleID());	
						 if(pst.executeUpdate()>0){
						    	return true;
						   }
				}
	
	
		 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return false;
    }    
	   /**
	   * 查询文章
	   * @param itemID 文章id
	   * @return  文章所有信息
	   */
		 public ArrayList<Collect> findByUser(String user){
			 Connection con = null;
			 ResultSet re= null;
			 ArrayList<Collect> list=new ArrayList<Collect> ();
			 try{
				con=this.getConnection();
				String sql ="select article.title,collect.*  from collect left join article on collect.articleID=article.id WHERE collect.userName = ?";
				PreparedStatement	pst = con.prepareStatement(sql);
				pst.setString(1, user);
				re =pst.executeQuery();
				while(re.next()){
					 Collect collect=new Collect();
					 collect.setArticleID(re.getInt("articleID"));
					 collect.setTitle(re.getString("title"));
					 collect.setUserName(re.getString("userName"));
					 collect.setId(re.getInt("id"));
					 list.add(collect);
				}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					closeConnection(con);
				}
			 return list;
		 }

}
