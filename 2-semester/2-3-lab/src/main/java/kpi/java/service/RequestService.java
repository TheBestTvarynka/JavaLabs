package kpi.java.service;

import kpi.java.dao.RequestDao;
import kpi.java.dao.RoomDao;
import kpi.java.dto.CreateOrderDto;
import kpi.java.utils.SimpleConnectionPool;
import kpi.java.entity.Room;
import kpi.java.utils.SelectRoomOptions;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RequestService {
    private static RequestService requestService;

    private RequestDao requestRepository;
    private RoomDao roomRepository;

    private RequestService() {
        requestRepository = new RequestDao();
        roomRepository = new RoomDao();
    }

    public List<Room> selectRooms(SelectRoomOptions options) throws SQLException {
        List<Room> rooms;
        try {
            roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            rooms = roomRepository.selectRooms(options);
            SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
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
