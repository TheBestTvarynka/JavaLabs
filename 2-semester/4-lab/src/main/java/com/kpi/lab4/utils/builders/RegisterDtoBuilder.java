package com.kpi.lab4.utils.builders;

import com.kpi.lab4.dto.RegisterDto;

public class RegisterDtoBuilder {
    RegisterDto dto = new RegisterDto();

    public RegisterDtoBuilder set(String parameter, String value) {
        switch (parameter) {
            case "username":
                this.dto.setUsername(value);
                break;
            case "email":
                this.dto.setEmail(value);
                break;
            case "fullName":
                this.dto.setFullName(value);
                break;
            case "password":
                this.dto.setPassword(value);
                break;
            default:
                break;
        }
        return this;
    }

    public RegisterDtoBuilder setUsername(String username) {
        this.dto.setUsername(username);
        return this;
    }

    public RegisterDtoBuilder setEmail(String email) {
        this.dto.setEmail(email);
        return this;
    }

    public RegisterDtoBuilder setFullName(String fullName) {
        this.dto.setFullName(fullName);
        return this;
    }

    public RegisterDtoBuilder setPassword(String password) {
        this.dto.setPassword(password);
        return this;
    }

    public RegisterDto build() {
        return this.dto;
    }
}
