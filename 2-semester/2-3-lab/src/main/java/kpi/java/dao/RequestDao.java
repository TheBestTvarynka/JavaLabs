package kpi.java.dao;

import kpi.java.dto.CreateRequestDto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class RequestDao extends GeneralDao {
    private final String addNewRequest = "insert into requests values (?, ?, ?, ?, ?, ?)";

    public void createRequest(CreateRequestDto createDto) throws SQLException {
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(addNewRequest);
        statement.setObject(1, UUID.randomUUID(), java.sql.Types.OTHER);
        statement.setInt(2, createDto.seatNumber);
        statement.setString(3, createDto.type.name());
        statement.setDate(4, new Date(createDto.dateFrom.getTime()));
        statement.setDate(5, new Date(createDto.dateTo.getTime()));
        statement.setString(6, createDto.phone);
        statement.execute();
    }
}
