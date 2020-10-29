package kpi.java.service;

import kpi.java.dao.OrderDao;
import kpi.java.dao.RoomDao;
import kpi.java.dto.CreateOrderDto;
import kpi.java.entity.Room;
import kpi.java.utils.SimpleConnectionPool;

import java.sql.SQLException;
import java.util.Optional;

public class OrderService {
    private static OrderService orderService;

    private OrderDao orderRepository;
    private RoomDao roomRepository;

    private OrderService() {
        this.orderRepository = new OrderDao();
        this.roomRepository = new RoomDao();
    }

    public String bookRoom(CreateOrderDto createDto) throws SQLException {
        Optional<Room> room;
        try {
            roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            room = roomRepository.findByRoomNumber(createDto.roomNumber);
            SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
        } catch(SQLException e) {
            throw new SQLException("Sorry, we are temporary unavailable. Please, try later.");
        }
        createDto.room = room.orElseThrow(() -> new SQLException("Wrong room number! Room not found."));
        try {
            orderRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            orderRepository.createOrder(createDto);
            SimpleConnectionPool.getPool().releaseConnection(orderRepository.releaseConnection());
        } catch(SQLException | IllegalArgumentException e) {
            throw new SQLException("Sorry, we are temporary unavailable. Please, try later.");
        }
        return "All success! You have two days to pay for the order.";
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }
}
