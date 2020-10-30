package kpi.java.service;

import kpi.java.dao.RequestDao;
import kpi.java.dao.RoomDao;
import kpi.java.dto.CreateRequestDto;
import kpi.java.exception.UnavailableException;
import kpi.java.utils.SimpleConnectionPool;
import kpi.java.entity.Room;
import kpi.java.utils.SelectRoomOptions;

import java.sql.SQLException;
import java.util.List;

public class RequestService {
    private static RequestService requestService;

    private RequestDao requestRepository;
    private RoomDao roomRepository;

    private RequestService() {
        requestRepository = new RequestDao();
        roomRepository = new RoomDao();
    }

    public List<Room> selectRooms(SelectRoomOptions options) throws UnavailableException {
        List<Room> rooms;
        try {
            roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            rooms = roomRepository.selectRooms(options);
            SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
        } catch(SQLException | IllegalArgumentException e) {
            throw new UnavailableException();
        }
        return rooms;
    }

    public String createRequest(CreateRequestDto createDto) throws UnavailableException {
        try {
            requestRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            requestRepository.createRequest(createDto);
            SimpleConnectionPool.getPool().releaseConnection(requestRepository.releaseConnection());
        } catch (SQLException e) {
            throw new UnavailableException();
        }
        return "All success. Our manager will choose the most suitable room for you.";
    }

    public static RequestService getInstance() {
        if (requestService == null) {
            requestService = new RequestService();
        }
        return requestService;
    }
}
