package com.ldr.bean;

import java.sql.Date;

public class Article {
	private int id;
	//���±���
	private String title;
	//��������
	private String content;
	//�����
	private int views;
	//������
	private int goods;
	//��������
	private int rubbis;
	//����ʱ��
	private Date creationDate;
   //���
	private String Introduction;
	//����ͼ
	private String coverimage;
	//����
	private String userName;
	//��������
	private String name;
	//�ļ�����
	private String type;
	//�ٱ���
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
