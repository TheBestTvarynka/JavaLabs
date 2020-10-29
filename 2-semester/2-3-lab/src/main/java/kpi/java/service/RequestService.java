package kpi.java.service;

import kpi.java.dao.RequestDao;
import kpi.java.dao.SimpleConnectionPool;
import kpi.java.entity.Room;
import kpi.java.utils.SelectRoomOptions;

import java.sql.SQLException;
import java.util.List;

public class RequestService {
    private static RequestService requestService;

    private RequestDao repository;

    private RequestService() {
        repository = new RequestDao();
    }

    public List<Room> selectRooms(SelectRoomOptions options) throws SQLException {
        List<Room> rooms;
        try {
            repository.setConnection(SimpleConnectionPool.getPool().getConnection());
            rooms = repository.selectRooms(options);
            SimpleConnectionPool.getPool().releaseConnection(repository.releaseConnection());
        } catch(SQLException | IllegalArgumentException e) {
            throw new SQLException("Sorry, we are temporary unavailable. Please, try later.");
        }
        return rooms;
    }

    public static RequestService getInstance() {
        if (requestService == null) {
            requestService = new RequestService();
        }
        return requestService;
    }
}
