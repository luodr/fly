package com.ldr.bean;

import java.sql.Date;
import java.sql.Timestamp;

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
	private Timestamp creationDate;
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
	//�ظ���
	private int reply;
	//������
	private String type2; 
	private String L_version;
	//ͼƬ
	private String img;
	//���״̬
	private int audit;
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getL_version() {
		return L_version;
	}
	public void setL_version(String l_version) {
		L_version = l_version;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	
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


	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
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
	public int getAudit() {
		return audit;
	}
	public void setAudit(int audit) {
		this.audit = audit;
	}

}
