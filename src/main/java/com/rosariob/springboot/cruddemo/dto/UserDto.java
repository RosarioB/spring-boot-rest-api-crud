package com.rosariob.springboot.cruddemo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
    @NotNull
    @Size(min = 1, message = "{Size.userDto.username}")
    private String userName;

    @NotNull
    @Size(min = 1, message = "{Size.userDto.password}")
    private String password;

    @NotNull
    private RoleName role;

    public enum RoleName {
        ROLE_EMPLOYEE,
        ROLE_MANAGER,
        ROLE_ADMIN

    }

    public UserDto(@NotNull String userName, @NotNull String password, @NotNull RoleName role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }
}
