package kpi.java.utils;

import kpi.java.enums.RoomStatus;
import kpi.java.enums.RoomType;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
public class SelectRoomOptions {
//    public float priceFrom;
//    public float priceTo;
//    public int seatFrom;
//    public int seatTo;
    private Optional<String> priceOrder = Optional.empty();
    private Optional<String> seatOrder = Optional.empty();
    private Set<RoomType> types = new HashSet<>();
    private Set<RoomStatus> statuses = new HashSet<>();

    public SelectRoomOptions() {
    }

    public void setStatus(String s) throws IllegalArgumentException {
        try {
            RoomStatus status = RoomStatus.valueOf(s.toUpperCase());
            if (statuses.contains(status)) {
                statuses.remove(status);
            } else {
                statuses.add(status);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong status name!");
        }
    }

    public void setType(String t) throws IllegalArgumentException {
        try {
            RoomType type = RoomType.valueOf(t.toUpperCase());
            if (types.contains(type)) {
                types.remove(type);
            } else {
                types.add(type);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Wrong type name!");
        }
    }

    public void setOrder(String param, String order) throws IllegalArgumentException {
        if (order == null || (!order.equals("none") && !order.equals("asc") && !order.equals("desc"))) {
            throw new IllegalArgumentException("Wrong order!");
        }
        if (param.equals("price")) {
            if (order.equals("none")) {
                priceOrder = Optional.empty();
            } else {
                priceOrder = Optional.of(order);
            }
        } else if (param.equals("seats")) {
            if (order.equals("none")) {
                seatOrder = Optional.empty();
            } else {
                seatOrder = Optional.of(order);
            }
        }
    }
}
