package com.ldr.bean;

import java.sql.Date;

public class Collect {
private int id;
private String userName;
private int articleID;
private String title;


public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public int getArticleID() {
	return articleID;
}
public void setArticleID(int articleID) {
	this.articleID = articleID;
}

}
