package com.sergio.dto;

import java.io.Serializable;
import java.sql.Blob;

public class AttachmentDto implements Serializable {
    private Long id;
    private Blob blob;
    private String name;
    private TicketDto ticket;

    public AttachmentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TicketDto getTicket() {
        return ticket;
    }

    public void setTicket(TicketDto ticket) {
        this.ticket = ticket;
    }
}
