package kpi.java.service;

import kpi.java.dao.OrderDao;
import kpi.java.dao.RoomDao;
import kpi.java.dto.CreateOrderDto;
import kpi.java.entity.Room;
import kpi.java.enums.RoomStatus;
import kpi.java.exception.AlreadyBookedException;
import kpi.java.exception.BookNotFoundException;
import kpi.java.exception.UnavailableException;
import kpi.java.utils.SimpleConnectionPool;
import kpi.java.utils.schedule.DeleteOrderJob;
import kpi.java.utils.schedule.Scheduler;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class OrderService {
    private static OrderService orderService;

    private OrderDao orderRepository;
    private RoomDao roomRepository;

    private OrderService() {
        this.orderRepository = new OrderDao();
        this.roomRepository = new RoomDao();
    }

    public String bookRoom(CreateOrderDto createDto)
            throws UnavailableException, BookNotFoundException, AlreadyBookedException {
        UUID id;
        try {
            roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            Optional<Room> room = roomRepository.findByRoomNumber(createDto.roomNumber);
            if (room.isPresent()) {
                if (!room.get().getStatus().equals(RoomStatus.AVAILABLE)) {
                    throw new AlreadyBookedException();
                }
                roomRepository.updateStatus(room.get().getId(), RoomStatus.BOOKED);
            }
            SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
            createDto.room = room.orElseThrow(BookNotFoundException::new);
            orderRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            id = orderRepository.createOrder(createDto);
            SimpleConnectionPool.getPool().releaseConnection(orderRepository.releaseConnection());
        } catch (SQLException | IllegalArgumentException ignored) {
            throw new UnavailableException();
        }

        Date date = new Date();
        // 2 * 24 * 60 * 60 * 1000 = 172_800_000
        date.setTime(date.getTime() + 172_800_000L);
        Scheduler.scheduleJob(new DeleteOrderJob(id), date);
        return "All success! You have two days to pay for the order.";
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new OrderService();
        }
        return orderService;
    }
}
