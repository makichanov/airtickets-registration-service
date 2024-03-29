package com.makichanov.core.factory;

import com.makichanov.core.entity.AirTicket;
import com.makichanov.core.entity.FlightDetails;
import org.springframework.stereotype.Component;

@Component
public class AirTicketFactory {

    // TODO: 7/26/22 Неуместное использование паттерна Фабрика
    // Чем можно заменить?
    public AirTicket createAirTicket(FlightDetails flightDetails) {
        int place = flightDetails.getPlaces();
        AirTicket airTicket = new AirTicket();
        airTicket.setFlightDetails(flightDetails);
        airTicket.setPrice(flightDetails.getBasePrice());
        airTicket.setPlace(place);
        return airTicket;
    }

}
