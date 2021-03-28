package com.sergio.domain;

import com.sergio.enums.State;
import com.sergio.enums.Urgency;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class Ticket {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_on", nullable = false)
    private Timestamp createdOn;

    @Column(name = "desired_resolution_date", nullable = false)
    private Timestamp desiredResolutionDate;

    //вставить связь
    private int assignee_id;

    //вставить связь
    private int ownerId;

    @Column(name = "state_id", nullable = false)
    private State stateId;

    // вставить связь
    private int categoryId;

    @Column(name = "urgency_id")
    private Urgency urgencyId;

    //вставить связь
    private int approverId;


    public Ticket() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getDesiredResolutionDate() {
        return desiredResolutionDate;
    }

    public void setDesiredResolutionDate(Timestamp desiredResolutionDate) {
        this.desiredResolutionDate = desiredResolutionDate;
    }

    public int getAssignee_id() {
        return assignee_id;
    }

    public void setAssignee_id(int assignee_id) {
        this.assignee_id = assignee_id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public State getStateId() {
        return stateId;
    }

    public void setStateId(State stateId) {
        this.stateId = stateId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Urgency getUrgencyId() {
        return urgencyId;
    }

    public void setUrgencyId(Urgency urgencyId) {
        this.urgencyId = urgencyId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }
}
