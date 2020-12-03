package com.kpi.lab4.services.schedule;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.entities.Order;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.enums.RoomStatus;
import com.kpi.lab4.dao.SimpleConnectionPool;

import java.sql.SQLException;
import java.util.Optional;
import java.util.TimerTask;
import java.util.UUID;

public class DeleteOrderJob extends TimerTask {
    private RoomDao roomRepository;
    private OrderDao orderRepository;
    private UUID orderId;

    public DeleteOrderJob(UUID id) {
        this.roomRepository = new RoomDao();
        this.orderRepository = new OrderDao();
        this.orderId = id;
    }

    @Override
    public void run() {
        try {
            orderRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isEmpty()) return;
            Order orderData = order.get();
            if (!orderData.getPaid()) {
                Room room = roomRepository.findByRoomNumber(orderData.getRoomNumber()).get();
                roomRepository.updateStatus(room.getId(), RoomStatus.AVAILABLE);
                orderRepository.deleteById(orderId);
            }
            SimpleConnectionPool.getPool().releaseConnection(orderRepository.releaseConnection());
            SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
        } catch (SQLException ignored) {

        }
    }
}
