package com.ast.HealthCare.Pojo;

import java.sql.Date;

public class CheckPojo {

	private int id;
	private String name;
	private Date date2;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	@Override
	public String toString() {
		return "CheckPojo [id=" + id + ", name=" + name + ", date2=" + date2 + "]";
	}
	
	
		
	
}
