package com.ldr.bean;

import java.sql.Date;

public class Article {
	private int id;
	//文章标题
	private String title;
	//文章内容
	private String content;
	//浏览数
	private int views;
	//点赞数
	private int goods;
	//觉得垃圾
	private int rubbis;
	//创建时间
	private Date creationDate;
   //简介
	private String Introduction;
	//封面图
	private String coverimage;
	//作者
	private String userName;
	//作者名称
	private String name;
	//文件类型
	private String type;
	//举报数
	private int report;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getGoods() {
		return goods;
	}
	public void setGoods(int goods) {
		this.goods = goods;
	}
	public int getRubbis() {
		return rubbis;
	}
	public void setRubbis(int rubbis) {
		this.rubbis = rubbis;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getIntroduction() {
		return Introduction;
	}
	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}
	public String getCoverimage() {
		return coverimage;
	}
	public void setCoverimage(String coverimage) {
		this.coverimage = coverimage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}

}
