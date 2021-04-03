package com.sergio.domain;

import com.sergio.enums.State;
import com.sergio.enums.Urgency;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "ticket")
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

    //еще надо здесь доработать
    @Column(name = "state_id", nullable = false)
    private State state;

    // и здесь доработать
    @Column(name = "urgency_id")
    private Urgency urgency;

    @OneToOne(mappedBy = "ticket")
    private Attachment attachment;

    @OneToMany(mappedBy = "ticket")
    private List<Feedback> feedbackList;

    @OneToMany(mappedBy = "ticket")
    private List<Comment> commentList;

    @OneToMany(mappedBy = "ticket")
    private List<History> historyList;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    // есть вопрос как соединять пользователей
    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User userAssignee;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User userOwner;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User userApprover;

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

    public State getStateId() {
        return state;
    }

    public void setStateId(State state) {
        this.state = state;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgencyId(Urgency urgency) {
        this.urgency = urgency;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUserAssignee() {
        return userAssignee;
    }

    public void setUserAssignee(User userAssignee) {
        this.userAssignee = userAssignee;
    }

    public User getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(User userOwner) {
        this.userOwner = userOwner;
    }

    public User getUserApprover() {
        return userApprover;
    }

    public void setUserApprover(User userApprover) {
        this.userApprover = userApprover;
    }
}
