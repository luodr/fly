package com.ldr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.ldr.bean.Article;
import com.ldr.bean.User;



public class ArticleDao {
	private static ArticleDao userDao = null;
	   private ArticleDao() {
	   }
	  
	 public static ArticleDao getInstance() {
	      if (userDao == null) {
	    	 userDao = new ArticleDao();
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
	 *  插入文章
	 * @param article 需要插入数据库的文章
	 * @return 插入数据库结果
	 */
	public  boolean insertArticle(Article article){
		Connection con=this.getConnection();
		try{
			String sql ="insert into article(title,content,views,goods,rubbis,creationDate,name,Introduction,coverimage,userName,type,report) values(?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, article.getTitle());
			pst.setString(2, article.getContent());
			pst.setInt(3, 0);
			pst.setInt(4, 0);
			pst.setInt(5, 0);
			pst.setDate(6,article.getCreationDate());
			pst.setString(7, article.getName());
			pst.setString(8, article.getIntroduction());
			pst.setString(9, article.getCoverimage());
			pst.setString(10, article.getUserName());
			pst.setString(11, article.getType());
			pst.setInt(12, 0);
		  if(	pst.executeUpdate()>0){
		    	return true;
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
		 public Article findByID(int itemID){
			 Connection con = null;
			 ResultSet re= null;
			 try{
					 con=this.getConnection();
					String sql ="select * from article where id = ?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, itemID);
					re=pst.executeQuery();
					while (re.next()){
						Article article=new   Article();
						article.setContent(re.getString("content"));
						article.setCoverimage(re.getString("coverimage"));
						article.setCreationDate(re.getDate("creationDate"));
						article.setGoods(re.getInt("goods"));
						article.setId(re.getInt("id"));
						article.setIntroduction(re.getString("introduction"));
						article.setName(re.getString("name"));
						article.setReport(re.getInt("report"));
						article.setTitle(re.getString("title"));
						article.setType(re.getString("type"));
						article.setUserName(re.getString("userName"));
						article.setViews(re.getInt("views"));
						return article;
					}
				}catch (Exception e) {
					e.printStackTrace();
				}finally{
					closeConnection(con);
				}
			 return null;
		 }
		 /**
		  *  添加浏览量
		  * @param itemID
		  */
	public void addView(int itemID){
		 Connection con = null;
		 
			String sql ="update article set views=views+1 where id = ?";
			PreparedStatement pst;
			try {
				 con=this.getConnection();
				pst = con.prepareStatement(sql);
				pst.setInt(1, itemID);
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		
	}
	
	
	public ArrayList<Article> searchArticle(String key){
		 ResultSet re= null;
		 Connection con = null;
		String sql ="select * from article WHERE content LIKE ? or type LIKE  ?";
		key="%"+key+"%";
		ArrayList<Article> arrayList=new ArrayList<Article>();
		PreparedStatement pst;
		try {
			 con=this.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, key);
			pst.setString(2, key);
			re=pst.executeQuery();
			while (re.next()){
				Article article=new   Article();
				article.setContent(re.getString("content"));
				article.setCoverimage(re.getString("coverimage"));
				article.setCreationDate(re.getDate("creationDate"));
				article.setGoods(re.getInt("goods"));
				article.setId(re.getInt("id"));
				article.setIntroduction(re.getString("introduction"));
				article.setName(re.getString("name"));
				article.setReport(re.getInt("report"));
				article.setTitle(re.getString("title"));
				article.setType(re.getString("type"));
				article.setUserName(re.getString("userName"));
				article.setViews(re.getInt("views"));
				arrayList.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return arrayList;
	}
	//SELECT * FROM article ORDER BY  views DESC limit 10;
	/**
	 * 点击率最高的文章
	 * @param num
	 * @return
	 */
	public ArrayList<Article> viewsArticleDESC(int num){
		 ResultSet re= null;
		 Connection con = null;
		String sql ="SELECT * FROM article ORDER BY  views DESC limit ?;";

		ArrayList<Article> arrayList=new ArrayList<Article>();
		PreparedStatement pst;
		try {
			 con=this.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, num);
			re=pst.executeQuery();
			while (re.next()){
				Article article=new   Article();
				article.setContent(re.getString("content"));
				article.setCoverimage(re.getString("coverimage"));
				article.setCreationDate(re.getDate("creationDate"));
				article.setGoods(re.getInt("goods"));
				article.setId(re.getInt("id"));
				article.setIntroduction(re.getString("introduction"));
				article.setName(re.getString("name"));
				article.setReport(re.getInt("report"));
				article.setTitle(re.getString("title"));
				article.setType(re.getString("type"));
				article.setUserName(re.getString("userName"));
				article.setViews(re.getInt("views"));
				arrayList.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return arrayList;
	}
}
