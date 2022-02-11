package com.example.demo.configs;

public enum ApplicationUserPermission {

	STUDENT_READ("student:read"),
	STUDENT_WRITE("student:write"),
	COURSE_READ("course:read"),
	COURSE_WRITE("course:write");

	private final String permission;
	
	ApplicationUserPermission(String permission) {
		// TODO Auto-generated constructor stub
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
