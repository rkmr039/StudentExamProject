package com.jwt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Exam")
public class Exam {
	
	@Id
	private int eid;
	private String subject;
	private double mark;
	private int sid;
	
	
	public int getEid() {
		return eid;
	}
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	
}
