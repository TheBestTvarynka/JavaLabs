package kpi.java.service;

import kpi.java.dao.OrderDao;
import kpi.java.dao.RoomDao;
import kpi.java.dto.CreateOrderDto;
import kpi.java.entity.Room;
import kpi.java.enums.RoomStatus;
import kpi.java.exception.BookNotFoundException;
import kpi.java.exception.UnavailableException;
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

    public String bookRoom(CreateOrderDto createDto)
            throws SQLException, IllegalArgumentException, BookNotFoundException {
        roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
        Optional<Room> room = roomRepository.findByRoomNumber(createDto.roomNumber);
        if (room.isPresent()) {
            roomRepository.updateStatus(room.get().getId(), RoomStatus.BOOKED);
        }
        SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
        createDto.room = room.orElseThrow(BookNotFoundException::new);
        orderRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
        orderRepository.createOrder(createDto);
        SimpleConnectionPool.getPool().releaseConnection(orderRepository.releaseConnection());
        return "All success! You have two days to pay for the order.";
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }
}
