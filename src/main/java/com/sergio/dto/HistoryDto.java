package com.sergio.dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

public class HistoryDto implements Serializable {

    private Long id;
    private Timestamp date;
    private String action;
    private UserDto user;



    @Pattern(regexp = "[a-z0-9~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]{0,500}", message = "incorrectly description")
    private String description;
    private TicketDto ticket;

    public HistoryDto() {
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }
}
