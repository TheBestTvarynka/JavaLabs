package com.kpi.lab4.services;

import com.kpi.lab4.dao.OrderDao;
import com.kpi.lab4.dao.RequestDao;
import com.kpi.lab4.dao.RoomDao;
import com.kpi.lab4.dto.CreateOrderDto;
import com.kpi.lab4.dto.CreateRequestDto;
import com.kpi.lab4.dto.Page;
import com.kpi.lab4.entities.Request;
import com.kpi.lab4.exception.NotFoundException;
import com.kpi.lab4.exception.UnavailableException;
import com.kpi.lab4.entities.Room;
import com.kpi.lab4.utils.SelectRoomOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RequestService {
    private static Logger logger = LogManager.getLogger(RequestService.class);

    private RequestDao requestRepository;
    private RoomDao roomRepository;
    private OrderDao orderRepository;

    public RequestService(RequestDao requestDao, RoomDao roomDao, OrderDao orderDao) {
        requestRepository = requestDao;
        roomRepository = roomDao;
        orderRepository = orderDao;
    }

    public Page<Room> selectRooms(SelectRoomOptions options) throws UnavailableException, IllegalArgumentException {
        Page<Room> page;
        try {
            List<Room> rooms = roomRepository.selectRooms(options);
            int from = (options.getPage() - 1) * options.getOffset();
            if (options.getPage() < 1 || from >= rooms.size()) {
                return new Page<>(new ArrayList<>(), options.getPage(), options.getOffset(), rooms.size());
            }
            int to = options.getPage() * options.getOffset();
            if (to >= rooms.size()) {
                to = rooms.size();
            }
            List<Room> sublist = rooms.subList(from, to);
            page = new Page<>(sublist, options.getPage(), options.getOffset(), rooms.size());
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            throw new UnavailableException();
        }
        return page;
    }

    public void createRequest(CreateRequestDto createDto) throws UnavailableException, IllegalArgumentException {
        try {
            requestRepository.save(createDto);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            throw new UnavailableException();
        }
    }

    public List<Request> getAllRequests() throws UnavailableException, IllegalArgumentException {
        try {
            return requestRepository.getAll();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            throw new UnavailableException();
        }
    }

    public String resolveRequest(UUID id, String roomNumber) throws UnavailableException, NotFoundException {
        try {
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
            return "Request closed. Order created.";
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage());
            throw new UnavailableException();
        }
    }
}
