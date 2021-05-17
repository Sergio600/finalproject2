package com.sergio.dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

public class CommentDto implements Serializable {

    private Long id;
    private UserDto user;

    @Pattern(regexp = "[a-z0-9~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]{0,500}", message = "incorrectly description")
    private String text;
    private Timestamp date;
    private TicketDto ticket;


    public UserDto getUser() {
        return user;
    }

    public void setUserDto(UserDto user) {
        this.user = user;
    }

    public CommentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

}
