package com.sergio.domain;

import com.sergio.enums.State;
import com.sergio.enums.Urgency;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Table(name = "Tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", length = 100)
    private String name;

    @NotNull
    @Column(name = "description", length = 500)
    private String description;

    @NotNull
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @Column(name = "desired_resolution_date")
    private LocalDateTime desiredResolutionDate;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User userAssignee;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User userOwner;

    @NotNull
    @Column(name = "state_id")
    @Enumerated(ORDINAL)
    private State state;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(name = "urgency_id")
    @Enumerated(ORDINAL)
    private Urgency urgency;

    @ManyToOne
    @JoinColumn(name = "approver_id")
    private User userApprover;

}
