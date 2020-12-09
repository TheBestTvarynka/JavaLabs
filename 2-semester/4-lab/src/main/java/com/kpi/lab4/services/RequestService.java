package com.kpi.lab4.services;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RequestDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.dao.SimpleConnectionPool;
import com.kpi.lab4.dto.CreateOrderDto;
import com.kpi.lab4.dto.CreateRequestDto;
import com.kpi.lab4.dto.Page;
import com.kpi.lab4.entities.Request;
import com.kpi.lab4.exception.NotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.utils.SelectRoomOptions;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestService {
    private RequestDao requestRepository;
    private RoomDao roomRepository;
    private OrderDao orderRepository;

    public RequestService() {
        requestRepository = new RequestDao();
        roomRepository = new RoomDao();
        orderRepository = new OrderDao();
    }

    public Page<Room> selectRooms(SelectRoomOptions options) throws UnavailableException, IllegalArgumentException {
        Page<Room> page;
        try {
            roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            List<Room> rooms = roomRepository.selectRooms(options);
            int from = (options.getPage() - 1) * options.getOffset();
            if (from >= rooms.size()) {
                from = rooms.size() - 1;
            }
            int to = options.getPage() * options.getOffset();
            if (to >= rooms.size()) {
                to = rooms.size();
            }
            List<Room> sublist = rooms.subList(from, to);
            page = new Page<>(sublist, options.getPage(), options.getOffset(), rooms.size());
            SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
        } catch (SQLException ignored) {
            throw new UnavailableException();
        }
        return page;
    }

    public String createRequest(CreateRequestDto createDto) throws UnavailableException {
        try {
            requestRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
            requestRepository.save(createDto);
            SimpleConnectionPool.getPool().releaseConnection(requestRepository.releaseConnection());
        } catch (SQLException | IllegalArgumentException e) {
            throw new UnavailableException();
        }
        return "All success. Our manager will choose the most suitable room for you.";
    }

    public List<Request> getAllRequests() throws SQLException, IllegalArgumentException {
        requestRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
        List<Request> requests = requestRepository.getAll();
        SimpleConnectionPool.getPool().releaseConnection(requestRepository.releaseConnection());
        return requests;
    }

    public String resolveRequest(UUID id, String roomNumber) throws SQLException, NotFoundException {
        requestRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
        orderRepository.setConnection(SimpleConnectionPool.getPool().getConnection());
        roomRepository.setConnection(SimpleConnectionPool.getPool().getConnection());

        Optional<Request> request = requestRepository.getById(id);
        Optional<Room> room = roomRepository.findByRoomNumber(roomNumber);
        if (request.isEmpty()) throw new NotFoundException("Request not found!");
        if (room.isEmpty()) throw new NotFoundException("Room not found!");
        requestRepository.deleteRequest(id);
        Request requestData = request.get();
        Room roomData = room.get();
        orderRepository.createOrder(new CreateOrderDto(
                requestData.getDateFrom(),
                requestData.getDateTo(),
                roomNumber,
                requestData.getPhone(),
                roomData
        ));

        SimpleConnectionPool.getPool().releaseConnection(requestRepository.releaseConnection());
        SimpleConnectionPool.getPool().releaseConnection(orderRepository.releaseConnection());
        SimpleConnectionPool.getPool().releaseConnection(roomRepository.releaseConnection());
        return "Request closed. Order created.";
    }
}
