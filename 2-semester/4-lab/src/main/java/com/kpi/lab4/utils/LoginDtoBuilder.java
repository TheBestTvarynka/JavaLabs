package com.kpi.lab4.utils;

import com.kpi.lab4.dto.LoginDto;

public class LoginDtoBuilder {
    private LoginDto dto = new LoginDto();

    public LoginDtoBuilder set(String param, String value) {
        switch (param) {
            case "username":
                this.dto.setUsername(value);
                break;
            case "password":
                this.dto.setPassword(value);
                break;
            default:
                break;
        }
        return this;
    }

    public LoginDtoBuilder setUsername(String username) {
        this.dto.setUsername(username);
        return this;
    }

    public LoginDtoBuilder setPassword(String password) {
        this.dto.setPassword(password);
        return this;
    }

    public LoginDto build() {
        return this.dto;
    }
}
