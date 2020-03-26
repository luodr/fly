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
import com.ldr.bean.User;
import com.ldr.util.ConnectManager;;



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
	public  int insertArticle(Article article){
		Connection con=ConnectManager.getConnection();
		 ResultSet re= null;
		try{
			String sql ="insert into article(title,content,views,goods,rubbis,creationDate,name,Introduction,coverimage,userName,type,report,type2,L_version) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, article.getTitle());
			pst.setString(2, article.getContent());
			pst.setInt(3, 0);
			pst.setInt(4, 0);
			pst.setInt(5, 0);
	
			pst.setTimestamp(6,		new Timestamp(article.getCreationDate().getTime()));
			pst.setString(7, article.getName());
			pst.setString(8, article.getIntroduction());
			pst.setString(9, article.getCoverimage());
			pst.setString(10, article.getUserName());
			pst.setString(11, article.getType());
			pst.setInt(12, 0);
			pst.setString(13, article.getType2());
			pst.setString(14, article.getL_version());
			
		  if(	pst.executeUpdate()>0){
			  //在关闭连接前获取刚插入的ID
			  String sql_ID="SELECT LAST_INSERT_ID();";
			  re=  pst.executeQuery(sql_ID);
			  if(re.next()){
				  
				  return re.getInt(1);
			  }
		   }
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return 0;
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
				  con=ConnectManager.getConnection();
					String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  WHERE article.id = ?";
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setInt(1, itemID);
					re=pst.executeQuery();
					while (re.next()){
						Article article=new   Article();
						article.setContent(re.getString("content"));
						article.setCoverimage(re.getString("coverimage"));
						article.setCreationDate(re.getTimestamp("creationDate"));
						article.setGoods(re.getInt("goods"));
						article.setId(re.getInt("id"));
						article.setAudit(re.getInt("audit"));
						article.setIntroduction(re.getString("introduction"));
						article.setName(re.getString("name"));
				      	 article.setImg(re.getString("img"));
						article.setTitle(re.getString("title"));
						article.setType(re.getString("type"));
						article.setUserName(re.getString("userName"));
						article.setViews(re.getInt("views"));
						article.setReply(re.getInt("reply"));
						article.setType2(re.getString("type2"));
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
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setInt(1, itemID);
				System.out.println("添加访问量"+pst.toString());
				
				pst.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		
	}
	/**
	 *  添加评论量
	 * @param itemID
	 */
	public void addReply(int itemID){
		 Connection con = null;
		 
			String sql ="update article set reply=reply+1 where id = ?";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
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
	/**
	 *  减少评论量
	 * @param itemID
	 */
	public void reduceReply(int itemID){
		 Connection con = null;
		 
			String sql ="update article set reply=reply-1 where id = ?";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
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
	/**
	 *  举报
	 * @param itemID
	 */
	public void addReport(int itemID){
		 Connection con = null;
		 
			String sql ="update article set report=report+1 where id = ?";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
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
	
	/**
	 * 模糊搜索
	 * @param key
	 * @param hot   热度排序
	 * @return
	 */
	public ArrayList<Article> searchArticle(String key,boolean noHot){
		 ResultSet re= null;
		 Connection con = null;
		 String sql;
		if(noHot){
			 sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  WHERE audit=1 and  article.title LIKE ? or article.type LIKE    ? ORDER BY  article.creationDate DESC";
		}else{
			 sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  WHERE audit=1 and  article.title LIKE ? or article.type LIKE    ? ORDER BY  article.reply DESC";
		}
		key="%"+key+"%";
		ArrayList<Article> arrayList=new ArrayList<Article>();
		PreparedStatement pst;
		try {
			 con=ConnectManager.getConnection();
			pst = con.prepareStatement(sql);
			pst.setString(1, key);
			pst.setString(2, key);
		
			re=pst.executeQuery();
			while (re.next()){
				Article article=new   Article();
				article.setCreationDate(re.getTimestamp("creationDate"));
				article.setGoods(re.getInt("goods"));
				article.setId(re.getInt("id"));
				article.setName(re.getString("name"));
				article.setReply(re.getInt("reply"));
				article.setTitle(re.getString("title"));
				article.setAudit(re.getInt("audit"));
				article.setType(re.getString("type"));
				 article.setImg(re.getString("img"));
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
	 * @param offset
	 * @return
	 */
	public ArrayList<Article> viewsArticleDESC(int num,int offset){
		 ResultSet re= null;
		 Connection con = null;
		String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`   where audit=1   ORDER BY  article.views DESC   limit ? OFFSET ?";

		ArrayList<Article> arrayList=new ArrayList<Article>();
		PreparedStatement pst;
		try {
			 con=ConnectManager.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setInt(2, offset);
			re=pst.executeQuery();
			while (re.next()){
				Article article=new   Article();
		
				article.setCreationDate(re.getTimestamp("creationDate"));
				article.setGoods(re.getInt("goods"));
				article.setId(re.getInt("id"));
				 article.setImg(re.getString("img"));
				article.setName(re.getString("name"));
				article.setReply(re.getInt("reply"));
				article.setAudit(re.getInt("audit"));
				article.setTitle(re.getString("title"));
				article.setType(re.getString("type"));
				article.setUserName(re.getString("userName"));
				article.setViews(re.getInt("views"));
				arrayList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return arrayList;
	}
	
	/**
	 * 评论率最高的文章
	 * @param num
	 * @param offset
	 * @return
	 */
	public ArrayList<Article> replyArticleDESC(int num,int offset){
		 ResultSet re= null;
		 Connection con = null;
		String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`   where audit=1   ORDER BY  article.reply DESC   limit ? OFFSET ?";

		ArrayList<Article> arrayList=new ArrayList<Article>();
		PreparedStatement pst;
		try {
			 con=ConnectManager.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, num);
			pst.setInt(2, offset);
			re=pst.executeQuery();
			while (re.next()){
				Article article=new   Article();
		
				article.setCreationDate(re.getTimestamp("creationDate"));
				article.setGoods(re.getInt("goods"));
				article.setId(re.getInt("id"));
				 article.setImg(re.getString("img"));
				article.setName(re.getString("name"));
				article.setReply(re.getInt("reply"));
				article.setAudit(re.getInt("audit"));
				article.setTitle(re.getString("title"));
				article.setType(re.getString("type"));
				article.setUserName(re.getString("userName"));
				article.setViews(re.getInt("views"));
				arrayList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
		return arrayList;
	}
	 /**
	  * 删除文章
	  * @param id 需要删除的文章id
	  */
	public void deleteArticle(int id){
		
		Connection con = null;
		String sql ="delete from  article WHERE id= ?";

		ArrayList<Article> arrayList=new ArrayList<Article>();
		PreparedStatement pst;
		try {
			 con=ConnectManager.getConnection();
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			closeConnection(con);
		}
	}
	/**
	 * 文章编辑
	 * @param article 
	 * @return
	 */
	public boolean updateArticle(Article article){
		Connection con=ConnectManager.getConnection();
		try{
			String sql ="update article set title= ? and content=? WHERE id=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, article.getTitle());
			pst.setString(2, article.getContent());
			pst.setInt(3, article.getId());
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
	
	 public ArrayList<Article> findAll(){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			  con=ConnectManager.getConnection();
				String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  where audit=1  ";
				PreparedStatement pst = con.prepareStatement(sql);
			
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
					article.setContent(re.getString("content"));
					article.setCoverimage(re.getString("coverimage"));
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setIntroduction(re.getString("introduction"));
					article.setAudit(re.getInt("audit"));
					article.setName(re.getString("name"));
					 article.setImg(re.getString("img"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	 
	 public ArrayList<Article> findPage(int OFFSET,int limit){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			  con=ConnectManager.getConnection();
				String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user` where audit=1  limit ?  OFFSET ? ";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, limit);
				pst.setInt(2, OFFSET);
				System.out.println(pst.toString());
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
					article.setContent(re.getString("content"));
					article.setCoverimage(re.getString("coverimage"));
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setAudit(re.getInt("audit"));
					article.setIntroduction(re.getString("introduction"));
					article.setName(re.getString("name"));
					 article.setImg(re.getString("img"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	 
	 
	  /**
	   *  查询最新的文章
	   * @param num
	   * @param offset
	   * @return
	   */
	 public ArrayList<Article> findNewArticle(int num,int offset){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			  con=ConnectManager.getConnection();
				String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  where audit=1   ORDER BY  article.creationDate DESC   limit ? OFFSET ? ";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, num);
				pst.setInt(2, offset);
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
			
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setReply(re.getInt("reply"));
					article.setName(re.getString("name"));
					article.setAudit(re.getInt("audit"));
					 article.setImg(re.getString("img"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	 
	 /**
	  * 查询某个用户的文章
	  * @param userName 用户唯一凭证
	  * @return Articles
	  */
	 public ArrayList<Article> findByUser(String userName){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			    con=ConnectManager.getConnection();
				String sql ="select id,title,views,goods,audit,creationDate,userName,type,reply,type2,name from  article where userName like ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, userName);
		
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
			
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setAudit(re.getInt("audit"));
					article.setId(re.getInt("id"));
					article.setReply(re.getInt("reply"));
					article.setName(re.getString("name"));
				 
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	 
	 
	 /**
	  * 查询某个用户的文章
	  * @param userName 用户唯一凭证
	  * @return Articles
	  */
	 public ArrayList<Article> findByName(String name){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			    con=ConnectManager.getConnection();
				String sql ="select id,title,views,goods,audit,creationDate,userName,type,reply,type2,name from article where audit=1 and name like ?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, name);
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
			
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setAudit(re.getInt("audit"));
			
					article.setName(re.getString("name"));
					article.setReply(re.getInt("reply"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	 /**
	  *  搜索分页查询类型
	  * @param type
	  * @return
	  */
	 public ArrayList<Article> searchType(String type,int OFFSET,int limit){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			    con=ConnectManager.getConnection();
				String sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  where audit=1 and article.type like ?   limit ? OFFSET ? ";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, type);
				pst.setInt(2, limit);
				pst.setInt(3, OFFSET);
			System.out.println(pst.toString());
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
				
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setAudit(re.getInt("audit"));
					 article.setImg(re.getString("img"));
					article.setName(re.getString("name"));
					article.setReply(re.getInt("reply"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	 
	 
	 /**
	  * 根据类型查询
	  * @param type
	  * @return
	  */
	 public ArrayList<Article> searchType(String type,boolean hot){
		 Connection con = null;
		 ResultSet re= null;
		 ArrayList<Article> list=new ArrayList<Article>();
		 try{
			    con=ConnectManager.getConnection();
			    String sql;
				if(!hot)
				{
					 sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`  where audit=1 and article.type like ?    ORDER BY   creationDate DESC"  ;
				}else{
					sql ="select article.*,`user`.img from article left join `user` on article.userName=`user`.`user`    where audit=1 and article.type like ?  ORDER BY  reply DESC";
				}
				
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, type);
			System.out.println(pst.toString());
				re=pst.executeQuery();
				while (re.next()){
					Article article=new   Article();
				
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					 article.setImg(re.getString("img"));
					article.setName(re.getString("name"));
					article.setReply(re.getInt("reply"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setAudit(re.getInt("audit"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
		 return list;
	 }
	    /**
	     * 查询举报
	     * @param type
	     * @param OFFSET
	     * @param limit
	     * @return
	     */
		public ArrayList<Article> reports(String type,int OFFSET,int limit){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="select * from article  WHERE  type=? and report   ORDER BY  report DESC   LIMIT ? OFFSET ?";
	
			ArrayList<Article> list=new ArrayList<Article>();
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setString(1, type);
				pst.setInt(2, limit);
				pst.setInt(3, OFFSET);
				re=pst.executeQuery();
				while (re.next()){
					Article article=new  Article();
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setName(re.getString("name"));
					article.setAudit(re.getInt("audit"));
					article.setReply(re.getInt("reply"));
					article.setReport(re.getInt("report"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return list;
		}
		 /**
		  * 统计已审核
		  * @return
		  */
		public int count(){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="select COUNT(id) FROM  article where audit=1 ";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
			
				re=pst.executeQuery();
				while (re.next()){
					int count=re.getInt("COUNT(id)");
					return count;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return 0;
		}
		 /**
		  * 查询已审核某个类型的数量
		  * @param type
		  * @return
		  */
		public int countType(String type){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="select COUNT(id) FROM article where audit=1 and   article.type like ? ";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setString(1, type);
				re=pst.executeQuery();
				while (re.next()){
					int count=re.getInt("COUNT(id)");
					return count;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return 0;
		}
		/**
		 * 查询未审核的某个类型贴子数量
		 * @param type
		 * @return
		 */
		public int countTypeNoAudit(String type){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="select COUNT(id) FROM article where audit=0 and   article.type like ? ";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setString(1, type);
				re=pst.executeQuery();
				while (re.next()){
					int count=re.getInt("COUNT(id)");
					return count;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return 0;
		}
		  /**
		   * 根据类型查询未审核
		   * @param type
		   * @param OFFSET
		   * @param limit
		   * @return
		   */
		public ArrayList<Article> FindNotAuditByType(String type,int OFFSET,int limit){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="select * from article  WHERE  audit=0 and type=?  LIMIT ? OFFSET ?";
	
			ArrayList<Article> list=new ArrayList<Article>();
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setString(1, type);
				pst.setInt(2, limit);
				pst.setInt(3, OFFSET);
				re=pst.executeQuery();
				System.out.println(pst.toString()+"-----");
				while (re.next()){
					Article article=new  Article();
					article.setCreationDate(re.getTimestamp("creationDate"));
					article.setGoods(re.getInt("goods"));
					article.setId(re.getInt("id"));
					article.setName(re.getString("name"));
					article.setReply(re.getInt("reply"));
					article.setAudit(re.getInt("audit"));
					article.setReport(re.getInt("report"));
					article.setTitle(re.getString("title"));
					article.setType(re.getString("type"));
					article.setUserName(re.getString("userName"));
					article.setViews(re.getInt("views"));
					list.add(article);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return list;
		}
		 /**
		  * 通过审核
		  * @param ID 文章ID
		  * @return
		  */
		public boolean auditArticle(int id){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="update article set audit=1 where id = ? ";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				if(pst.executeUpdate()>0){
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return false;
		}
		
		public int count(int id){
			 ResultSet re= null;
			 Connection con = null;
			String sql ="select count(id) from article";
			PreparedStatement pst;
			try {
				 con=ConnectManager.getConnection();
				pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				re=pst.executeQuery();
				if(re.next()){
					return re.getInt("count(id)");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				closeConnection(con);
			}
			return 0;
		}
}
