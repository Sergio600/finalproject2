package com.sergio.domain;

import lombok.Data;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Blob;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "Attachment")
public class Attachment implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "blob")
    private Blob blob;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @OneToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;


}
