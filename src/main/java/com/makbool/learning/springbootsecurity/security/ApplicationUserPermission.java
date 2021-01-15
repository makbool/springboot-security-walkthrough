package com.makbool.learning.springbootsecurity.security;

public enum ApplicationUserPermission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),

    COURSE_READ("course:read"),
    COURSE_WRITE("course:write");

    public String getPermission() {
        return permission;
    }

    private final String permission;
    
    ApplicationUserPermission(String permission) {

        this.permission = permission;
    }

}
