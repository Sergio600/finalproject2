package com.sergio.dto;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    private int id;
    private String name;
    private List<TicketDto> ticketList;

    public CategoryDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TicketDto> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketDto> ticketList) {
        this.ticketList = ticketList;
    }
}
