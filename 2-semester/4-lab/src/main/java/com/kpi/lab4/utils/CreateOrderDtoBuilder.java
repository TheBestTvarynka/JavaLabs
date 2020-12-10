package com.kpi.lab4.utils;

import com.kpi.lab4.dto.CreateOrderDto;

import java.util.Date;

public class CreateOrderDtoBuilder {
    private CreateOrderDto dto = new CreateOrderDto();

    public CreateOrderDtoBuilder() {

    }

    public CreateOrderDtoBuilder set(String param, String value) {
        switch (param) {
            case "roomNumber":
                setRoomNumber(value);
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
        }
        return this;
    }

    public CreateOrderDtoBuilder setPhone(String phone) {
        this.dto.setPhone(phone);
        return this;
    }

    public CreateOrderDtoBuilder setRoomNumber(String room) {
        this.dto.setRoomNumber(room);
        return this;
    }

    public CreateOrderDtoBuilder setDateFrom(String dateFrom) {
        this.dto.setDateFrom(new Date());
        return this;
    }

    public CreateOrderDtoBuilder setDateTo(String dateTo) {
        this.dto.setDateTo(new Date());
        return this;
    }

    public CreateOrderDto build() {
        return this.dto;
    }
}
