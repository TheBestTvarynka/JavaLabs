package com.kpi.lab4.services;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RequestDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.dto.CreateRequestDto;
import com.kpi.lab4.dto.Page;
import com.kpi.lab4.entities.Request;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.enums.RoomType;
import com.kpi.lab4.exception.NotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.utils.builders.SelectRoomOptions;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.*;

public class RequestServiceTest {
    RequestDao requestDao;
    OrderDao orderDao;
    RoomDao roomDao;

    @Before
    public void before() {
        requestDao = mock(RequestDao.class);
        orderDao = mock(OrderDao.class);
        roomDao = mock(RoomDao.class);
    }

    @Test
    public void whenCutPageFromEmptyList_thenReturnEmptyPage() throws SQLException {
        SelectRoomOptions options = new SelectRoomOptions();

        List<Room> res = new ArrayList<>();
        when(roomDao.selectRooms(options)).thenReturn(res);

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        Page<Room> page = service.selectRooms(options);
        assertEquals(0, page.getData().size());
        assertEquals(0, page.getCount());
        verify(roomDao).selectRooms(options);
    }

    @Test
    public void whenCutPageFromSmallList_thenReturnNotFullPage() throws SQLException {
        SelectRoomOptions options = new SelectRoomOptions();

        List<Room> res = new ArrayList<>();
        res.add(null);
        res.add(null);
        res.add(null);
        when(roomDao.selectRooms(options)).thenReturn(res);

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        Page<Room> page = service.selectRooms(options);
        assertEquals(3, page.getData().size());
        assertEquals(3, page.getCount());
        verify(roomDao).selectRooms(options);
    }

    @Test
    public void whenCutPageFromBigList_thenReturnOnePage() throws SQLException {
        SelectRoomOptions options = new SelectRoomOptions();

        List<Room> res = new ArrayList<>();
        res.add(null);
        res.add(null);
        res.add(null);
        res.add(null);
        res.add(null);
        res.add(null);
        when(roomDao.selectRooms(options)).thenReturn(res);

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        Page<Room> page = service.selectRooms(options);
        assertEquals(5, page.getData().size());
        assertEquals(6, page.getCount());
        verify(roomDao).selectRooms(options);
    }

    @Test
    public void whenCutWrongPage_thenReturnEmptyPage() throws SQLException {
        SelectRoomOptions options = new SelectRoomOptions();
        options.setPage(3);

        List<Room> res = new ArrayList<>();
        res.add(null);
        res.add(null);
        res.add(null);
        when(roomDao.selectRooms(options)).thenReturn(res);

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        Page<Room> page = service.selectRooms(options);
        assertEquals(0, page.getData().size());
        assertEquals(3, page.getCount());
        verify(roomDao).selectRooms(options);
    }

    @Test
    public void whenCreate_thenCallSave() throws SQLException {
        CreateRequestDto dto = new CreateRequestDto();
        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        service.createRequest(dto);
        verify(requestDao).save(dto);
    }

    @Test(expected = UnavailableException.class)
    public void whenCreate_thenThrowSQLException() throws SQLException {
        CreateRequestDto dto = new CreateRequestDto();
        doThrow(new SQLException()).when(requestDao).save(dto);

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        service.createRequest(dto);
        verify(requestDao).save(dto);
    }

    @Test
    public void testGetAllRequests() throws SQLException {
        List<Request> res = new ArrayList<>();
        res.add(new Request(UUID.randomUUID(), 3, RoomType.ROOM, new Date(), new Date(), "1"));
        res.add(new Request(UUID.randomUUID(), 2, RoomType.LUX, new Date(), new Date(), "2"));
        res.add(new Request(UUID.randomUUID(), 1, RoomType.PRESIDENT, new Date(), new Date(), "3"));
        res.add(new Request(UUID.randomUUID(), 1, RoomType.VIP, new Date(), new Date(), "4"));
        when(requestDao.getAll()).thenReturn(res);

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        assertEquals(service.getAllRequests(), res);
    }

    @Test(expected = UnavailableException.class)
    public void whenGetAll_thenThrowSQLException() throws SQLException {
        doThrow(new SQLException()).when(requestDao).getAll();

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        service.getAllRequests();
        verify(requestDao).getAll();
    }

    @Test(expected = NotFoundException.class)
    public void whenResolve_thenRequestNotFound() throws SQLException {
        UUID id = UUID.randomUUID();
        when(requestDao.getById(null)).thenReturn(Optional.empty());

        String room = "1-03";
        when(roomDao.findByRoomNumber(room)).thenReturn(Optional.of(new Room()));

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        service.resolveRequest(id, room);
    }

    @Test(expected = NotFoundException.class)
    public void whenResolve_thenRoomNotFound() throws SQLException {
        UUID id = UUID.randomUUID();
        when(requestDao.getById(null)).thenReturn(Optional.of(new Request()));

        String room = "1-03";
        when(roomDao.findByRoomNumber(room)).thenReturn(Optional.empty());

        RequestService service = new RequestService(requestDao, roomDao, orderDao);
        service.resolveRequest(id, room);
    }
}
