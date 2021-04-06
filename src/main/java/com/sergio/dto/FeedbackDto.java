package com.sergio.dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;

public class FeedbackDto implements Serializable {

    private int id;
    private int rate;
    private Timestamp date;

    @Pattern(regexp = "[a-z0-9~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]{0,500}", message = "incorrectly description")
    private String text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }
}
