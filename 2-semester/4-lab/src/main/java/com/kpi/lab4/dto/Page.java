package com.kpi.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Page<T> {
    private List<T> data;
    private int page;
    private int offset;
    private int count;
}
