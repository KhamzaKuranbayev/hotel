package uz.pdp.hotel.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.dto.RoomDTO;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.entity.Room;
import uz.pdp.hotel.repository.HotelRepository;
import uz.pdp.hotel.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    final RoomRepository roomRepository;
    final HotelRepository hotelRepository;

    public RoomController(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

    // Pageable
    @GetMapping("/page")
    public List<Room> getRoomPage(@RequestParam("page") Integer page, @RequestParam("size") Integer size,
                                  @RequestParam("id") Integer hotelId) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Room> roomPage = roomRepository.findAllByHotelId(hotelId, pageable);

        List<Room> roomList = roomPage.toList();

        return roomList;
    }

    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    @PostMapping("/{id}")
    public String save(@RequestBody RoomDTO roomDTO, @PathVariable Integer id) {
        Room room = new Room();
        room.setNumber(room.getNumber());
        room.setFloor(room.getFloor());
        room.setSize(room.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDTO.getHotelId());
        if (!optionalHotel.isPresent())
            return "Hotel not found!";
        room.setHotel(optionalHotel.get());

        roomRepository.save(room);
        return "Room saved!";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody RoomDTO roomDTO, @PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {
            optionalRoom.get().setNumber(roomDTO.getNumber());
            optionalRoom.get().setFloor(roomDTO.getFloor());
            optionalRoom.get().setSize(roomDTO.getSize());

            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDTO.getHotelId());
            if (!optionalHotel.isPresent())
                return "Hotel not found!";

            roomRepository.save(optionalRoom.get());
            return "Room updated!";
        }
        return "Room not found!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        roomRepository.deleteById(id);
        return "Room deleted!";
    }


}
