package com.sergio.dto;

import com.sergio.domain.Ticket;

import java.io.Serializable;
import java.sql.Timestamp;

public class CommentDto implements Serializable {

    private int id;
    private String text;
    private Timestamp date;
    private TicketDto ticket;
    private UserDto user;

    public CommentDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
