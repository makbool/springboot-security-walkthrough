package com.makbool.learning.springbootsecurity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.makbool.learning.springbootsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    STUDENT(new HashSet<>()),
    ADMIN(new HashSet<>(Arrays.asList(STUDENT_READ, STUDENT_WRITE,COURSE_READ, COURSE_WRITE))),
    ADMIN_TRAINEE(new HashSet<>(Arrays.asList(STUDENT_READ, COURSE_READ)));

    public final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> authorities = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }


}
