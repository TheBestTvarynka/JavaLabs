package com.kpi.lab4.services;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.dto.CreateOrderDto;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.enums.RoomStatus;
import com.kpi.lab4.enums.RoomType;
import com.kpi.lab4.exception.AlreadyBookedException;
import com.kpi.lab4.exception.BookNotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class OrderServiceTest {
    private RoomDao roomDao;
    private OrderDao orderDao;

    @Before
    public void before() {
        roomDao = mock(RoomDao.class);
        orderDao = mock(OrderDao.class);
    }

    @Test(expected = AlreadyBookedException.class)
    public void whenBookRoom_thenThrowAlreadyBookedException() throws SQLException {
        CreateOrderDto orderDto = new CreateOrderDto(new Date(), new Date(), "1-01", "0123", null);
        when(roomDao.findByRoomNumber(orderDto.getRoomNumber())).thenReturn(Optional.of(new Room(
                UUID.randomUUID(), "1-01", 3, RoomType.ROOM, RoomStatus.BOOKED, 4.5f
        )));

        OrderService service = new OrderService(orderDao, roomDao);
        service.bookRoom(orderDto);
    }

    @Test(expected = BookNotFoundException.class)
    public void whenBookRoom_thenThrowBookNotFoundException() throws SQLException {
        CreateOrderDto orderDto = new CreateOrderDto(new Date(), new Date(), "1-01", "0123", null);
        when(roomDao.findByRoomNumber(orderDto.getRoomNumber())).thenReturn(Optional.empty());

        OrderService service = new OrderService(orderDao, roomDao);
        service.bookRoom(orderDto);
    }

    @Test
    public void whenBookRoom_thenCreateOrderAndUpdateRoomStatus() throws SQLException {
        Room room = new Room(
                UUID.randomUUID(), "1-01", 3, RoomType.ROOM, RoomStatus.AVAILABLE, 4.5f
        );
        CreateOrderDto orderDto = new CreateOrderDto(new Date(), new Date(), "1-01", "0123", null);
        UUID orderId = UUID.randomUUID();
        when(roomDao.findByRoomNumber(orderDto.getRoomNumber())).thenReturn(Optional.of(room));
        when(orderDao.createOrder(orderDto)).thenReturn(orderId);

        new OrderService(orderDao, roomDao).bookRoom(orderDto);
        verify(roomDao).updateStatus(room.getId(), RoomStatus.BOOKED);
        verify(orderDao).createOrder(orderDto);
    }

    @Test(expected = UnavailableException.class)
    public void whenBookRoom_thenTrowUnavailableException() throws SQLException {
        CreateOrderDto orderDto = new CreateOrderDto(new Date(), new Date(), "1-01", "0123", null);
        doThrow(new SQLException()).when(roomDao).findByRoomNumber(orderDto.getRoomNumber());

        new OrderService(orderDao, roomDao).bookRoom(orderDto);
    }
}
