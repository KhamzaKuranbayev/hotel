package uz.pdp.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    private Integer number;

    private Integer floor;

    private Integer size;

    @ManyToOne
    private Hotel hotel;

}
