package uz.pdp.hotel.controller;

import org.springframework.web.bind.annotation.*;
import uz.pdp.hotel.entity.Hotel;
import uz.pdp.hotel.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    final HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @PostMapping
    public String save(@RequestBody Hotel hotel) {
        hotelRepository.save(hotel);
        return "Hotel saved!";
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Hotel hotel, @PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if(optionalHotel.isPresent()){
            optionalHotel.get().setName(hotel.getName());
            hotelRepository.save(optionalHotel.get());
            return "Hotel updated!";
        }
        return "Hotel not found!";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        hotelRepository.deleteById(id);
        return "Hotel deleted!";
    }


}
