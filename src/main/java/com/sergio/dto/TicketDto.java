package com.sergio.dto;

import com.sergio.domain.Attachment;
import com.sergio.enums.State;
import com.sergio.enums.Urgency;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class TicketDto implements Serializable {

    private Long id;

    @Pattern(regexp = "[a-z0-9~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]{0,100}", message = "incorrectly name")
    private String name;

    @Pattern(regexp = "[a-z0-9~.\"(),:;<>@\\[\\]!#$%&'*+-/=?^_`{|}]{0,500}", message = "incorrectly description")
    private String description;
    private Timestamp createdOn;
    private Timestamp desiredResolutionDate;
    private State state;
    private Urgency urgency;
    private Attachment attachment;
    private UserDto userAssignee;
    private UserDto userOwner;
    private UserDto userApprover;
    private CategoryDto category;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private List<HistoryDto> historyList;
    private List<CommentDto> commentList;
    private List<FeedbackDto> feedbackList;


    public TicketDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Urgency getUrgency() {
        return urgency;
    }

    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public UserDto getUserAssignee() {
        return userAssignee;
    }

    public void setUserAssignee(UserDto userAssignee) {
        this.userAssignee = userAssignee;
    }

    public UserDto getUserOwner() {
        return userOwner;
    }

    public void setUserOwner(UserDto userOwner) {
        this.userOwner = userOwner;
    }

    public UserDto getUserApprover() {
        return userApprover;
    }

    public void setUserApprover(UserDto userApprover) {
        this.userApprover = userApprover;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public List<HistoryDto> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<HistoryDto> historyList) {
        this.historyList = historyList;
    }

    public List<CommentDto> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentDto> commentList) {
        this.commentList = commentList;
    }

    public List<FeedbackDto> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<FeedbackDto> feedbackList) {
        this.feedbackList = feedbackList;
    }

    @Override
    public String toString() {
        return "TicketDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", desiredResolutionDate=" + desiredResolutionDate +
                ", state=" + state +
                ", urgency=" + urgency +
                ", attachment=" + attachment +
                ", userAssignee=" + userAssignee +
                ", userOwner=" + userOwner +
                ", userApprover=" + userApprover +
                ", category=" + category +
                ", comment='" + comment + '\'' +
                ", historyList=" + historyList +
                ", commentList=" + commentList +
                ", feedbackList=" + feedbackList +
                '}';
    }
}
