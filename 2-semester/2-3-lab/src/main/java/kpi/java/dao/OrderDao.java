package kpi.java.dao;

import kpi.java.dto.CreateOrderDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class OrderDao extends GeneralDao {
    private final String addNewOrder = "insert into orders values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void createOrder(CreateOrderDto createDto) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement pstmt = connection.prepareStatement(addNewOrder);
        pstmt.setObject(1, UUID.randomUUID(), java.sql.Types.OTHER);
        pstmt.setDate(2, new Date(new java.util.Date().getTime()));
        pstmt.setFloat(3, createDto.room.getPrice());
        pstmt.setString(4, createDto.room.getNumber());
        pstmt.setString(5, createDto.room.getType().name());
        pstmt.setDate(6, new Date(createDto.dateFrom.getTime()));
        pstmt.setDate(7, new Date(createDto.dateTo.getTime()));
        pstmt.setString(8, createDto.phone);
        pstmt.setBoolean(9, false);
        pstmt.execute();
    }
}
