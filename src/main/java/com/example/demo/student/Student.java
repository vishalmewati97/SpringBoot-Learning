package com.example.demo.student;

public class Student {

	private  Integer studentId;
	private String StudentName;
	public Student(Integer studentId, String studentName) {
		super();
		this.studentId = studentId;
		StudentName = studentName;
	}
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", StudentName=" + StudentName + "]";
	}
	
}
