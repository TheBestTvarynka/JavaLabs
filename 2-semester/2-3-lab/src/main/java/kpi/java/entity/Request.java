package kpi.java.entity;

import kpi.java.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
public class Request {
    private UUID id;
    private int seatNumber;
    private RoomType type;
    private Date dateFrom;
    private Date dateTo;
    private String phone;
}
