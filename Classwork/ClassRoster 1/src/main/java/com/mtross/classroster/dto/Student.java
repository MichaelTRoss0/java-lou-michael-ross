/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.classroster.dto;

/**
 *
 * @author mike
 */
public class Student {
	private String firstName;
	private String lastName;
	private String studentId;
	private String cohort; // Java or .Net + cohort month/year
	
	public Student(String studentId) {
		this.studentId = studentId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getStudentId() {
		return studentId;
	}
	
	public String getCohort() {
		return cohort;
	}
	
	public void setCohort(String cohort) {
		this.cohort = cohort;
	}
}
