package com.sergio.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class FeedbackDto implements Serializable {

    private int id;
    private int rate;
    private Timestamp date;
    private String taxt;
    private UserDto user;
    private TicketDto ticket;

    public FeedbackDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getTaxt() {
        return taxt;
    }

    public void setTaxt(String taxt) {
        this.taxt = taxt;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }
}
