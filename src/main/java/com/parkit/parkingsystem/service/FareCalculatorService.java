package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

import java.util.concurrent.TimeUnit;

public class FareCalculatorService {

    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }
        long inTime = ticket.getInTime().getTime();
        long outTime = ticket.getOutTime().getTime();

       double durationTime = (double) outTime - (double) inTime;
       double duration = ((durationTime / 1000)/60/60);

        if (duration < 0.5) {
            ticket.setPrice(0);
        } else {
            double rate;
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR:
                    rate = Fare.CAR_RATE_PER_HOUR;
                    break;
                case BIKE:
                    rate = Fare.BIKE_RATE_PER_HOUR;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown Parking Type");
            }
            ticket.setPrice(duration * rate * (1-0.01*ticket.getDiscount()));
        }
    }
}
