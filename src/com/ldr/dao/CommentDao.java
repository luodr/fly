package com.ldr.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.ldr.bean.Article;
import com.ldr.bean.Comment;
import com.ldr.bean.HotComment;
import com.ldr.bean.CommentLike;
import com.ldr.bean.User;
import com.ldr.bean.UserComment;
import com.ldr.util.ConnectManager;



public class CommentDao {
	private static CommentDao userDao = null;
	   private CommentDao() {
	   }
	  
	 public static CommentDao getInstance() {
	      if (userDao == null) {
	    	 userDao = new CommentDao();
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
	 *  插入评论
	 * @param article 
	 * @return 插入数据库结果
	 */
	public  boolean insertComment(Comment  comment){
		Connection con=ConnectManager.getConnection();
		try{
			String sql ="insert into comment(username,commentcontent,articleid,creationdate,responderName) values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, comment.getUsername());
			pst.setString(2, comment.getCommentcontent());
			pst.setInt(3, comment.getArticleid());
			pst.setTimestamp(4, comment.getCreationdate());
			pst.setString(5, comment.getResponderName());
		  if(pst.executeUpdate()>0){
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
	 *  查询文章的评论
	 * @param id
	 * @return
	 */
	public ArrayList<Comment> findByarticleId(int articleId){
	    ResultSet re=null;
	    Connection con=ConnectManager.getConnection();
		ArrayList<Comment> list=new ArrayList<Comment>();
		try{
			String sql ="select comment.*,`user`.`name`,`user`.img from `comment` inner join  `user`  on comment.username=`user`.`user` WHERE `comment`.articleid=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, articleId);
			System.out.println("查询评论语句："+pst.toString());
		     re=pst.executeQuery();
		     while(re.next()){
		   Comment comment=new Comment();
           comment.setId(re.getInt("id"));
           comment.setUsername(re.getString("username"));
           comment.setCommentcontent(re.getString("commentcontent"));
           comment.setArticleid(re.getInt("articleid"));
           comment.setCreationdate(re.getTimestamp("creationdate"));
           comment.setResponderName(re.getString("responderName"));
           comment.setLike(re.getInt("like"));
           comment.setRead(re.getBoolean("read"));
           comment.setName(re.getString("name"));
           comment.setImg(re.getString("img"));
		   list.add(comment);
			}
		 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
	  return list;	
	}
	
	
	
	public ArrayList<HotComment> findHotUser(){
	    ResultSet re=null;
	    Connection con=ConnectManager.getConnection();
		ArrayList<HotComment> list=new ArrayList<HotComment>();
		try{
			String sql ="select `comment`.username,comment.creationdate,`user`.`name`,`user`.img,count(`comment`.username) as repeat_count from `comment` left join `user` on comment.username=`user`.`user`  group by comment.username  having repeat_count >0  limit 12;";
			PreparedStatement pst = con.prepareStatement(sql);
			
		     re=pst.executeQuery();
		     while(re.next()){
		      HotComment comment=new HotComment();
		      comment.setUsername(re.getString("username"));
		      comment.setCount(re.getInt("repeat_count"));
		      comment.setImg(re.getString("img"));
		      comment.setName(re.getString("name"));
		      list.add(comment);
			}
		 
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
	  return list;	
	}
	
	public boolean addLike(int commentID){
		Connection con=ConnectManager.getConnection();
		try{
			String sql ="update  comment set comment.like=comment.like+1  where id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, commentID);
		  if(pst.executeUpdate()>0){
		    	return true;
		   }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return false;
	}
	
	
	public ArrayList<UserComment> findUserComments(String user,int limit){
		
	

		   ResultSet re=null;
		    Connection con=ConnectManager.getConnection();
			ArrayList<UserComment> list=new ArrayList<UserComment>();
			try{
				String sql ="SELECT comment.username , comment.commentcontent,comment.creationdate,article.title,comment.articleid  FROM comment LEFT JOIN article ON comment.articleid = article.id WHERE comment.username=?  ORDER BY  creationDate DESC limit ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, user);
				pst.setInt(2, limit);
			     re=pst.executeQuery();
			     while(re.next()){
			     UserComment userComment=new UserComment();
			     userComment.setArticleid(re.getInt("articleid"));
			     userComment.setCommentcontent(re.getString("commentcontent"));
			     userComment.setCreationdate(re.getTimestamp("creationdate"));
			     userComment.setTitle(re.getString("title"));
			     list.add(userComment);
				}
			 
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		  return list;	
	}
	/**
	 * 查询别人回复我的信息
	 * @param user
	 * @param limit
	 * @return
	 */
	public ArrayList<UserComment> findMyReply(String user,int limit){
		
		

		   ResultSet re=null;
		    Connection con=ConnectManager.getConnection();
			ArrayList<UserComment> list=new ArrayList<UserComment>();
			try{
				String sql ="SELECT comment.username , comment.commentcontent,comment.creationdate,article.title,comment.articleid,user.name as replyName FROM (comment LEFT JOIN  article ON comment.articleid = article.id ) left join user on comment.username = user.`user`  WHERE comment.responderName=? and  comment.read=0  ORDER BY  creationDate DESC limit ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, user);
				pst.setInt(2, limit);
			     re=pst.executeQuery();
			     while(re.next()){
			     UserComment userComment=new UserComment();
			     userComment.setArticleid(re.getInt("articleid"));
			     userComment.setCommentcontent(re.getString("commentcontent"));
			     userComment.setCreationdate(re.getTimestamp("creationdate"));
			     userComment.setTitle(re.getString("title"));
			     userComment.setReplyName(re.getString("replyName"));
			     list.add(userComment);
				}
			 
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		  return list;	
	}
	/**
	 * 清空未读
	 * @param user

	 */
	public void clearMyReply(String user){
		
		

		   ResultSet re=null;
		    Connection con=ConnectManager.getConnection();
			try{
				String sql ="update comment set comment.read=1 where comment.username=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, user);
				System.out.println(pst.toString());
			  pst.executeUpdate();
			    
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		
	}
	/**
	 * 删除评论
	 * @param id
	 * @param username
	 * @param articleid
	 */
	public boolean  deleteComment(int  id,String username,int articleid){
		
		

		   ResultSet re=null;
		    Connection con=ConnectManager.getConnection();
			try{
				String sql ="delete from comment where id=? and username like ? and  articleid = ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				pst.setString(2, username);
				pst.setInt(3, articleid);
				System.out.println(pst.toString());
			if(  pst.executeUpdate()>0){
				return true;
			}
			    
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return false;
	}
}
