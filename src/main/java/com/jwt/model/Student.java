package com.jwt.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Student")
public class Student implements Serializable {

	private static final long serialVersionUID = -3465813074586302847L;

	@Id
	private int id; 
	
	public int getId() {
		return id;
	}
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public void setId(int id) {
		this.id = id;
	}
	
	  
	private int rollNum;
	private String sname;
	private String gender;
	private String country;
	private int age;
	private Date dateOfJoin;
	private double finalScore;
	
	@Transient
	private String grade;
	
	
	public String getGrade() {
		GradeInterface gInt = (double x) -> {String grade = x < 40 ? "FAIL" : x < 60 ? "AVERAGE" : x< 75 ? "FIRST CLASS" : "DISTINCTION"; return grade; };
		return gInt.getGrade(this.finalScore);
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getRollNum() {
		return rollNum;
	}
	
	public void setRollNum(int rollNo) {
		this.rollNum = rollNo;
	}
	
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public Date getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	
	public void setFinalScore(double finalScore) {
		this.finalScore = finalScore;
	}
	public double getFinalScore() {
		return finalScore;
	}
	
	
}