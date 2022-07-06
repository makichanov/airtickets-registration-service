package com.makichanov.core.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flight_address")
@Getter
@Setter
@NoArgsConstructor
public class FlightAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flight_address_fa_id_seq")
    @SequenceGenerator(name = "flight_address_fa_id_seq", sequenceName = "flight_address_fa_id_seq", allocationSize = 1)
    @Column(name = "fa_id")
    private Long id;

    @Column(name = "fa_airport_name")
    private String airportName;

    @Column(name = "fa_airport_address")
    private String airportAddress;

    @OneToMany(mappedBy = "addressFrom")
    private List<AirTicket> ticketsFrom;

    @OneToMany(mappedBy = "addressTo")
    private List<AirTicket> ticketsTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlightAddress that = (FlightAddress) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (airportName != null ? !airportName.equals(that.airportName) : that.airportName != null) return false;
        return airportAddress != null ? airportAddress.equals(that.airportAddress) : that.airportAddress == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (airportName != null ? airportName.hashCode() : 0);
        result = 31 * result + (airportAddress != null ? airportAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlightAddress{" +
                "id=" + id +
                ", airportName='" + airportName + '\'' +
                ", airportAddress='" + airportAddress + '\'' +
                '}';
    }

}
