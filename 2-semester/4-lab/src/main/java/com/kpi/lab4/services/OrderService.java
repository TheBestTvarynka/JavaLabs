package com.kpi.lab4.services;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.dto.CreateOrderDto;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.enums.RoomStatus;
import com.kpi.lab4.exception.AlreadyBookedException;
import com.kpi.lab4.exception.BookNotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.dao.SimpleConnectionPool;
import com.kpi.lab4.services.schedule.DeleteOrderJob;
import com.kpi.lab4.services.schedule.Scheduler;

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
