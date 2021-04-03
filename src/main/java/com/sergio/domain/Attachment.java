package com.sergio.domain;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "attachment")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;


    @Column(name = "blob", nullable = false)
    private Blob blob;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;


    public Attachment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
