package com.ldr.bean;

import java.sql.Date;

public class User {
   private int  id;
   private String  user;
   private String name ;
   private int age;
   private String sex;
   private String password ;
   private int type;
   private String address;
   private Date regTime;
   private String signature;
   private String img;
   private String mail;
   
   

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public String getImg() {
	return img;
}

public void setImg(String img) {
	this.img = img;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public Date getRegTime() {
	return regTime;
}

public void setRegTime(Date regTime) {
	this.regTime = regTime;
}

public String getSignature() {
	return signature;
}

public void setSignature(String signature) {
	this.signature = signature;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public int getType() {
	return type;
}

public void setType(int type) {
	this.type = type;
}
   
   
   
}
