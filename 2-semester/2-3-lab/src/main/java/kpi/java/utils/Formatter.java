package kpi.java.utils;

import kpi.java.entity.Request;
import kpi.java.entity.Room;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class Formatter {
    private static String format(String data, int length) {
        StringBuilder builder = new StringBuilder(data);
        while (builder.length() < length) {
            builder.append(' ');
        }
        return builder.toString();
    }

    public static String formatRooms(List<Room> rooms) {
        if (rooms == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        builder.append("-------------------------------------------------\n");
        builder.append("|number|seat_number|type      |status    |price |\n");
        builder.append("-------------------------------------------------\n");
        for (Room room : rooms) {
            builder.append('|');
            builder.append(format(room.getNumber(), 6));
            builder.append('|');
            builder.append(format(Integer.toString(room.getSeatNumber()), 11));
            builder.append('|');
            builder.append(format(room.getType().name(), 10));
            builder.append('|');
            builder.append(format(room.getStatus().name(), 10));
            builder.append('|');
            builder.append(format(decimalFormat.format(room.getPrice()), 6));
            builder.append('|');
            builder.append('\n');
        }
        builder.append("-------------------------------------------------\n");
        return builder.toString();
    }

    public static String formatRequests(List<Request> requests) {
        if (requests == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        DateFormat dateFormat = new SimpleDateFormat("yyy-mm-dd");
        builder.append("--------------------------------------------------------------\n");
        builder.append("|id  |seat_number|type      |date from |date to   |phone     |\n");
        builder.append("--------------------------------------------------------------\n");
        int i = 0;
        for (Request request : requests) {
            builder.append('|');
            builder.append(format(Integer.toString(i), 4));
            builder.append('|');
            builder.append(format(Integer.toString(request.getSeatNumber()), 11));
            builder.append('|');
            builder.append(format(request.getType().name(), 10));
            builder.append('|');
            builder.append(format(dateFormat.format(request.getDateFrom()), 10));
            builder.append('|');
            builder.append(format(dateFormat.format(request.getDateTo()), 10));
            builder.append('|');
            builder.append(format(request.getPhone(), 10));
            builder.append('|');
            builder.append('\n');
            i++;
        }
        builder.append("--------------------------------------------------------------\n");
        return builder.toString();
    }
}
