package com.kpi.lab4.utils.builders;

import com.kpi.lab4.dto.CreateRequestDto;
import com.kpi.lab4.enums.RoomType;

import java.util.Date;

public class CreateRequestDtoBuilder {
    private CreateRequestDto dto = new CreateRequestDto();

    public CreateRequestDtoBuilder() {

    }

    public CreateRequestDtoBuilder set(String param, String value) {
        switch (param) {
            case "type":
                setType(value);
                break;
            case "seatNumber":
                setSeatNumber(value);
                break;
            case "phone":
                setPhone(value);
                break;
            case "dateFrom":
                setDateFrom(value);
                break;
            case "dateTo":
                setDateTo(value);
                break;
            default:
                break;
        };
        return this;
    }

    public CreateRequestDtoBuilder setType(String type) {
        try {
            this.dto.setType(RoomType.valueOf(type));
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Wrong room type name!");
        }
        return this;
    }

    public CreateRequestDtoBuilder setSeatNumber(String number) {
        try {
            this.dto.setSeatNumber(Integer.parseInt(number));
        } catch (NumberFormatException ignored) {
            throw new IllegalArgumentException("Wrong number of seats!");
        }
        return this;
    }

    public CreateRequestDtoBuilder setPhone(String phone) {
        if (phone.matches("^\\d{10}$")) {
            this.dto.setPhone(phone);
        } else {
            throw new IllegalArgumentException("Wrong phone format!");
        }
        return this;
    }

    public CreateRequestDtoBuilder setDateFrom(String dateFrom) {
        this.dto.setDateFrom(new Date());
        return this;
    }

    public CreateRequestDtoBuilder setDateTo(String dateTo) {
        this.dto.setDateTo(new Date());
        return this;
    }

    public CreateRequestDto build() {
        return this.dto;
    }
}
