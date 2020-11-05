package kpi.java.dao;

import kpi.java.dto.CreateRequestDto;
import kpi.java.entity.Request;
import kpi.java.enums.RoomType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestDao extends GeneralDao {
    private final String addNewRequest = "insert into requests values (?, ?, ?, ?, ?, ?)";
    private final String selectAllRequests = "select * from requests";
    private final String selectRequestById = "select * from requests where id=?";
    private final String deleteRequest = "delete from requests where id=?";

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

    public List<Request> getAll() throws SQLException, IllegalArgumentException {
        List<Request> requests = new ArrayList<>();
        Statement statement = getConnection().createStatement();
        ResultSet res = statement.executeQuery(selectAllRequests);
        while (res.next()) {
            requests.add(new Request(
                    UUID.fromString(res.getString("id")),
                    res.getInt("seat_number"),
                    RoomType.valueOf(res.getString("type")),
                    res.getDate("date_from"),
                    res.getDate("date_to"),
                    res.getString("phone")
            ));
        }
        return requests;
    }

    public Optional<Request> getById(UUID id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(selectRequestById);
        statement.setObject(1, id, java.sql.Types.OTHER);
        ResultSet res = statement.executeQuery();
        Request request = null;
        if (res.next()) {
            request = new Request(
                    UUID.fromString(res.getString("id")),
                    res.getInt("seat_number"),
                    RoomType.valueOf(res.getString("type")),
                    res.getDate("date_from"),
                    res.getDate("date_to"),
                    res.getString("phone")
            );
        }
        return request == null ? Optional.empty() : Optional.of(request);
    }

    public void deleteRequest(UUID id) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(deleteRequest);
        statement.setObject(1, id, java.sql.Types.OTHER);
        statement.execute();
    }
}
