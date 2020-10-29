package kpi.java.dao;

import kpi.java.entity.Room;
import kpi.java.enums.RoomStatus;
import kpi.java.enums.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class RoomDao extends GeneralDao {
    private final String findByNumber = "select * from rooms where number=?";

    public Optional<Room> findByRoomNumber(String roomNumber) throws SQLException, IllegalArgumentException {
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(findByNumber);
        pstmt.setString(1, roomNumber);
        ResultSet res = pstmt.executeQuery();
        Room room = null;
        while (res.next()) {
            room = new Room(
                    UUID.fromString(res.getString("id")),
                    res.getString("number"),
                    res.getInt("seat_number"),
                    RoomType.valueOf(res.getString("type")),
                    RoomStatus.valueOf(res.getString("status")),
                    res.getFloat("price")
            );
        }
        return room == null ? Optional.empty() : Optional.of(room);
    }
}
