package kpi.java.dao;

import kpi.java.entity.Room;
import kpi.java.enums.RoomStatus;
import kpi.java.enums.RoomType;
import kpi.java.utils.SelectRoomOptions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class RequestDao extends GeneralDao {

    public List<Room> selectRooms(SelectRoomOptions options) throws SQLException {
        StringBuilder queryBuilder = new StringBuilder("select * from rooms");
        Set<RoomType> types = options.getTypes();
        Set<RoomStatus> statuses = options.getStatuses();
        if (types.size() > 0 || statuses.size() > 0) {
            queryBuilder.append(" where");
        }
        Iterator<RoomType> typeIterator = types.iterator();
        if (typeIterator.hasNext()) {
            queryBuilder.append(" (type=" + "'").append(typeIterator.next()).append("'");
            while (typeIterator.hasNext()) {
                queryBuilder.append(" or type=" + "'").append(typeIterator.next()).append("'");
            }
            queryBuilder.append(")");
        }
        Iterator<RoomStatus> statusIterator = statuses.iterator();
        if (statusIterator.hasNext()) {
            if (types.size() > 0) {
                queryBuilder.append(" and");
            }
            queryBuilder.append(" (status=" + "'").append(statusIterator.next()).append("'");
            while (statusIterator.hasNext()) {
                queryBuilder.append(" or status=" + "'").append(statusIterator.next()).append("'");
            }
            queryBuilder.append(")");
        }
        Optional<String> priceOrder = options.getPriceOrder();
        Optional<String> seatsOrder = options.getSeatOrder();
        priceOrder.ifPresent(s -> queryBuilder.append(" order by price ").append(s));
        if (seatsOrder.isPresent()) {
            if (priceOrder.isPresent()) {
                queryBuilder.append(", seat ").append(seatsOrder.get());
            } else {
                queryBuilder.append(" order by seat_number ").append(seatsOrder.get());
            }
        }
        System.out.println(queryBuilder.toString());
        Statement statement = getConnection().createStatement();
        ResultSet res = statement.executeQuery(queryBuilder.toString());
        List<Room> rooms = new ArrayList<>();
        while (res.next()) {
            rooms.add(new Room(
                    UUID.fromString(res.getString("id")),
                    res.getString("number"),
                    res.getInt("seat_number"),
                    RoomType.valueOf(res.getString("type")),
                    RoomStatus.valueOf(res.getString("status")),
                    res.getFloat("price")
            ));
        }
        return rooms;
    }
}
