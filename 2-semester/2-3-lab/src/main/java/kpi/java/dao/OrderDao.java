package kpi.java.dao;

import kpi.java.dto.CreateOrderDto;
import kpi.java.entity.Order;
import kpi.java.enums.RoomType;

import java.sql.*;
import java.util.Optional;
import java.util.UUID;

public class OrderDao extends GeneralDao {
    public UUID createOrder(CreateOrderDto createDto) throws SQLException {
        final String addNewOrder = "insert into orders values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        UUID id = UUID.randomUUID();
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(addNewOrder);
        pstmt.setObject(1, id, java.sql.Types.OTHER);
        pstmt.setDate(2, new Date(new java.util.Date().getTime()));
        pstmt.setFloat(3, createDto.room.getPrice());
        pstmt.setString(4, createDto.room.getNumber());
        pstmt.setString(5, createDto.room.getType().name());
        pstmt.setDate(6, new Date(createDto.dateFrom.getTime()));
        pstmt.setDate(7, new Date(createDto.dateTo.getTime()));
        pstmt.setString(8, createDto.phone);
        pstmt.setBoolean(9, false);
        pstmt.execute();
        return id;
    }

    public Optional<Order> findById(UUID id) throws SQLException {
        final String selectOrderById = "select * from orders where id=?";
        PreparedStatement statement = getConnection().prepareStatement(selectOrderById);
        statement.setObject(1, id, java.sql.Types.OTHER);
        ResultSet res = statement.executeQuery();
        Order order = null;
        if (res.next()) {
            order = new Order(
                    UUID.fromString(res.getString("id")),
                    res.getString("room_number"),
                    res.getDate("creation_date"),
                    res.getFloat("price"),
                    RoomType.valueOf(res.getString("type")),
                    res.getDate("date_from"),
                    res.getDate("date_to"),
                    res.getBoolean("paid")
            );
        }
        return order == null ? Optional.empty() : Optional.of(order);
    }

    public void deleteById(UUID id) throws SQLException {
        final String deleteOrderById = "delete from orders where id=?";
        PreparedStatement statement = getConnection().prepareStatement(deleteOrderById);
        statement.setObject(1, id, java.sql.Types.OTHER);
        statement.execute();
    }
}
